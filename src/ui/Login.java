package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controllers.LoginController;
import utilities.WindowBridge;

import javax.swing.JPasswordField;
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
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(51, 204, 153));
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 550, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 66));
		lblNewLabel.setBounds(162, 36, 190, 80);
		frmLogin.getContentPane().add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setBounds(175, 142, 255, 42);
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
	
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(315, 279, 115, 42);
		frmLogin.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(175, 279, 115, 42);
		frmLogin.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(112, 146, 53, 33);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(81, 215, 78, 33);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPass.setBounds(175, 211, 255, 42);
		frmLogin.getContentPane().add(txtPass);
	}
}
