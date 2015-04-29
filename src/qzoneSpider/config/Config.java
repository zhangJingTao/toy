package config;

/**
 * 数据库连接配置
 * Created by ZhangJingtao on 2015/4/28.
 */
public class Config {
    public static int REPEAT_TIMES = 3;//尝试错误次数
    public static String URL = "";
    public static Integer PAGES = 0;//获取页数 0 == 获取所有
    //    static String DB_DRIVER = "com.mysql.jdbc.Driver";
//    static String DB_URL = "jdbc:mysql://localhost:3306/toy?useUnicode=true&characterEncoding=utf-8";//
    public static String DB_URL = "localhost";
    public static String DB_NAME = "toy";
    public static Integer DB_PORT = 27017;
    public static String DB_USER = "";
    public static String DB_PWD = "";


}
