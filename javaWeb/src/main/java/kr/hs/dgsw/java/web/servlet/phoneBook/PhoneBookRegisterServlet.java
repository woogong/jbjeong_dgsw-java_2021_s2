package kr.hs.dgsw.java.web.servlet.phoneBook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/phoneBook/register.do")
public class PhoneBookRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 저장한다.
		
		System.out.println("register.do  : " + request.getParameter("name"));
		
		//response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().append("{'resultCode': 'OK'}");
		
		//response.getWriter().close();
	}
	

}
