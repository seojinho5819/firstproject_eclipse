package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.RegistMemberVO;

public class DBManager {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";

	private static final String user = "user1124"; // DB ID
	private static final String pass = "1234"; // DB 패스워드
	
	public RegistMemberVO loginUser(String email, String password) {
		String sql = "select * from RegistMember where member_email=? and member_password=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		RegistMemberVO registMemberVO = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			pstmt = con.prepareStatement(sql); // 쿼리문 준비
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				registMemberVO = new RegistMemberVO();// 빈 멤버객체
				registMemberVO.setMember_no(rs.getInt("member_no"));// pk
				registMemberVO.setMember_name(rs.getString("member_name"));
				registMemberVO.setMember_email(rs.getString("member_email"));
				registMemberVO.setMember_id(rs.getString("member_id"));
				registMemberVO.setMember_password(rs.getString("member_password"));
				registMemberVO.setMember_rank(rs.getString("member_rank"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return registMemberVO;
	}
}
