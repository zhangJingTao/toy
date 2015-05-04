package generator;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import db.client.MongodbConnection;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by ZhangJingtao on 2015/5/4.
 */
public class DictionaryUtil {
    private static DictionaryUtil instance;
    private Integer curLength;
    private String curStr;//当前的str
    private MongoCursor cursor = null;

    public static String CONFIG_HASH = "18973892071979";
    public static Integer BASIC_LENGTH = 8;//生成的字符串最少8位
    public static String MIN_STR = "10000000";//起始最小
    MongodbConnection connection = MongodbConnection.getInstance("SHA1-DictStr");
    MongoCollection dbColl = connection.getCollection();


    public static DictionaryUtil getInstance() {
        if (instance == null) {
            instance = new DictionaryUtil();
        }
        return instance;
    }

    public void init() throws JSONException {
        BasicDBObject query = new BasicDBObject("config_id", CONFIG_HASH);
        Iterator<MongoCursor> result = dbColl.find(query).iterator();
        if (!result.hasNext()) {
            setCurStr(MIN_STR);
            setCurLength(BASIC_LENGTH);
        } else {
            cursor = result.next();
            String jsonStr = JSON.serialize(cursor);
            JSONObject json = new JSONObject(jsonStr);
            setCurStr(json.getString("cur_str"));
            setCurLength(json.getInt("cur_length"));
        }
    }

    public void destory() {
        try {
            dbColl.deleteOne(new BasicDBObject("config_id", CONFIG_HASH));
            String cur = new JSONObject().put("config_id", CONFIG_HASH).put("cur_str", curStr).put("cur_length", curLength).toString();
            Document value = Document.parse(cur);
            dbColl.insertOne(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //length: 62 进制
    public static String[] dict =
            {
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                    "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
                    "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                    "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            };

    public void setCurStr(String curStr) {
        this.curStr = curStr;
    }

    public void setCurLength(Integer curLength) {
        this.curLength = curLength;
    }

    public String getNextStr() {
        String[] curArray = curStr.split("");
        Integer length = curArray.length;
        return "";
    }
}
