package kr.hs.dgsw.java.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.hs.dgsw.java.web.domain.Member;

public class MemberServiceImpl implements MemberService {

	@Override
	public void registerMember(Member member) {
		try {
			// 데이터베이스 연결
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mariadb://211.53.209.159:3306/dgsw2021", 
							"dgsw", "1111");
			
			// SQL 작성
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO member ");
			sql.append("(email, password, name, contact, birthday, ");
			sql.append(" register_time) ");
			sql.append("VALUES ");
			sql.append("(?, ?, ?, ?, ?, ");
			sql.append(" NOW() ) ");
			System.out.println(sql.toString());
			
			// PreparedStatment 생성, 수행
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getContact());
			
			String sBirthday = member.getBirthday();
			SimpleDateFormat dateFormat = 
					new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(sBirthday);
			pstmt.setDate(5, new java.sql.Date(birthday.getTime()));
			
			pstmt.executeUpdate();
			
			// 데이터베이스 연결 종료
			con.close();
			
		} catch (Exception e) {
			throw new RuntimeException("회원 가입 실패", e);
		}
		
	}
	
	public static void main(String[] args) {
		try {
			MemberService memberService = new MemberServiceImpl();
			Member member = new Member();
			member.setEmail("abcd@gmail.zzz");
			member.setPassword("123455");
			member.setName("테스트회원");
			member.setContact("010-0000-1111");
			member.setBirthday("2035-04-25");
			
			memberService.registerMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
