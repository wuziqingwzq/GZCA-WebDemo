package gzca.ca;

import gzca.ca.util.GzcaUtil;
import gzca.ca.util.HttpSecurityClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GZCANetONEJ {
    private String Url = "https://gzyzt.org.cn/";
    private int Port = 0;
    private String Path = "";


    public String verifyCert(String cert) {
        Path = "/vc.svr";
        Port = 443;
        //测试数据
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("cert", cert));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        String result = null;
        try {
            result = httpSecurityClient.HttpsDataPost(para, Url, Port, Path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String verfiySign(String data, String signature, String cert) {
        Path = "/vp1.svr";
        Port = 443;

        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("cert", cert));
        para.add(new BasicNameValuePair("signature", signature));
        para.add(new BasicNameValuePair("data", data));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        String result = null;
        try {
            result = httpSecurityClient.HttpsDataPost(para, Url, Port, Path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String createTimestamp(String digest, String algo) throws IOException {
        Path = "/tsac.svr";
        Port = 443;
        String result = null;

        List<NameValuePair> para = new ArrayList<NameValuePair>();
        digest = GzcaUtil.sha1Encode(digest);
        para.add(new BasicNameValuePair("digest", digest));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        HttpEntity entity = httpSecurityClient.HttpsPostRasp(para, Url, Port, Path).getEntity();
        byte[] entitybyte = EntityUtils.toByteArray(entity);
        result = new String(Base64.encodeBase64(entitybyte));
        return result;
    }

    public String verifyTimestamp(String tsr, String algo) {
        Path = "/tsav.svr";
        Port = 443;
        String result = null;

        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("tsr", tsr));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
//        HttpEntity entity = httpSecurityClient.HttpsPostRasp(para, Url, Port, Path).getEntity();
        CloseableHttpResponse rsps = httpSecurityClient.HttpsPostRasp(para,Url,Port,Path);
        try {
            if (rsps.getStatusLine().getStatusCode()==200){
                HttpEntity entity = rsps.getEntity();
                result = EntityUtils.toString(entity);
            }else return null;
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String pubKeyEncrypt(String certid, String data) {
        Path = "/ppe.svr";
        Port = 443;
        String result = null;
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("id",certid));
        para.add(new BasicNameValuePair("data",GzcaUtil.base64Encode(data)));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        HttpEntity entity = httpSecurityClient.HttpsPostRasp(para, Url, Port, Path).getEntity();
        try {
            result = EntityUtils.toString(entity,"utf-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String priKeyDecrypt(String certid, String data,String passwd) {
        Path = "/spd.svr";
        Port = 443;
        String result = null;
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("id",certid));
        para.add(new BasicNameValuePair("data",data));
        para.add(new BasicNameValuePair("passwd",passwd));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        HttpEntity entity = httpSecurityClient.HttpsPostRasp(para, Url, Port, Path).getEntity();
        try {
            result = EntityUtils.toString(entity,"utf-8");
            return GzcaUtil.base64Decode(result);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getPcsIds(){
        Path = "/sl.svr";
        Port = 443;
        String certList[] = null;
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        try {
            CloseableHttpResponse resp = httpSecurityClient.HttpsPostRasp(para, Url, Port, Path);
            HttpEntity entity =resp.getEntity();
            String list = EntityUtils.toString(entity,"utf-8");
            certList = list.trim().split("\n");
            return certList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
