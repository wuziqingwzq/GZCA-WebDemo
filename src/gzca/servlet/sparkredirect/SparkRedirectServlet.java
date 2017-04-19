package gzca.servlet.sparkredirect;

import cn.com.syan.spark.app.sdk.connect.SparkConnectException;
import cn.com.syan.spark.app.sdk.connect.api.OpenID;
import cn.com.syan.spark.app.sdk.connect.api.auth.UserExtension;
import cn.com.syan.spark.app.sdk.connect.api.auth.UserInfo;
import cn.com.syan.spark.app.sdk.connect.javabeans.AccessToken;
import cn.com.syan.spark.app.sdk.connect.javabeans.auth.UserExtensionBean;
import cn.com.syan.spark.app.sdk.connect.javabeans.auth.UserInfoBean;
import cn.com.syan.spark.app.sdk.connect.oauth.Oauth;

import javax.servlet.RequestDispatcher;
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
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = "";
            String openID = "";
            Long tokenExceptionIn = 0L;
            if(accessTokenObj.getAccessToken().equals("")){
                System.out.println("没有获取到token");
            }else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExceptionIn = accessTokenObj.getExpireIn();
            }
            //返回accessToken
            request.getSession().setAttribute("accessToken",accessToken);
            request.getSession().setAttribute("tokenExceptionIn",String.valueOf(tokenExceptionIn));
            //返回OpenID
            OpenID openIDObj = new OpenID(accessToken) ;
            String openid = openIDObj.getUserOpenID();
            request.getSession().setAttribute("openID",openid);
            //返回用户扩展项
            UserInfo userInfo =new UserInfo(accessToken,openid);
            UserInfoBean userInfoBean = userInfo.getUserInfo();

            UserExtension userExtension = new UserExtension(accessToken,openid);
            UserExtensionBean userExtensionBean = userExtension.getUserExtension();

            if(openid==null){

            }else {
                response.sendRedirect("/WebDemo/testpage/SparkCallback.jsp");
            }
        } catch (SparkConnectException e) {
            System.out.println("获取accessToken出错");
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    public void init(){
        System.out.println("test servlet");
    }
}
