package client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpConnection{


    /**
     * 发送Get请求
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static String readContentFromGet(String url) throws IOException{
        String getURL = url;
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
        String lines;
        StringBuffer html = new StringBuffer();
        while ((lines = reader.readLine()) != null){
           html.append(lines);
        }
        reader.close();
        connection.disconnect();
        return html.toString();
    }

    /**
     * 发送Post请求
     * @param url
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readContentFromPost(String url) throws IOException, JSONException {
        URL postUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        String content = "" + "&activatecode=" + URLEncoder.encode("久酷博客", "utf-8");
        out.writeBytes(content);
        out.flush();
        out.close(); // flush and close
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
        String line;
        StringBuffer html = new StringBuffer();
        while ((line = reader.readLine()) != null){
            html.append(line);
        }
        reader.close();
        connection.disconnect();
        return new JSONObject(html.toString());
    }
}