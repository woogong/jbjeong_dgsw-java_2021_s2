package kr.hs.dgsw.java.web.servlet.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.java.web.domain.Member;
import kr.hs.dgsw.java.web.service.MemberService;
import kr.hs.dgsw.java.web.service.MemberServiceImpl;

@WebServlet("/member/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			MemberService memberService = new MemberServiceImpl();
			
			// 아이디 중복 여부를 검사한다.
			if (memberService.isEmailDuplicated(request.getParameter("email"))) {
				String url = "registerFail.jsp?reason=" + URLEncoder.encode("이미 사용 중인 아이디입니다.", "UTF-8");
				
				response.sendRedirect(url);
				return;
			}
			
			// 데이터베이스에 등록한다.
			Member member = new Member();
			member.setEmail(request.getParameter("email"));
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			member.setContact(request.getParameter("contact"));
			member.setBirthday(request.getParameter("birthday"));
			
			memberService.registerMember(member);
			
			response.sendRedirect("registerSuccess.html");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(URLEncoder.encode("registerFail.jsp?reason=" + e.getMessage(), "UTF-8"));
		}
	}

}
