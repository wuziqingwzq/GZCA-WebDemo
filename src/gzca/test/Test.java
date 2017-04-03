package gzca.test;

import com.gzca.CA;
import com.gzca.HttpClientUtil;
import com.gzca.SSLClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.*;

/**
 * Created by wuziqing on 2017-3-6.
 */
public class Test {
    public static void main(String arg[]) {
//----------------------------------------------------------------测试发送https-------------------------
//        String statusCode = "";
//        try {
//            ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
//            Protocol.registerProtocol("https",new Protocol("https",fcty,443));
//            HttpClient httpClient = new HttpClient();
//            PostMethod post = new PostMethod("https://58.42.231.108:5001/gzcaverify/servlet/VerifyInterface?forto=VerifyCertificate&Appnameen=Test_W&Serialnumber=070005201702081100000000140080");
//            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//            int reVal = httpClient.executeMethod(post);
//            if (reVal == 200) {
//                statusCode = post.getResponseBodyAsString().trim();
//            } else {
//                statusCode = reVal + "|访问失败";
//            }
//        } catch (IOException e) {
//            // TODO 自动生成 catch 块
//            e.printStackTrace();
//        }
//        System.out.println(statusCode);
//        System.out.println("--------------------------------------");

//      ---------------------测试连接用户证书管理系统，3.0jar包------------------------------------------------------
//        CA ca= new CA("https://58.42.231.108:5001","Test_W");
//
//        Long begintime = new Date().getTime();
////        System.out.println(ca.VerifySignedData("070005201702250100000000143456","123","1L2MOINDjjoUHsVAGjoGUYxIQxdmKZx9OCzEu7na9p/wTjJeTd1l2lr15czEAf1d62aayTA1ExkdviLiE3bwmFmUTC/HH30vaipoLvScKos1rHgHzgj8rqcMetwRH4G5a3EUXPHK/KQ6LDyaPTbd5sXuqY0hyF5Oqbc1rE/96LM="));
//        System.out.println(ca.VerifyCertificate("070005201702250100000000143456"));
//        Long overtime = new Date().getTime();
//        System.out.println((overtime-begintime)+"毫秒");
//      ---------------------测试连接时间戳服务----------------------------------------------------------------------
//        String URL = "https://10.0.33.30:9198/tsac.svr";
//        String sha1str = "932F3C1B56257CE8539AC269D7AAB42550DACF8818D075F0BDF1990562AAE3EF";
//        System.out.println(sha1str.length());
//        HashMap createMap = new HashMap();
//        createMap.put("digest",sha1str );
////        createMap.put("algo", "sha1");
////        HttpClientUtil httpClientUtil = new HttpClientUtil();
//        TryHttpClientUtil httpClientUtil =  new TryHttpClientUtil();
//        String getreturn = httpClientUtil.doPost(URL, createMap);
//        if (getreturn != null){
//            System.out.println(getreturn);
//        }else System.out.println("没有返回值");



//      --------------------------------------------------------------------------------------------------------------
    }
    //测试httppost方法，继承gzca包中原有的信息进行重构
//    static class TryHttpClientUtil extends HttpClientUtil{
//        private String charset = "utf-8";
//        public String doPost(String url, Map<String, String> map){
//            SSLClient httpClient = null;
//            HttpPost httpPost = null;
//            String result = null;
//
//            try {
//                httpClient = new SSLClient();
//                String ex = url ;
//                httpPost = new HttpPost(ex);
//                ArrayList list = new ArrayList();
//                Iterator iterator = map.entrySet().iterator();
//
//                while(iterator.hasNext()) {
//                    Map.Entry response = (Map.Entry)iterator.next();
//                    list.add(new BasicNameValuePair((String)response.getKey(), (String)response.getValue()));
//                }
//
//                if(list.size() > 0) {
//                    UrlEncodedFormEntity response1 = new UrlEncodedFormEntity(list, this.charset);
//                    httpPost.setEntity(response1);
//                }
//
//                HttpResponse response2 = httpClient.execute(httpPost);
//                if(response2 != null) {
//                    HttpEntity resEntity = response2.getEntity();
//                    if(resEntity != null) {
//                        result = EntityUtils.toString(resEntity, this.charset);
//                    }
//                }
//            } catch (Exception var11) {
//                var11.printStackTrace();
//            }
//            return null;
//        }
//    }


}
