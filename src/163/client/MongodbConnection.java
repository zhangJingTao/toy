package client;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.Config;

/**
 * Created by admin on 2015/4/28.
 */
public class MongodbConnection{
    static MongodbConnection instance;
    static MongoDatabase db;
    static MongoClient mongoClient;
    static MongoCollection collection;
    static String instanceName;

    public static synchronized MongodbConnection getInstance(String instName) {
        if (instance == null) {
            instance = new MongodbConnection();
            setInstanceName(instName);
            init();
        }
        return instance;
    }

    static void init(){
        mongoClient = new MongoClient(Config.DB_URL,Config.DB_PORT );
        // 连接到数据库
        db = mongoClient.getDatabase("toy");
//        boolean auth = db.(Config.DB_USER, Config.DB_PWD);
//        System.out.println("权限校验结果:"+auth);
        collection = db.getCollection(instanceName);
    }

    public MongoCollection getCollection() {
        if (collection == null){
             throw  new NullPointerException("请先执行init()...");
        }
        return collection;
    }

    public static void setInstanceName(String instanceName) {
        MongodbConnection.instanceName = instanceName;
    }
}
