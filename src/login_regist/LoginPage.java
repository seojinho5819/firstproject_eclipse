package login_regist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class LoginPage extends JPanel {
   
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "user1104"; // DB ID
	private static final String pass = "1234"; // DB 패스워드
	DBManager dbManager;
	Connection con;

	
   public static final int WIDTH = 450;
   public static final int HEIGHT = 630;
   

   private JFrame frame;
   private JTextField t_id;
   private JTextField t_askEmail;
   JPanel p_findPass;
   JPanel opaqPanel;
   private JPasswordField t_password;
   private JCheckBox check_isSetEmail;
  
   RegistForm registForm = new RegistForm();


   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				  
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {

		initialize(dbManager);
	}

	public void loginShow() {
		frame.setVisible(true);
	}

	public void loginHide() {
		frame.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DBManager dbManager) {
		this.dbManager = dbManager;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
      
      
      JPanel panel = new JPanel();
      frame.getContentPane().add(panel);
      panel.setLayout(new BorderLayout(0, 0));
      
      JPanel n_panel = new JPanel();
      n_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      n_panel.setBackground(Color.WHITE);
      n_panel.setPreferredSize(new Dimension(WIDTH, 50));
      
      panel.add(n_panel, BorderLayout.NORTH);
      
      JPanel c_panel = new JPanel(); /*{
         protected void paintComponent(Graphics g) {
         ImageIcon icon = new ImageIcon("C:/workspace/Java_workspace/TeamProject/src/res/bg_test.jpg");
         Image img = icon.getImage();
         img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
         g.drawImage(img, 0, 0, this);
         setOpaque(false);
         super.paintComponent(g);
         }
      };
       */
      c_panel.setOpaque(false);
      c_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
      c_panel.setBackground(SystemColor.controlLtHighlight);
      panel.add(c_panel, BorderLayout.CENTER);
      c_panel.setLayout(null);
      
      t_id = new JTextField(30);
      t_id.setFont(new Font("Arial Black", Font.BOLD, 21));
      t_id.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      t_id.setBounds(101, 232, 231, 48);
      c_panel.add(t_id);
      t_id.setColumns(10);
      
      JLabel id_label = new JLabel("ID");
      id_label.setFont(new Font("Arial Black", Font.BOLD, 22));
      id_label.setBounds(198, 200, 191, 32);
      c_panel.add(id_label);
      
      JLabel pass_label = new JLabel("PASSWORD");
      pass_label.setFont(new Font("Arial Black", Font.BOLD, 22));
      pass_label.setBounds(141, 290, 191, 22);
      c_panel.add(pass_label);
      
      JCheckBox check_isSetEmail = new JCheckBox("아이디 저장");
      check_isSetEmail.setMargin(new Insets(0, 0, 0, 0));
      check_isSetEmail.setBackground(SystemColor.controlLtHighlight);
      check_isSetEmail.setFont(new Font("HY견고딕", Font.PLAIN, 15));
      check_isSetEmail.setBounds(99, 372, 112, 23);
      c_panel.add(check_isSetEmail);
      
      JButton bt_login = new JButton("로그인");
      bt_login.setBackground(new Color(0, 153, 51));
      bt_login.setForeground(new Color(255, 255, 255));
      bt_login.setFont(new Font("HY견고딕", Font.BOLD, 20));
      bt_login.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	 login();
         }
      });
      bt_login.setBorder(new LineBorder(new Color(0, 153, 51), 5, true));
      bt_login.setBounds(101, 407, 231, 32);
      c_panel.add(bt_login);
      
      JLabel signinLabel = new JLabel("회원가입");
      signinLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      signinLabel.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      signinLabel.setBounds(261, 451, 110, 22);
      c_panel.add(signinLabel);
      signinLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseReleased(MouseEvent e) {
        	registForm.registShow();
            System.out.println("회원가입");
            loginHide();
           
            
         }
      });
      
      JLabel findPass = new JLabel("비밀번호찾기");
      findPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      findPass.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      findPass.setBounds(235, 371, 110, 22);
      findPass.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseReleased(MouseEvent e) {
            System.out.println("비밀번호찾기");
            p_findPass.setVisible(true);
            opaqPanel.setVisible(true);//메일보내기
            t_id.setEnabled(false);
            t_password.setEnabled(false);
            check_isSetEmail.setEnabled(false);
            bt_login.setEnabled(false);
         }
      });
//      
      c_panel.add(findPass);
      
      p_findPass = new JPanel();
      p_findPass.setBounds(100, 0, 218, 141);
      p_findPass.setBackground(new Color(255,255,255));
      c_panel.add(p_findPass);
      p_findPass.setLayout(null);
      p_findPass.setVisible(false);
      
      t_askEmail = new JTextField(50);
      t_askEmail.setBounds(12, 71, 194, 27);
      p_findPass.add(t_askEmail);
      t_askEmail.setColumns(10);
      
      JLabel Label_askEmail = new JLabel("이메일 주소 입력하세요.");
      Label_askEmail.setBounds(12, 40, 150, 27);
      p_findPass.add(Label_askEmail);
      
      JButton bt_askEmail = new JButton("확인");
      bt_askEmail.setBounds(115, 108, 91, 23);
      p_findPass.add(bt_askEmail);
      
      bt_askEmail.addActionListener((e) -> {
    	  String askEmail = t_askEmail.getText();
    	  Mailing mailing = new Mailing(askEmail);

    	  System.out.println(askEmail);
    	  
    	  mailing.send(askEmail);
         p_findPass.setVisible(false);
         opaqPanel.setVisible(false);
         t_id.setEnabled(true);
         t_password.setEnabled(true);
         check_isSetEmail.setEnabled(true);
         bt_login.setEnabled(true);
       
      });
      
      JLabel BigLabel = new JLabel("로그인");
      BigLabel.setForeground(SystemColor.controlDkShadow);
      BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
      BigLabel.setFont(new Font("HY견고딕", Font.PLAIN, 40));
      BigLabel.setBounds(98, 49, 234, 92);
      c_panel.add(BigLabel);
      
      t_password = new JPasswordField(30);
      t_password.setEchoChar('*');
      t_password.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      t_password.setBounds(101, 318, 231, 48);
      c_panel.add(t_password);
      
      
      opaqPanel = new JPanel();
      opaqPanel.setVisible(false);
      opaqPanel.setBackground(SystemColor.controlShadow);
      opaqPanel.setBounds(0, 0, 446, 512);
      c_panel.add(opaqPanel);
      opaqPanel.setLayout(null);
      
      
   
      
   }
   public void login() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		
		String sql="select * from RegistMember where member_id=? and member_password=?";
		try {
			con = DriverManager.getConnection(url, user, pass);
			pstmt=con.prepareStatement(sql); //쿼리문 준비 
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_password.getPassword()));
			
			rs = pstmt.executeQuery();
			if(rs.next()) { //레코드가 존재한다면...회원인증 성공 
				
				
				JOptionPane.showMessageDialog(this, "인증성공");
				
			}else {
				JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}