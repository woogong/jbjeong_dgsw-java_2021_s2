package kr.hs.dgsw.java.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import kr.hs.dgsw.java.web.domain.PhoneNumber;

public class PhoneBookServiceImpl implements PhoneBookService {

	public PhoneBookServiceImpl() {
	}

	@Override
	public PhoneNumber create(PhoneNumber phoneNumber) {
		try {
			Connection con = ConnectionManager.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO phonebook ( ");
			sql.append("	name, ");
			sql.append("	phone_number ");
			sql.append(") VALUES ( ");
			sql.append("	?, ");
			sql.append("	? ");
			sql.append(") ");
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phoneNumber.getName());
			pstmt.setString(2, phoneNumber.getPhoneNumber());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement("SELECT MAX(idx) FROM phonebook ");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int idx = rs.getInt(1);
			rs.close();
			pstmt.close();
			
			phoneNumber.setIdx(idx);
			
			con.close();
			
			return phoneNumber;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PhoneNumber read(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PhoneNumber phoneNumber) {
		try {
			Connection con = ConnectionManager.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE phonebook SET  ");
			sql.append("	name = ?,  ");
			sql.append("	phone_number = ?  ");
			sql.append(" WHERE idx = ? ");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phoneNumber.getName());
			pstmt.setString(2, phoneNumber.getPhoneNumber());
			pstmt.setInt(3, phoneNumber.getIdx());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int idx) {
		try {
			Connection con = ConnectionManager.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM phonebook  ");
			sql.append(" WHERE idx = ? ");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, idx);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PhoneNumber> list() {
		try {
			Connection con = ConnectionManager.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("	idx, ");
			sql.append("	name, ");
			sql.append("	phone_number ");
			sql.append("  FROM phonebook");
			sql.append(" ORDER BY idx DESC ");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			List<PhoneNumber> list = new ArrayList<PhoneNumber>();
			while (rs.next()) {
				PhoneNumber item = new PhoneNumber();
				item.setIdx(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setPhoneNumber(rs.getString(3));
				
				list.add(item);
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try {
			PhoneBookService service = new PhoneBookServiceImpl();
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setName("abcd");
			phoneNumber.setPhoneNumber("010-0000-0011");
			
			phoneNumber = service.create(phoneNumber);
			System.out.println(new Gson().toJson(phoneNumber));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
}
