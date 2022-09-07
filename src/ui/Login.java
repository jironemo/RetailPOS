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
		UIManager.setLookAndFeel(UIManager.createLookAndFeel("Nimbus"));

	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		frmLogin = new JFrame();
		frmLogin.setUndecorated(true);
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 623, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to AKS POS System");
		lblNewLabel.setBackground(SystemColor.window);
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(73, 23, 483, 80);
		frmLogin.getContentPane().add(lblNewLabel);
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setBounds(345, 144, 237, 42);
		frmLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (LoginController.handleLogin(txtUser.getText(),new String(txtPass.getPassword()))){
					WindowBridge.switchWindowsPermanent(frmLogin, new Dashboard());
				}
			}
		});
	
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(470, 300, 115, 42);
		frmLogin.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
			}
		});
		
		btnCancel.setForeground(SystemColor.desktop);
		btnCancel.setBackground(SystemColor.control);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(345, 300, 115, 42);
		frmLogin.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1.setBounds(342, 114, 89, 33);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(345, 203, 121, 33);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPass.setBounds(345, 236, 237, 42);
		frmLogin.getContentPane().add(txtPass);
		
		JLabel icon = new JLabel("");
		icon.setIconTextGap(0);
		icon.setIcon(new ImageIcon(Login.class.getResource("/resources/boy.png")));
		icon.setBounds(45, 93, 290, 249);
		frmLogin.getContentPane().add(icon);
	}
}
