package com.project.myapp;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.*;
@WebServlet(name = "AuthCodeServlet")
public class AuthCodeServlet extends HttpServlet {
    private static String APP_KEY ;
    private static String APP_SECRET ;
    public void init() {
        APP_KEY = getServletContext().getInitParameter("APP_KEY");
        APP_SECRET = getServletContext().getInitParameter("APP_SECRET");
        
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authcode = request.getParameter("code");
        String jsontoken = oauthClient.getAuthToken(authcode , APP_KEY , APP_SECRET);
        String uu = (String) request.getSession(true).getAttribute("_auuid");
        if(jsontoken == "error") {
            request.getRequestDispatcher("/error.jsp").forward(request , response);
        }
        Map<String, String> map = new HashMap<String, String>();

        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(jsontoken,
                new TypeReference<HashMap<String, String>>() {
                });
        String authToken = map.get("access_token");
        request.getSession(true).setAttribute("Token", authToken);
        System.out.println(authToken);

        if(authToken != null) {
            request.getRequestDispatcher("/success.jsp").forward(request , response);
        }
    }
}
