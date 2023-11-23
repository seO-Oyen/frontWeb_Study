package frontweb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frontweb.vo.Member;

public class A1120Dao {
	/*
	 * member01 테이블 기준(회원관리) 리스트화면, 등록처리, 상세화면, 수정, 삭제
	 * 처리해서 출력
	 * */
	
	public List<Member> getMemberList() {
		List<Member> members = new ArrayList<Member>();
		String sql = "SELECT mno, name, id, pwd, auth, point\r\n"
				+ "FROM MEMBER01";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					members.add(new Member(
							rs.getInt("mno"), 
							rs.getString("name"),
							rs.getString("id"),
							rs.getString("pwd"),
							rs.getString("auth"),
							rs.getInt("point")));
				}
				System.out.println("데이터 건수 : " + members.size());
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		return members;
	}

}