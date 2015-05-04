package test;

import com.mongodb.client.MongoCollection;
import db.client.MongodbConnection;

/**
 * Created by ZhangJingtao on 2015/5/4.
 *
 * SHA-1 碰撞测试
 */
public class CollisionTest {

    public static void main(String[] args) {
        MongodbConnection connection = MongodbConnection.getInstance("SHA1-Collision");
        MongoCollection dbColl = connection.getCollection();
    }
}
