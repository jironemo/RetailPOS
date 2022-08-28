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
public class Dashboard extends JFrame  {

	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */


/**
	 * Create the frame.
	 */
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 786);
		mainPanel = new JPanel();
		mainPanel.setSize(1366,786);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);		
		JPanel viewPanel = new JPanel();
		viewPanel.setBounds(279, 71, 1050, 596);
		mainPanel.add(viewPanel);
		viewPanel.setLayout(null);
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setBackground(new Color(255, 204, 0));
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new AddSale(getDashboard()));
			}
		});
		btn_AddSale.setBorderPainted(false);
		btn_AddSale.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setBounds(10, 147, 256, 65);
		mainPanel.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setIcon(null);
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setBackground(new Color(255, 204, 0));
		btn_Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WindowBridge.switchWindowsTemp(getDashboard(), new Stocks(getDashboard()));
				viewPanel.setVisible(false);
				viewPanel.removeAll();
				viewPanel.add(new StocksPanel());
				viewPanel.setVisible(true);
			}
		});

		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_Stock.setBorderPainted(false);
		btn_Stock.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Stock.setBounds(10, 71, 256, 65);
		mainPanel.add(btn_Stock);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(0, 0, 1366, 60);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setIconTextGap(2);
		lblDashboard.setAlignmentY(0.0f);
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashboard.setBounds(0, 0, 400, 60);
		panel.add(lblDashboard);
		lblDashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/resources/dashboard.png")));
		lblDashboard.setFont(new Font("Roboto Black", Font.PLAIN, 50));
		
		JButton btn_ViewSales = new JButton("VIEW SALES");
		btn_ViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_ViewSales.setForeground(Color.WHITE);
		btn_ViewSales.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_ViewSales.setBorderPainted(false);
		btn_ViewSales.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_ViewSales.setBackground(new Color(255, 204, 0));
		btn_ViewSales.setBounds(10, 223, 255, 60);
		mainPanel.add(btn_ViewSales);
		
		setContentPane(mainPanel);
		
		JButton btn_Receipt = new JButton("DISPLAY RECEIPT");
		btn_Receipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getDashboard(), new ReceiptPrint());
			}
		});
		btn_Receipt.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_Receipt.setForeground(Color.WHITE);
		btn_Receipt.setFont(new Font("Tahoma", Font.BOLD, 23));
		btn_Receipt.setBorderPainted(false);
		btn_Receipt.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Receipt.setBackground(new Color(255, 204, 0));
		btn_Receipt.setBounds(10, 294, 255, 60);
		mainPanel.add(btn_Receipt);


	}
	
	public JFrame getDashboard() {
		return this;
	}
}
