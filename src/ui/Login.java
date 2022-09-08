package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controllers.LoginController;
import utilities.WindowBridge;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
public class Login extends LoginController {

	private JFrame frmLogin;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		UIManager.setLookAndFeel(UIManager.createLookAndFeel("Windows"));

	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(223, 193, 120));
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 623, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblAks = new JLabel("AKS");
		lblAks.setForeground(Color.BLACK);
		lblAks.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblAks.setBackground(Color.WHITE);
		lblAks.setBounds(88, 146, 47, 33);
		frmLogin.getContentPane().add(lblAks);
		
		JLabel lblNewLabel = new JLabel("Welcome to AKS POS System");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(SystemColor.window);
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(46, 31, 526, 55);
		frmLogin.getContentPane().add(lblNewLabel);
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setBounds(345, 150, 237, 42);
		frmLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (LoginController.handleLogin(txtUser.getText(),new String(txtPass.getPassword()))){
					WindowBridge.switchWindowsPermanent(frmLogin, new Dashboard());
				}
			}
		});
	
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(Color.black);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnNewButton.setBounds(345, 300, 237, 42);
		frmLogin.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(342, 114, 101, 33);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(345, 203, 98, 33);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPass.setBounds(345, 235, 237, 42);
		frmLogin.getContentPane().add(txtPass);
		
		JLabel icon = new JLabel("");
		icon.setIconTextGap(0);
		icon.setIcon(new ImageIcon(Login.class.getResource("/resources/boy-removebg-preview.png")));
		icon.setBounds(28, 103, 290, 249);
		frmLogin.getContentPane().add(icon);
	}
}
