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
 * Created by wuziqing on 2017-4-5.
 */
@WebServlet(name = "SendRedirectToSpark",urlPatterns = "/SendRedirectToSpark")
public class SendRedirectToSpark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("type").equals("login")){
            try {
                Oauth oauth = new Oauth();
                String authURL = oauth.getAuthorizeURL(request);
                System.out.println(authURL);
                response.sendRedirect(authURL);
            } catch (SparkConnectException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
