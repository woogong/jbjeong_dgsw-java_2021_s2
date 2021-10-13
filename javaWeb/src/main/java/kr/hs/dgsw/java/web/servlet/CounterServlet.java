package kr.hs.dgsw.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.java.web.repository.CounterRepository;
import kr.hs.dgsw.java.web.repository.CounterRepositoryWithFile;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CounterRepository repository = new CounterRepositoryWithFile();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		repository.addCount();
		int count = repository.getCount();

		response.setCharacterEncoding("EUC-KR");
		response.getWriter().append("방문자 : ").append(count + "명");
	}

}
