package gzca.servlet.netone;


import com.syan.netonej.exception.NetonejExcepption;
import com.syan.netonej.http.client.PCSClient;
import com.syan.netonej.http.client.SVSClient;
import com.syan.netonej.http.client.TSAClient;
import com.syan.netonej.http.entity.TSR;
import gzca.ca.GZCANetONEJ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
                String cert = "";
                cert = request.getParameter("NetOne_certB64");
                String data = "";
                data = request.getParameter("netOneData");
                String sigature = "";
                sigature = request.getParameter("netOneValue");

                SVSClient svsClient = new SVSClient();
                svsClient.initClient("111.85.176.62", "9188");
                PrintWriter pw = response.getWriter();
                try {
                    int result = svsClient.verifyPKCS1(data, sigature, cert);
                    if (result == 200) {
                        pw.write("verify OK:" + "200");
                    } else pw.write("verify failed");
                } catch (NetonejExcepption netonejExcepption) {
                    netonejExcepption.printStackTrace();
                    pw.write("verify failed");
                }
                break;
            }

            case "timeStampByNetOne": {
                String data = "";
                data = request.getParameter("netOneData");

                String strTimestamp;
                TSAClient tsaClient = new TSAClient();
                tsaClient.initClient("111.85.176.62", "9198");
                PrintWriter pw = response.getWriter();
                try {
                    strTimestamp = tsaClient.createTimestamp(data, "sha1");
                    if (strTimestamp != null) {
                        pw.write(strTimestamp);
                    } else {
                        pw.write("error");
                    }
                } catch (NetonejExcepption netonejExcepption) {
                    pw.write("error");
                    netonejExcepption.printStackTrace();
                }
                break;
            }

            case "verifyTimeStamp": {
                String netOneValue = "";
                netOneValue = request.getParameter("netOneValue");
                TSAClient tsaClient = new TSAClient();
                tsaClient.initClient("111.85.176.62", "9198");
                PrintWriter pw = response.getWriter();
                try {
                    TSR str = tsaClient.verifyTimestamp(netOneValue);
                    pw.write(str.toString());
                } catch (NetonejExcepption netonejExcepption) {
                    pw.write("error");
                    netonejExcepption.printStackTrace();
                }
                break;
            }

            case "pubKeyEncrypt": {
                String data = "";
                data = request.getParameter("netOneData");

                PCSClient pcsClient = new PCSClient();
                pcsClient.initClient("111.85.176.62", "9178");
                PrintWriter pw = response.getWriter();
                String certList[] = new String[0];
                try {
                    certList = pcsClient.getPcsIds();
                    for (int i = 0; i < certList.length; i++) {
                        System.out.println(certList[i]);
                    }
                    String encryptData = pcsClient.pubKeyEncrypt(certList[0], "123456", "kid", data);
                    pw.write(encryptData);
                } catch (NetonejExcepption netonejExcepption) {
                    netonejExcepption.printStackTrace();
                    pw.write("error");
                }
                break;
            }

            case "priKeyDecrypt": {
                String data;
                String encryptData = "";
                encryptData = request.getParameter("netOneValue");

                PCSClient pcsClient = new PCSClient();
                pcsClient.initClient("111.85.176.62", "9178");
                PrintWriter pw = response.getWriter();
                String certList[];

                try {
                    certList = pcsClient.getPcsIds();
                    for (int i = 0; i < certList.length; i++) {
                        System.out.println(certList[i]);
                    }
                    data = pcsClient.priKeyDecrypt(certList[0], "123456", "kid", encryptData);
                    pw.write(data);
                } catch (NetonejExcepption netonejExcepption) {
                    netonejExcepption.printStackTrace();
                    pw.write("error");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
