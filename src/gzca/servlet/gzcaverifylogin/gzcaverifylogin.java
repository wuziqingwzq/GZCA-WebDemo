package gzca.servlet.gzcaverifylogin;


import com.gzca.CA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wuziqing on 2017-3-8.
 */
@WebServlet(name = "gzcaverifylogin", urlPatterns = "/gzcaverifylogin")
public class gzcaverifylogin extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //初始化连接参数

        String type = request.getParameter("type");
        String gzcaverifyURL = "https://58.42.231.108:5001";
        String app_name = "Test_W";
        response.setHeader("Content-Type", "text/html;charset=UTF-8");

        //创建CA对象
        CA ca = new CA(gzcaverifyURL, app_name);

        //连接用户证书管理系统
        String result = "connect failed......";
        if (type.equals("VerifyCertificate")) {
            //验证证书
            String cert_serial = request.getParameter("cert_sign_serial");
            result = ca.VerifyCertificate(cert_serial);
        } else if (type.equals("VerifySign")) {
            //验证签名
            String cert_serial = "";
            cert_serial = request.getParameter("cert_sign_serial");
            String data = "";
            data = request.getParameter("data");
            String signature = "";
            signature = request.getParameter("signature");
            signature = signature.replace(" ","+");
            result = ca.VerifySignedData(cert_serial, data, signature);
            System.out.println("签名原文：" + data);
            System.out.println("签名结果：" + signature);
        }

        //返回用户证书管理系统响应
        String a[] = result.split("\r\n");
        result = a[0];
        System.out.println(System.currentTimeMillis() + "连接成功：结果为" + result);

        //以字符流返回response
        PrintWriter pw = response.getWriter();
        pw.write(result);
        pw.close();
//        //以字节流返回response
//        ServletOutputStream outStream = response.getOutputStream();
//        outStream.write(result.getBytes());
//        outStream.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

    public void init() throws ServletException {
        //custom initialization code
    }

    public void destroy() {
        System.out.println("servlet over");
    }

}
