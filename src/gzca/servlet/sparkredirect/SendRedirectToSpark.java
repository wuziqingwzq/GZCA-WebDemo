package gzca.servlet.sparkredirect;

import cn.com.syan.spark.app.sdk.classified.SparkClient;
import cn.com.syan.spark.app.sdk.classified.SparkClients;
import cn.com.syan.spark.app.sdk.classified.entity.Oid;
import cn.com.syan.spark.app.sdk.classified.entity.Response;
import cn.com.syan.spark.app.sdk.classified.entity.User;
import cn.com.syan.spark.app.sdk.connect.SparkConnectException;
import cn.com.syan.spark.app.sdk.connect.oauth.Oauth;
import gzca.config.PropertiesConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuziqing on 2017-4-5.
 */
@WebServlet(name = "SendRedirectToSpark", urlPatterns = "/SendRedirectToSpark")
public class SendRedirectToSpark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //操作选项表
        int typeIndex = 0;
        String type = request.getParameter("type");
        List<String> typelist = new ArrayList<String>();
        typelist.add("login");
        typelist.add("pushCert");
        typelist.add("updateCert");

        //确定选择功能模块
        for (int i = 0; i < typelist.size(); i++) {
            if (typelist.get(i).equals(type)) {
                typeIndex = i;
                break;
            }
        }

        switch (typeIndex) {
            case 0: {
                try {
                    Oauth oauth = new Oauth();                          //创建一个Oauth对象
                    String authURL = oauth.getAuthorizeURL(request);    //获取到统一互认地址
                    response.sendRedirect(authURL);                     //将用户的请求重定向到统一认证地址
                    break;
                } catch (SparkConnectException e) {
                    e.printStackTrace();
                    break;
                }
            }
            //推送数据到统一互认平台
            case 1: {
                String userName = "";
                String userIdNo = "";
                String userCert = "";
                String userOid = "";
                userName = request.getParameter("userName");
                userIdNo = request.getParameter("userIdNo");
                userCert = request.getParameter("userCert");
                userOid = request.getParameter("userOid");
                /****
                 * 以下代码为调用接口示例代码
                 */
                //推送数据客户端的配置
                String Url = "http://www.gzca.net.cn";
                PropertiesConfig pc = new PropertiesConfig();
                Url = pc.getConfig("spark_url");
                String PrivateKey =pc.getConfig("spark_private_key");    //管理员私钥
                String Cert = pc.getConfig("spark_certificate");          //管理员证书公钥
                //使用自定义配置初始化客户端
                SparkClient sc = new SparkClient(Url,PrivateKey,Cert);

                //获取默认的客户端
                //SparkClient sc = SparkClients.getDefaultClient();

                //创建用户对象
//                cn.com.syan.spark.app.sdk.classified.entity.User user = new cn.com.syan.spark.app.sdk.classified.entity.User();
                User user = new User();               //创建用户类
                user.setCertificate(userCert);        //设置用户证书B64
                user.setName(userName);               //设置用户名称 或者单位名称     如果不设置 系统为默认使用证书CN项作为名称
                user.setIdno(userIdNo);               //设置证件号（组织机构代码或身份证号）

                List<Oid> oidList = new ArrayList<Oid>();                               //创建扩展列表
                Oid oid = new Oid();                                                    //创建扩展项类
                oid.setOidMask(pc.getConfig("Spark_OidMask"));          //组扩展 标识（根据不同的应用，该项需要修改）
                oid.setOidValue(userOid);                                               //组上扩展值（证书对应的UserID）
                oidList.add(oid);

                /**
                 * 如果应用组没有oid扩展  oidList 可以为null
                 * 如   sc.joinGroup(user,1,null);
                 */
//                int groupID = 1;
                int groupID = Integer.valueOf(pc.getConfig("GroupID"));
                Response r = null;
                try {
                    r = sc.joinGroup(user, groupID, oidList);                       //执行推送数据，参数2是组ID，根据不同的应用，该项需要修改
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
                PrintWriter pw = response.getWriter();                              //推送失败后
                if (r == null) {
                    pw.write("push failed");
                    pw.close();
                } else {
                    if (r.getRet() == 0) {
                        pw.write("push success:" + r.getMsg());
                        pw.close();
                    }
                }
                break;
            }
            case 2: {
                String oldCert = "";
                String newCert = "";
                oldCert = request.getParameter("oldCert");
                newCert = request.getParameter("newCert");
                SparkClient sc = SparkClients.getDefaultClient();
                Response r = null;
                PrintWriter pw = response.getWriter();
                try {
                    r = sc.updateCertificate(oldCert, newCert);
                    if (r.getRet() == 0) {
                        pw.write("update success");
                        pw.close();
                    } else {
                        pw.write("update failed:" + r.getMsg());
                        pw.close();
                    }
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
