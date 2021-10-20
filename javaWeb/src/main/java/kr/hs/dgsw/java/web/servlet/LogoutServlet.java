package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.java.web.service.CookieSessionManager;
import kr.hs.dgsw.java.web.service.SessionManager;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		SessionManager sessionManager = new CookieSessionManager();
		sessionManager.doLogout(request, response);
		
		response.sendRedirect("login.jsp");
	}

}
