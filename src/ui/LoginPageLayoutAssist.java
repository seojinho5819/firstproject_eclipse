package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import login_regist.Mailing;

public class LoginPageLayoutAssist implements LayoutAssist {

	public static final int WIDTH = 450;
	public static final int HEIGHT = 630;

	public void setLayout(Page p) {
		LoginPage page = (LoginPage) p;

		JFrame frame = page.frame;
		frame.setResizable(false);
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(panel);

		JPanel n_panel = new JPanel();
		n_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		n_panel.setBackground(Color.WHITE);
		n_panel.setPreferredSize(new Dimension(WIDTH, 50));

		panel.add(n_panel, BorderLayout.NORTH);

		JPanel c_panel = new JPanel();
		c_panel.setOpaque(false);
		c_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		c_panel.setBackground(SystemColor.controlLtHighlight);
		panel.add(c_panel, BorderLayout.CENTER);
		c_panel.setLayout(null);

		JTextField t_email = page.t_email;
		t_email.setFont(new Font("Arial Black", Font.BOLD, 21));
		t_email.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		t_email.setBounds(101, 232, 231, 48);
		c_panel.add(t_email);
		t_email.setColumns(10);

		JLabel email_label = new JLabel("E-Mail");
		email_label.setFont(new Font("Arial Black", Font.BOLD, 22));
		email_label.setBounds(180, 200, 191, 32);
		c_panel.add(email_label);

		JLabel pass_label = new JLabel("PASSWORD");
		pass_label.setFont(new Font("Arial Black", Font.BOLD, 22));
		pass_label.setBounds(141, 290, 191, 22);
		c_panel.add(pass_label);

		JCheckBox check_isSetId = page.check_isSetId;
		check_isSetId.setMargin(new Insets(0, 0, 0, 0));
		check_isSetId.setBackground(SystemColor.controlLtHighlight);
		check_isSetId.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		check_isSetId.setBounds(99, 372, 112, 23);
		c_panel.add(check_isSetId);

		JButton bt_login = page.bt_login;
		bt_login.setBackground(new Color(0, 153, 51));
		bt_login.setForeground(new Color(255, 255, 255));
		bt_login.setFont(new Font("HY견고딕", Font.BOLD, 20));

		bt_login.setBorder(new LineBorder(new Color(0, 153, 51), 5, true));
		bt_login.setBounds(101, 407, 231, 32);
		c_panel.add(bt_login);

		JLabel signinLabel = page.signinLabel;
		signinLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signinLabel.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		signinLabel.setBounds(261, 451, 110, 22);
		c_panel.add(signinLabel);

		JLabel findPass = page.findPass;
		findPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		findPass.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		findPass.setBounds(235, 371, 110, 22);
		c_panel.add(findPass);

		JPanel p_findPass = page.p_findPass;
		p_findPass.setBounds(100, 0, 218, 141);
		p_findPass.setBackground(new Color(255, 255, 255));
		c_panel.add(p_findPass);
		p_findPass.setLayout(null);
		p_findPass.setVisible(false);

		JTextField t_askEmail = page.t_askEmail;
		t_askEmail.setBounds(12, 71, 194, 27);
		p_findPass.add(t_askEmail);
		t_askEmail.setColumns(10);

		JLabel Label_askEmail = new JLabel("이메일 주소 입력하세요.");
		Label_askEmail.setBounds(12, 40, 150, 27);
		p_findPass.add(Label_askEmail);

		JButton bt_askEmail = page.bt_askEmail;
		bt_askEmail.setBounds(115, 108, 91, 23);
		p_findPass.add(bt_askEmail);

		JLabel BigLabel = new JLabel("로그인");
		BigLabel.setForeground(SystemColor.controlDkShadow);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setFont(new Font("HY견고딕", Font.PLAIN, 40));
		BigLabel.setBounds(98, 49, 234, 92);
		c_panel.add(BigLabel);

		JPasswordField t_password = page.t_password;
		t_password.setEchoChar('*');
		t_password.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		t_password.setBounds(101, 318, 231, 48);
		c_panel.add(t_password);

		JPanel opaqPanel = page.opaqPanel;
		opaqPanel.setVisible(false);
		opaqPanel.setBackground(SystemColor.controlShadow);
		opaqPanel.setBounds(0, 0, 446, 512);
		c_panel.add(opaqPanel);
		opaqPanel.setLayout(null);
	}
}
