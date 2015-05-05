package generator;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import db.client.MongodbConnection;
import org.bson.BsonDocument;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by ZhangJingtao on 2015/5/4.
 */
public class DictionaryUtil {
    private static DictionaryUtil instance;
    private Integer curLength;
    private Long curStr;//当前的十进制
    private MongoCursor cursor = null;

    public static String CONFIG_HASH = "BE9043E68BAFF1DF54ADF2E0F9F8C1CF";
    public static Integer BASIC_LENGTH = 8;//生成的字符串最少8位
    public static Long MIN_STR = 218340105584896L;//起始最小十进制 --  62进制的 100000000
    MongodbConnection connection = MongodbConnection.getInstance("SHA1-DictStr");
    MongoCollection dbColl = connection.getCollection();


    public static DictionaryUtil getInstance() {
        if (instance == null) {
            instance = new DictionaryUtil();
        }
        return instance;
    }

    public void init() throws JSONException {
        System.out.println(new Date()+"开始初始化...");
        BasicDBObject query = new BasicDBObject("config_id", CONFIG_HASH);
        Iterator<MongoCursor> result = dbColl.find(query).iterator();
        if (!result.hasNext()) {
            setCurStr(MIN_STR);
            setCurLength(BASIC_LENGTH);
        } else {
            Document document = (Document) result.next();
            String jsonStr = JSON.serialize(document);
            JSONObject json = new JSONObject(jsonStr);
            setCurStr(json.getLong("cur_str"));
            setCurLength(json.getInt("cur_length"));
        }
        System.out.println("当前配置||初始值(十进制):"+MIN_STR+"长度"+BASIC_LENGTH);
        System.out.println(new Date()+"初始化完成...");
    }

    public void destory() {
        try {
            System.out.println(new Date()+"本次销毁中...");
            dbColl.deleteOne(new BasicDBObject("config_id", CONFIG_HASH));
            String cur = new JSONObject().put("config_id", CONFIG_HASH).put("cur_str", curStr).put("cur_length", curLength).toString();
            Document value = Document.parse(cur);
            dbColl.insertOne(value);
            System.out.println(new Date()+"销毁成功...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //length: 62 进制
//    public static String[] dict =
//            {
//                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
//                    "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
//                    "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
//                    "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
//            };

    public void setCurStr(Long curStr) {
        this.curStr = curStr;
    }

    public void setCurLength(Integer curLength) {
        this.curLength = curLength;
    }

    public String getNextStr() throws Exception{
        if (curStr == null){
            throw new Exception("请先执行init()方法");
        }
        String result = TenTo62.baseString(new BigInteger(curStr.toString()),62);
        curStr++;
        return result;
    }
}
