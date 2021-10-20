package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_read")
public class SessionReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		/*
		session.setAttribute("now", new Date());
		session.setAttribute("myName", "Á¤ÀçºÀ");
		session.setAttribute("number", 124);
		*/
		
		session.invalidate();
		session.removeAttribute("id");
		
		Date now = (Date)session.getAttribute("now");
		String name = (String)session.getAttribute("myName");
		int number = (Integer)session.getAttribute("number");
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("now : ").append(now).append("<br>");
		buffer.append("name : ").append(name).append("<br>");
		buffer.append("number : ").append(number).append("<br>");
		
		response.getWriter().append(buffer.toString());
	}


}
