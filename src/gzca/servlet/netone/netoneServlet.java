package gzca.servlet.netone;


import com.syan.netonej.http.client.PCSClient;
import gzca.ca.GZCANetONEJ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Created by wuziqing on 2017-4-27.
 */
@WebServlet(name = "netoneServlet", urlPatterns = "/netoneTest")
public class netoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = "";
        type = request.getParameter("type");
        switch (type) {
            case "verifycert": {
                //使用httpsPost方式调用证书。
                String cert = request.getParameter("NetOne_certB64");
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                String result = gzcaNetONEJ.verifyCert(cert);
                System.out.println("验证证书" + result);
                PrintWriter pw = response.getWriter();
                if (result.equals("200")) {
                    pw.write("200:verify OK");
                } else {
                    pw.write("verify failed");
                }
                break;

                //netoneJ调用验证证书
//                String cert = request.getParameter("NetOne_certB64");
//                SVSClient svsClient = new SVSClient();
//                svsClient.initClient("111.85.176.62", "9188");
//                PrintWriter pw = response.getWriter();
//                try {
//                    int verifyResult = svsClient.verifyCertificate(cert);
//                    System.out.println(verifyResult);
//                    pw.write("200:verify OK");
//                } catch (NetonejExcepption netonejExcepption) {
//                    netonejExcepption.printStackTrace();
//                    pw.write("verify failed");
//                }
//                pw.close();
//                break;
            }
            case "verifySignatureByNetOne": {
                //获取参数
                String cert = "";
                cert = request.getParameter("NetOne_certB64");
                String data = "";
                data = request.getParameter("netOneData");
                String sigature = "";
                sigature = request.getParameter("netOneValue");

                //netonej验证
//                SVSClient svsClient = new SVSClient();
//                svsClient.initClient("111.85.176.62", "9188");
//                PrintWriter pw = response.getWriter();
//                try {
//                    int result = svsClient.verifyPKCS1(data, sigature, cert);
//                    if (result == 200) {
//                        pw.write("verify OK:" + "200");
//                    } else pw.write("verify failed");
//                } catch (NetonejExcepption netonejExcepption) {
//                    netonejExcepption.printStackTrace();
//                    pw.write("verify failed");
//                }

                //以https方式验证
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                String result = gzcaNetONEJ.verfiySign(data, sigature, cert);
                PrintWriter pw = response.getWriter();
                if (result.equals("200")) {
                    pw.write("200:verify OK");
                } else {
                    pw.write("verify failed");
                }
                break;
            }

            case "timeStampByNetOne": {
                String data = "";
                data = request.getParameter("netOneData");
                String strTimestamp;

                //使用netoneJ来获取时间戳
//                TSAClient tsaClient = new TSAClient();
//                tsaClient.initClient("111.85.176.62", "9198");
//                PrintWriter pw = response.getWriter();
//                try {
//                    strTimestamp = tsaClient.createTimestamp(data, "sha1");
//                    if (strTimestamp != null) {
//                        pw.write(strTimestamp);
//                    } else {
//                        pw.write("error");
//                    }
//                } catch (NetonejExcepption netonejExcepption) {
//                    pw.write("error");
//                    netonejExcepption.printStackTrace();
//                }

                //使用gzcanetonej来获取时间戳
                PrintWriter pw = response.getWriter();
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                strTimestamp = gzcaNetONEJ.createTimestamp(data, "sha1");
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                if (strTimestamp != null && !strTimestamp.equals("")) {
                    pw.write(strTimestamp);
                } else {
                    pw.write("error");
                }
                break;
            }

            case "verifyTimeStamp": {
                String netOneValue = "";
                netOneValue = request.getParameter("netOneValue");

                //使用netoneJ来进行验证时间戳
//                TSAClient tsaClient = new TSAClient();
//                tsaClient.initClient("111.85.176.62", "9198");
//                PrintWriter pw = response.getWriter();
//                try {
//                    TSR str = tsaClient.verifyTimestamp(netOneValue);
//                    pw.write(str.toString());
//                } catch (NetonejExcepption netonejExcepption) {
//                    pw.write("error");
//                    netonejExcepption.printStackTrace();
//                }

                //使用GZCANetONEJ进行时间戳验证
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                String result = gzcaNetONEJ.verifyTimestamp(netOneValue, "sha1");
                PrintWriter pw = response.getWriter();
                if (result != null && !result.equals("")) {
                    pw.write(result);
                } else {
                    pw.write("error");
                }
                break;
            }

            case "pubKeyEncrypt": {
                String data = "";
                data = request.getParameter("netOneData");

                //使用NetONEJ公钥加密
//                PCSClient pcsClient = new PCSClient();
//                pcsClient.initClient("111.85.176.62", "9178");
//                PrintWriter pw = response.getWriter();
//                String certList[] = new String[0];
//                try {
//                    certList = pcsClient.getPcsIds();
//                    for (int i = 0; i < certList.length; i++) {
//                        System.out.println(certList[i]);
//                    }
//                    String encryptData = pcsClient.pubKeyEncrypt(certList[0], "123456", "kid", data);
//                    pw.write(encryptData);
//                } catch (NetonejExcepption netonejExcepption) {
//                    netonejExcepption.printStackTrace();
//                    pw.write("error");
//                }

                //使用GZCANetONEJ进行公钥加密
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                String certList[] = new String[0];
                certList = gzcaNetONEJ.getPcsIds();
                String result = gzcaNetONEJ.pubKeyEncrypt(certList[0], data);
                PrintWriter pw = response.getWriter();
                if (result != null && !result.equals("")) {
                    pw.write(result);
                } else {
                    pw.write("error");
                }
                break;
            }

            case "priKeyDecrypt": {
                String data;
                String encryptData = "";
                encryptData = request.getParameter("netOneValue");

                //使用NetONEJ进行私钥解密
                PCSClient pcsClient = new PCSClient();
                pcsClient.initClient("111.85.176.62", "9178");
                PrintWriter pw = response.getWriter();

                //使用NetONEJ进行私钥解密
//                String certList[];
//                try {
//                    certList = pcsClient.getPcsIds();
//                    for (int i = 0; i < certList.length; i++) {
//                        System.out.println(certList[i]);
//                    }
//                    data = pcsClient.priKeyDecrypt(certList[0], "123456", "kid", encryptData);
//                    pw.write(data);
//                } catch (NetonejExcepption netonejExcepption) {
//                    netonejExcepption.printStackTrace();
//                    pw.write("error");
//                }

                //使用GZCANetONEJ进行私钥解密
                GZCANetONEJ gzcaNetONEJ = new GZCANetONEJ();
                String certList[] = new String[0];
                certList = gzcaNetONEJ.getPcsIds();
                String result = gzcaNetONEJ.priKeyDecrypt(certList[0], encryptData,"123456");
                if (result != null && !result.equals("")) {
                    pw.write(result);
                } else {
                    pw.write("error");
                }
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
