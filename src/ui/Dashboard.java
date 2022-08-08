package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utilities.WindowBridge;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setSize(1920,1080);
		contentPane.setBackground(new Color(51, 204, 102));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Dashboard");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Arial Black", Font.PLAIN, 57));
		lblTitle.setBounds(796, 40, 338, 69);
		contentPane.add(lblTitle);
		
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setBackground(Color.LIGHT_GRAY);
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new AddSale(getDashboard()));
			}
		});
		btn_AddSale.setBorderPainted(false);
		btn_AddSale.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setBounds(181, 265, 245, 222);
		contentPane.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setBackground(Color.LIGHT_GRAY);
		btn_Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new Stocks(getDashboard()));
			}
		});

		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_Stock.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_Stock.setBorderPainted(false);
		btn_Stock.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Stock.setBounds(492, 265, 245, 222);
		contentPane.add(btn_Stock);
	}
	
	public JFrame getDashboard() {
		return this;
	}

}
