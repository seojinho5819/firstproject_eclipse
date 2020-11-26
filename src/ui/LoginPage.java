package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.DBManager;
import layout.MainApp;
import login_regist.Mailing;
import login_regist.RegistForm;
import models.RegistMemberVO;
import sun.net.www.protocol.file.Handler;

public class LoginPage extends Page {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					LoginPage window = getInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame frame;
	public JTextField t_email;
	public JTextField t_askEmail;
	public JPanel p_findPass;
	public JPanel opaqPanel;
	public JPasswordField t_password;
	public JCheckBox check_isSetId;
	public JButton bt_login;
	public JLabel signinLabel;
	public JLabel findPass;
	public JButton bt_askEmail;

	////////////////////////////
	private RegistForm registForm = new RegistForm();
	////////////////////////////

	private DBManager db;

	private static LoginPage instance = null;

	public static LoginPage getInstance() {
		if (instance == null) {
			instance = new LoginPage();
		}
		return instance;
	}

	private LoginPage() {
		frame = new JFrame();
		t_email = new JTextField(30);
		t_askEmail = new JTextField(50);
		p_findPass = new JPanel();
		opaqPanel = new JPanel();
		t_password = new JPasswordField(30);
		check_isSetId = new JCheckBox("이메일 저장");

		bt_login = new JButton("로그인");
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				requestLogin();
			}
		});

		signinLabel = new JLabel("회원가입");
		signinLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				gotoSignIn();
			}
		});

		findPass = new JLabel("비밀번호찾기");
		findPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("비밀번호찾기");
				gotoPassword();
			}
		});

		bt_askEmail = new JButton("확인");
		bt_askEmail.addActionListener((e) -> {
			String askEmail = t_askEmail.getText();
			sendEmail(askEmail);
			gotoMain();
		});

		db = new DBManager();

		runAssist(new LoginPageLayoutAssist());
	}

	public void loginShow() {
		frame.setVisible(true);
	}

	public void loginHide() {
		frame.setVisible(false);
	}

	private void sendEmail(String content) {
		String askEmail = t_askEmail.getText();
		Mailing mailing = new Mailing(askEmail);

		System.out.println(askEmail);

		mailing.send(askEmail);
	}

	private void gotoPassword() {
		p_findPass.setVisible(true);
		opaqPanel.setVisible(true);// 메일보내기
		t_email.setEnabled(false);
		t_password.setEnabled(false);
		check_isSetId.setEnabled(false);
		bt_login.setEnabled(false);
	}

	private void gotoMain() {
		p_findPass.setVisible(false);
		opaqPanel.setVisible(false);
		t_email.setEnabled(true);
		t_password.setEnabled(true);
		check_isSetId.setEnabled(true);
		bt_login.setEnabled(true);
	}

	private void gotoSignIn() {
		registForm.registShow();
		System.out.println("회원가입");
		loginHide();
	}

	private void requestLogin() {
		String email = t_email.getText();
		String password = new String(t_password.getPassword());

		RegistMemberVO registMemberVO = db.loginUser(email, password);
		if (registMemberVO != null) {
			MainApp app = MainApp.getInstance();
			app.setHasSession(true);
			app.setRegistMemberVO(registMemberVO);

			app.frame.setVisible(true);
			app.la_userName.setText(app.getRegistMemberVO().getMember_id());
			loginHide();
		} else {
			JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");
		}
	}
}
