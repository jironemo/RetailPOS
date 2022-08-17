package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utilities.WindowBridge;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
@SuppressWarnings("serial")
public class Dashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


/**
	 * Create the frame.
	 */
	public Dashboard() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setSize(1920,1080);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setBackground(new Color(0, 139, 139));
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new AddSale(getDashboard()));
			}
		});
		btn_AddSale.setBorderPainted(false);
		btn_AddSale.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setBounds(678, 224, 377, 290);
		contentPane.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setIcon(new ImageIcon(Dashboard.class.getResource("/resources/search.png")));
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setBackground(new Color(0, 139, 139));
		btn_Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new Stocks(getDashboard()));
			}
		});

		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_Stock.setBorderPainted(false);
		btn_Stock.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Stock.setBounds(245, 225, 387, 289);
		contentPane.add(btn_Stock);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/resources/dashboard.png")));
		lblDashboard.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblDashboard.setBounds(436, 52, 396, 80);
		contentPane.add(lblDashboard);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDashboard().dispose();
				
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 44));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(1860, 0, 60, 60);
		contentPane.add(btnNewButton);
		

	}
	
	public JFrame getDashboard() {
		return this;
	}
}
