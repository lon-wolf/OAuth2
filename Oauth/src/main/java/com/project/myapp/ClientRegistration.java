package com.project.myapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ClientRegistration
 */
public class ClientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String APP_KEY, NGROK;
//	@Override
	public void init() throws ServletException {
		APP_KEY = getServletContext().getInitParameter("APP_KEY");
		NGROK = getServletContext().getInitParameter("NGROK");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		oauthClient AuthClient = new oauthClient();
		//String uu = session.getAttribute("_auuid").toString();
		//session.setAttribute("uu1",UUID.randomUUID().toString());
		String uuid=UUID.randomUUID().toString();
		session.setAttribute("_auuid", uuid);
		session.setAttribute("license_code" , request.getParameter("lcode"));
		session.setAttribute("email_id" , request.getParameter("email"));
		String url = AuthClient.getRedirectAuthCodeURL(APP_KEY, request.getParameter("lcode"), NGROK);
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
