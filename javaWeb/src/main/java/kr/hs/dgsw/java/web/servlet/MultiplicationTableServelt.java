package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class MultiplicationTableServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=euc-kr");

		String sDan = request.getParameter("dan");
		String result;
		try {
			int dan = Integer.parseInt(sDan);

			result = makeTable(dan);
			
		} catch (Exception e) {
			result = "잘못된 입력값입니다. 멍충아.";
		}

		response.getWriter().append(result);
	}

	private String makeTable(int dan) {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("<h1>구구단</h1>");
		buffer.append("<h3>").append(dan).append("단</h3>");
		
		for (int i = 1 ; i<= 9 ; i++) {
			buffer.append(dan).append(" * ").append(i)
				.append(" = ").append(dan * i).append("<br>");
		}
		
		return buffer.toString();
	}

}
