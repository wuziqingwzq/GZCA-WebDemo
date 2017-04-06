package gzca.test;

import cn.com.syan.spark.app.sdk.connect.oauth.Oauth;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by wuziqing on 2017-3-6.
 */
public class Test {
    public static void main(String arg[]) {

        Oauth oauth = new Oauth();
        System.out.println(oauth);

    }

}
