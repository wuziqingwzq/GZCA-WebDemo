package gzca.ca;

import gzca.ca.util.HttpSecurityClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//file:/D:/WorkSpace/GZCA-WebDemo/out/production/GZCA-WebDemo/gzca/ca/util/
//file:/D:/WorkSpace/GZCA-WebDemo/out/artifacts/GZCA_WebDemo_war_exploded/WEB-INF/classes/gzca/ca/
public class GZCANetONEJ {
    private String Url = "";
    private String Path = "";
    private int Port = 0;
    public String verifyCert(String cert){
        Url = "https://111.85.176.62";
        Path ="vc.svr";
        Port = 443;
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(new BasicNameValuePair("cert", cert));
        HttpSecurityClient httpSecurityClient = new HttpSecurityClient();
        String result = null;
        try {
            result = httpSecurityClient.HttpsDataPost(para,Url,Port,Path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
}
