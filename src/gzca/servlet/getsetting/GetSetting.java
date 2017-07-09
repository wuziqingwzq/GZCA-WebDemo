package gzca.servlet.getsetting;

import gzca.config.PropertiesConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuziqing on 2017-7-9.
 */
@WebServlet(name = "GetSetting" ,urlPatterns = "/getSetting")
public class GetSetting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        System.out.println(type);
        PropertiesConfig pc = new PropertiesConfig();
        pc.getConfig("gzcaverify");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
