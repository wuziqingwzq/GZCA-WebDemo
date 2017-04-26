package gzca.servlet.sparkredirect;

import cn.com.syan.spark.app.sdk.classified.SparkClient;
import cn.com.syan.spark.app.sdk.classified.SparkClients;
import cn.com.syan.spark.app.sdk.classified.entity.Oid;
import cn.com.syan.spark.app.sdk.classified.entity.Response;
import cn.com.syan.spark.app.sdk.classified.entity.User;
import cn.com.syan.spark.app.sdk.connect.SparkConnectException;
import cn.com.syan.spark.app.sdk.connect.oauth.Oauth;

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

        //确定选择某一模块
        for (int i = 0; i < typelist.size(); i++) {
            if (typelist.get(i).equals(type)) {
                typeIndex = i;
                break;
            }
        }

        switch (typeIndex) {
            case 0: {
                try {
                    Oauth oauth = new Oauth();
                    String authURL = oauth.getAuthorizeURL(request);
                    System.out.println(authURL);
                    response.sendRedirect(authURL);
                    break;
                } catch (SparkConnectException e) {
                    e.printStackTrace();
                    break;
                }
            }
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
                SparkClient sc = SparkClients.getDefaultClient();
                User user = new User();
                user.setCertificate(userCert);
                user.setName(userName);       //设置用户名称 或者单位名称     如果不设置 系统为默认使用证书CN项作为名称
                user.setIdno(userIdNo);               //证件号
                List<Oid> oidList = new ArrayList<Oid>();
                Oid oid = new Oid();
                oid.setOidMask("Test_Group_OID");             //组扩展 标识
                oid.setOidValue(userOid);           //组上扩展值
                oidList.add(oid);
                /**
                 * 如果应用组没有oid扩展  oidList 可以为null
                 * 如   sc.joinGroup(user,1,null);
                 */
                Response r = null;
                try {
                    r = sc.joinGroup(user, 1, oidList);
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
                PrintWriter pw = response.getWriter();
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
