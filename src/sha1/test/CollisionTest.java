package test;

import com.mongodb.client.MongoCollection;
import db.client.MongodbConnection;
import generator.DictionaryUtil;
import org.bson.Document;
import org.json.JSONObject;
import util.SHA1Encoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangJingtao on 2015/5/4.
 *
 * SHA-1 碰撞测试
 */
public class CollisionTest {

    public static void main(String[] args) {
        MongodbConnection connection = MongodbConnection.getInstance("SHA1-Collision");
        MongoCollection dbColl = connection.getCollection();
        DictionaryUtil du = new DictionaryUtil();
        int batchSize = 1000;//假设相邻的1000个value中不存在SHA1相同的...
        List<Document> batchDoc = new ArrayList<Document>();
        try {
            du.init();
            for (int i = 0; i < 20000000; i++) {
                String next = du.getNextStr();
                String hex = SHA1Encoder.SHA1(next);
                String cur = new JSONObject().put("cur_str", next).put("SHA1_str", hex).toString();
                Document value = Document.parse(cur);
                batchDoc.add(value);
                if (batchDoc.size() >= batchSize){
                    dbColl.insertMany(batchDoc);
                    batchDoc = new ArrayList<Document>();
                }
            }
            if (!batchDoc.isEmpty()){
                dbColl.insertMany(batchDoc);
            }
            du.destory();
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }
}
