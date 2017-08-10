package gzca.ca;

import gzca.ca.util.HttpSecurityClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GZCANetONEJ {
    private String Url = "";
    private String Path = "";
    private int Port = 0;

    public String verifyCert(String cert) {
        Url = "https://gzyzt.org.cn/";
        Path = "/vc.svr";
        Port = 443;

        //测试数据
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("cert", cert));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        String result = null;
        try {
            result = httpSecurityClient.HttpsDataPost(para, Url, Port, Path);
            System.out.println("gzcahttps调用信息：" + result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String verfiySign(String cert, String data, String signature) {

        return "";
    }

}
