package gzca.servlet.sparkredirect;

import cn.com.syan.spark.app.sdk.connect.SparkConnectException;
import cn.com.syan.spark.app.sdk.connect.oauth.Oauth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuziqing on 2017-4-3.
 */

@WebServlet(name = "SparkRedirectServlet",urlPatterns = "/SparkRedirectServlet")
public class SparkRedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testservlet");
        if(request.getParameter("type")=="login"){
            try {
                String authURL = new Oauth().getAuthorizeURL(request);
                response.sendRedirect(authURL);
            } catch (SparkConnectException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
