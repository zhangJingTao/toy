package uiMain;

import client.HttpConnection;
import client.MongodbConnection;
import com.mongodb.client.MongoCollection;
import config.Config;
import org.bson.Document;

import java.util.Scanner;

/**
 * Created by admin on 2015/4/28.
 */
public class Main {
    public static void main(String[] args) {
        String url = Config.URL;
        System.out.println("新闻URL:http://news.163.com/15/0429/20/AOD5B3P500014PRF.html ID即AOD5B3P500014PRF");
        System.out.println("输入新闻ID...");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            MongodbConnection connection = MongodbConnection.getInstance("163"+command);
            MongoCollection dbColl = connection.getCollection();

            System.out.println("请输入获取评论页数..");
            while (scanner.hasNextInt()) {
                Integer pager = scanner.nextInt();
                for (int i = 1; i < pager+1; i++) {
                    Integer pos = i;
                    String curUrl = url.replace("[ID]", command).replace("[PAGNO]", pos + "");
                    System.out.println(curUrl);
                    try {
                        String cur = HttpConnection.readContentFromGet(curUrl);
                        cur = cur.replace("var newPostList=","");
                        cur = cur.substring(0,cur.length()-1);
                        System.out.println(cur);
                        Document document = Document.parse(cur);
                        dbColl.insertOne(document);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
    }
}
