package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import utilities.DBConnector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
@SuppressWarnings("serial")
public class Dashboard extends JFrame  {

	private JPanel mainPanel, viewPanel;
	JComboBox<String> comboBox;
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
		mainPanel.setBackground(new Color(252, 225, 59));
		mainPanel.setLayout(null);		
		viewPanel = new JPanel();
		viewPanel.setBounds(279, 71, 1050, 596);
		mainPanel.add(viewPanel);
		viewPanel.setLayout(null);
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AddSale.setForeground(new Color(255, 255, 255));
		btn_AddSale.setBackground(new Color(204, 153, 0));
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddSale();
			}
		});
		btn_AddSale.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 30));
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setBounds(10, 147, 256, 65);
		mainPanel.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Stock.setIcon(null);
		btn_Stock.setForeground(new Color(255, 255, 255));
		btn_Stock.setBackground(new Color(204, 153, 0));
		btn_Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WindowBridge.switchWindowsTemp(getDashboard(), new Stocks(getDashboard()));
				showStocks();
			}
		});

		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 30));
		btn_Stock.setBounds(10, 71, 256, 65);
		mainPanel.add(btn_Stock);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(252, 225, 59));
		panel.setBounds(0, 0, 1366, 60);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setBackground(new Color(255, 255, 255));
		lblDashboard.setIconTextGap(2);
		lblDashboard.setAlignmentY(0.0f);
		lblDashboard.setForeground(new Color(255, 153, 0));
		lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashboard.setBounds(0, 0, 319, 60);
		panel.add(lblDashboard);
		lblDashboard.setIcon(null);
		lblDashboard.setFont(new Font("Roboto Black", Font.PLAIN, 50));

		comboBox = new JComboBox<String>();
		comboBox.addItem("English");
		comboBox.addItem("မြန်မာ");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (viewPanel.getComponent(0).getClass() == StocksPanel.class) {
					((StocksPanel)viewPanel.getComponent(0)).setText(comboBox.getSelectedIndex());
				}
				if(viewPanel.getComponent(0).getClass() == AddSalePanel.class) {
					((AddSalePanel)viewPanel.getComponent(0)).setText(comboBox.getSelectedIndex());
					((AddSalePanel)viewPanel.getComponent(0)).updatelanguage(comboBox.getSelectedIndex());
				}
			}
		});
		comboBox.setOpaque(false);
		comboBox.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		comboBox.setBackground(new Color(51, 205, 225));
		comboBox.setBounds(1129, 6, 206, 39);
		panel.add(comboBox);
		
		JLabel lblChooseLanguage = new JLabel("Choose Language");
		lblChooseLanguage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblChooseLanguage.setVerticalAlignment(SwingConstants.TOP);
		lblChooseLanguage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseLanguage.setForeground(new Color(47, 79, 79));
		lblChooseLanguage.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblChooseLanguage.setBackground(new Color(112, 128, 144));
		lblChooseLanguage.setBounds(872, 13, 247, 24);
		panel.add(lblChooseLanguage);
		
		JButton btn_ViewSales = new JButton("VIEW SALES");
		btn_ViewSales.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ViewSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSales();
			}
		});
		btn_ViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_ViewSales.setForeground(new Color(255, 255, 255));
		btn_ViewSales.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 30));
		btn_ViewSales.setBackground(new Color(204, 153, 0));
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
		viewPanel.add(new AddSalePanel(comboBox.getSelectedIndex()));
		viewPanel.setVisible(true);
}

	protected void showStocks() {
		// TODO Auto-generated method stub
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		StocksPanel stocksPanel = new StocksPanel(comboBox.getSelectedIndex());
		viewPanel.add(stocksPanel);
		viewPanel.setVisible(true);
	}

	public JFrame getDashboard() {
		return this;
	}
}
