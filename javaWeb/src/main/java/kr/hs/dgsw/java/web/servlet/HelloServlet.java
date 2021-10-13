package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		SimpleDateFormat format = 
				new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ HH:mm:ss");
		
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		
		
		response.setCharacterEncoding("EUC-KR");
		
		response.getWriter()
			.append("<b>Hello<b> ")
			.append(name + "  ")
			.append(format.format(date));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
