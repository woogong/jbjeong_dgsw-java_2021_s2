package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/play")
public class PlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = null;
		
		// 로그인 여부 검사
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			
			if ("dgsw_cookie".equals(name)) {
				id = cookie.getValue();
				break;
			}
		}
		
		String msg = null;
		if (id == null) {
			msg = "권한이 없습니다.";
		} else {
			msg = "당신의 아이디는 '" + id + "'입니다";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append(msg);
	}


}
