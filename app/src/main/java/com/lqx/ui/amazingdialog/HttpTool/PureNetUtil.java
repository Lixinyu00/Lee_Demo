package com.lqx.ui.amazingdialog.HttpTool;

/**
 * Created by NEDUsoftware on 2017/4/18.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * 网络访问工具类
 */
public class PureNetUtil {
    public static String get(String url){
        return post(url,null);
    }
    /**
     * 设定post方法获取网络资源,如果参数为null,实际上设定为get方法
     * @param url 网络地址
     * @param param 请求参数键值对
     * @return 返回Json数据
     */
    public static String post(String url, Map  param){
        HttpURLConnection connection=null;
        try{
            URL url_1=new URL(url);
            connection=(HttpURLConnection)url_1.openConnection();
            StringBuffer sb=null;
            if(param!=null){
                sb=new StringBuffer();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                OutputStream out=connection.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                Set<Map.Entry<String,String>>p=param.entrySet();
                for(Map.Entry s:p){
                    sb.append(s.getKey()).append("=").append(s.getValue()).append("&");
                }
                //将参数通过输出流写入
                writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());
                writer.close();//一定要关闭,不然可能出现参数不全的错误
                sb=null;
            }
            connection.connect();
            sb=new StringBuffer();
            int recode=connection.getResponseCode();
            BufferedReader reader=null;
            if(recode==200){
                InputStream in=connection.getInputStream();
                reader=new BufferedReader(new InputStreamReader(in));
                String str=null;
                sb=new StringBuffer();
                while((str=reader.readLine())!=null){
                    sb.append(str).append(System.getProperty("line.separator"));
                }
                reader.close();
                if(sb.toString().length()==0){
                    return null;
                }
                return sb.toString().substring(0,sb.toString().length() - System.getProperty("line.separator").length());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
        return null;
    }
}
