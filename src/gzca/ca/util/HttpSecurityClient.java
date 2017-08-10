package gzca.ca.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

public class HttpSecurityClient {
    public void dopost(){

    }
    /**
     * 信任https网站证书
     *
     * @return
     */

    public void getclasspath(){
        System.out.println(this.getClass().getResource(""));
    }

    /**
     * 使用HTTPS协议与用户证书管理系统交互
     *
     * @throws URISyntaxException
     */
    public String HttpsDataPost(List<NameValuePair> para,String url,int port,String path) throws URISyntaxException {
        //获取传入地址的host名，根据初始化配置来进行连接
        String result = "";
        URI uriByUrl = new URI(url);
        System.out.println(uriByUrl);
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost(uriByUrl.getHost())
                .setPort(port)
                .setPath(path)
                .build();
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .build();
        HttpPost httpPost = new HttpPost(uri);
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(para, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            closeableHttpResponse = (CloseableHttpResponse) httpClient.execute(httpPost);
            result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            result = String.valueOf(closeableHttpResponse.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslcsf = null;
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        sslcsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
            @Override
            public void verify(String s, SSLSocket sslSocket) throws IOException {

            }

            @Override
            public void verify(String s, X509Certificate x509Certificate) throws SSLException {

            }

            @Override
            public void verify(String s, String[] strings, String[] strings1) throws SSLException {

            }

            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        return sslcsf;
    }
}
