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
import javax.swing.border.EtchedBorder;
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
		btn_AddSale.setBounds(678, 224, 377, 290);
		contentPane.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setIcon(null);
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setBackground(new Color(255, 204, 0));
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
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDashboard().dispose();
				
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 44));
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnNewButton.setBounds(1860, 0, 60, 60);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(0, 0, 1920, 60);
		contentPane.add(panel);
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
				WindowBridge.switchWindowsTemp(getDashboard(), new ViewSales(getDashboard()));
			}
		});
		btn_ViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_ViewSales.setForeground(Color.WHITE);
		btn_ViewSales.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_ViewSales.setBorderPainted(false);
		btn_ViewSales.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_ViewSales.setBackground(new Color(255, 204, 0));
		btn_ViewSales.setBounds(1103, 224, 377, 290);
		contentPane.add(btn_ViewSales);
		

	}
	
	public JFrame getDashboard() {
		return this;
	}
}
