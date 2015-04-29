package uiMain;

import client.MongodbConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;
import java.util.UUID;

/**
 * Created by admin on 2015/4/28.
 */
public class Main {
    static MongodbConnection connection = MongodbConnection.getInstance("qzone_post");


    public static void main(String[] args) {
        System.out.println("press 'Q'---EXIT");
        System.out.println("输入QQ...");
        Scanner scanner = new Scanner(System.in);
        MongoCollection dbColl = connection.getCollection();

        while (scanner.hasNext()){
            //exit
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("q")){
                System.exit(0);
            }
            int times = Integer.valueOf(command);
            for (int i = 0; i < times; i++) {
                Document document = new Document();
                document.append(UUID.randomUUID().toString(),"21dsa");
                dbColl.insertOne(document);
            }
            FindIterable<Document> array =  dbColl.find();
            while (array.iterator().hasNext()){
                System.out.println(array.iterator().next().toJson().toString());
            }
        }
    }
}
