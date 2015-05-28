package test;

import com.mongodb.client.MongoCollection;
import db.client.MongodbConnection;
import generator.DictionaryUtil;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.json.JSONObject;
import util.SHA1Encoder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ZhangJingtao on 2015/5/4.
 *
 * SHA-1 碰撞测试
 */
public class CollisionTest {

    public static void main(String[] args) throws IOException {
        MongodbConnection connection = MongodbConnection.getInstance("SHA1-Collision");
        MongoCollection dbColl = connection.getCollection();
        DictionaryUtil du = new DictionaryUtil();
        int batchSize = 100000;//假设相邻的1000个value中不存在SHA1相同的...
        List<Document> batchDoc = new ArrayList<Document>();
        String cur = new String();
        try {
            du.init();
            for (int i = 0; i < 3000000000L; i++) {
                String next = du.getNextStr();
                if (next == null || next.length()==0){
                    continue;
                }
                String hex = SHA1Encoder.SHA1(next);
                cur = new JSONObject().put("cur_str", next).put("SHA1_str", hex).toString();
                Document value = Document.parse(cur);
                batchDoc.add(value);
                if (batchDoc.size() >= batchSize){
                    System.out.println("当前到第" + i + "个");
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
            System.out.println("异常类型:"+ex.getMessage());
            StringBuffer log = new StringBuffer();
            for (Object o : batchDoc.toArray()){
                log.append(o.toString()+"||");
            }
            //写入文件防止console过小丢失
            FileUtils.writeStringToFile(new File("C://sha1.log"),"异常类型:"+ex.getMessage());
            FileUtils.writeStringToFile(new File("C://sha1.log"),"当前异常数据:"+log.toString());
            du.destory();
        }



    }
}
