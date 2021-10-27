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

@WebServlet("/member/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {
			MemberService memberService = new MemberServiceImpl();

			int memberIdx = Integer.parseInt(request.getParameter("memberIdx"));
			
			// 데이터베이스에 등록한다.
			Member member = new Member();
			member.setName(request.getParameter("name"));
			member.setContact(request.getParameter("contact"));
			member.setBirthday(request.getParameter("birthday"));
			member.setMemberIdx(memberIdx);
			
			memberService.updateMember(member);
			
			response.sendRedirect("updateSuccess.html");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(URLEncoder.encode("updateFail.jsp?reason=" + e.getMessage(), "UTF-8"));
		}
	}

}
