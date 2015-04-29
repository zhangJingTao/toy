package config;

/**
 * 数据库连接配置
 * Created by ZhangJingtao on 2015/4/28.
 */
public class Config {
    public static String URL = "http://taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6?uin=[QQ]&inCharset=utf-8&outCharset=utf-8&hostUin=34572806&notice=0&sort=0&pos=[POS]&num=20&code_version=1&format=json&need_private_comment=1&g_tk=910822701";
    //    static String DB_DRIVER = "com.mysql.jdbc.Driver";
//    static String DB_URL = "jdbc:mysql://localhost:3306/toy?useUnicode=true&characterEncoding=utf-8";//
    public static String DB_URL = "localhost";
    public static String DB_NAME = "toy";
    public static Integer DB_PORT = 27017;
    public static String DB_USER = "";
    public static String DB_PWD = "";


}
