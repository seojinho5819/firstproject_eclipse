package login_regist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.RegistMemberVO;

//DB 처리
public class DBManager extends JFrame {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";

	private static final String user = "user1104"; // DB ID
	private static final String pass = "1234"; // DB 패스워드

	// 로그인 상태 여부를 보관할 변수
	private boolean hasSession = false;// 세션을 보유하고 있는지...

	RegistForm registForm;
	RegistMemberVO registMemberVO;
	private Connection con;

	public DBManager() {
		this.getConnection();
	}

	// DB 랑 UI 랑 분리 Code 나 Message 를 return 하는걸 추천
	/** DB연결 메소드 */
	public void getConnection() {
		try {
			Class.forName(driver);// 드라이버 로드
			con = DriverManager.getConnection(url, user, pass); // 접속시도 후, 객체 반환
			if (con == null) { // 접속실패인경우 메시지 출력
//				JOptionPane.showMessageDialog(this, "데이터베이스에 접속하지 못했습니다.");
			} else {// 접속 성공의 경우 윈도우 제목창에 현재 접속자 출력
				this.setTitle(user + " 접속 중");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void disConnection() {
		// null 이 아닐때만 닫아야 함, 만일 이런 확인절차를 거치지 않으면 NullPointerException 발생할수있슴
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
