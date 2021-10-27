package kr.hs.dgsw.java.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.java.web.service.SessionManager;
import kr.hs.dgsw.java.web.service.SessionManagerMaker;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// ���̵� ��й�ȣ ���� �Ϸ�
		
		SessionManager sessionManager = SessionManagerMaker.make();
		sessionManager.doLogin(request, response, id);
		
		response.sendRedirect("work.jsp");
	}
	*/
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// ���̵� ��й�ȣ ���� �Ϸ�
		
		SessionManager sessionManager = SessionManagerMaker.make();
		sessionManager.doLogin(request, response, id);
		
		response.sendRedirect("work.jsp");
	}

}
