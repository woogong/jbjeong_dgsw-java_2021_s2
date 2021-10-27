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
			
			// ���̵� �ߺ� ���θ� �˻��Ѵ�.
			if (memberService.isEmailDuplicated(request.getParameter("email"))) {
				String url = "registerFail.jsp?reason=" + URLEncoder.encode("�̹� ��� ���� ���̵��Դϴ�.", "UTF-8");
				
				response.sendRedirect(url);
				return;
			}
			
			// �����ͺ��̽��� ����Ѵ�.
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
