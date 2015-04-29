package uiMain;

import client.HttpConnection;
import client.MongodbConnection;
import com.mongodb.client.MongoCollection;
import config.Config;
import org.bson.Document;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Created by admin on 2015/4/28.
 */
public class Main {
    static MongodbConnection connection = MongodbConnection.getInstance("qzone_post");

    public static void main(String[] args) {
        String url = Config.URL;
        System.out.println("press 'Q'---EXIT");
        System.out.println("输入QQ...");
        Scanner scanner = new Scanner(System.in);
        MongoCollection dbColl = connection.getCollection();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            System.out.println("请输入获取页数(每页20条)..");
            while (scanner.hasNextInt()) {
                Integer pager = scanner.nextInt();
                for (int i = 0; i < pager; i++) {
                    Integer pos = i * 20;
                    String curUrl = url.replace("[QQ]", command).replace("[POS]", pos + "");
                    System.out.println(curUrl);
                    try {
                        JSONObject cur = HttpConnection.readContentFromGet(curUrl);
                        Document document = Document.parse(cur.toString());
                        dbColl.insertOne(document);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
    }
}
