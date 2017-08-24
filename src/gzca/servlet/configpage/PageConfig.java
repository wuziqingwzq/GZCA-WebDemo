package gzca.servlet.configpage;

import gzca.config.PropertiesConfig;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "PageConfig" ,urlPatterns = "/pageconfig")
public class PageConfig extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        PropertiesConfig pc = new PropertiesConfig();
        Map<String,String> configmap= pc.getAllConfig();
        JSON configjson = JSONObject.fromObject(configmap);
        System.out.println(configjson.toString());
        pw.write(configjson.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
