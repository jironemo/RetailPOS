package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utilities.DBConnector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class Dashboard extends JFrame  {

	private JPanel mainPanel, viewPanel;

	/**
	 * Launch the application.
	 */


/**
	 * Create the frame.
	 */
	public Dashboard() {
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			DBConnector.closeConnection();
		}
		public void windowClosing(WindowEvent e) {
			DBConnector.closeConnection();
		}
	});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 786);
		mainPanel = new JPanel();
		mainPanel.setSize(1366,786);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);		
		viewPanel = new JPanel();
		viewPanel.setBounds(279, 71, 1050, 596);
		mainPanel.add(viewPanel);
		viewPanel.setLayout(null);
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setBackground(new Color(255, 204, 0));
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddSale();
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
				showStocks();
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
		btn_ViewSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSales();
			}
		});
		btn_ViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_ViewSales.setForeground(Color.WHITE);
		btn_ViewSales.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_ViewSales.setBorderPainted(false);
		btn_ViewSales.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_ViewSales.setBackground(new Color(255, 204, 0));
		btn_ViewSales.setBounds(10, 223, 255, 60);
		mainPanel.add(btn_ViewSales);
		
		setContentPane(mainPanel);
		showStocks();
		

	}
	
	protected void showSales() {
	// TODO Auto-generated method stub
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		viewPanel.add(new ReportView());
		viewPanel.setVisible(true);
}

	protected void showAddSale() {
	// TODO Auto-generated method stub

		viewPanel.setVisible(false);
		viewPanel.removeAll();
		viewPanel.add(new AddSalePanel());
		viewPanel.setVisible(true);
}

	protected void showStocks() {
		// TODO Auto-generated method stub
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		StocksPanel stocksPanel = new StocksPanel();
		stocksPanel.table.setLocation(31, 30);
		viewPanel.add(stocksPanel);
		viewPanel.setVisible(true);
	}

	public JFrame getDashboard() {
		return this;
	}
}
