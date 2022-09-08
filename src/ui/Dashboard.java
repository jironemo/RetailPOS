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
import javax.swing.ImageIcon;
@SuppressWarnings("serial")
public class Dashboard extends JFrame  {

	private JPanel mainPanel, viewPanel;
	JComboBox<String> comboBox;
	JLabel lblChooseLanguage,lblTitle;
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
		mainPanel.setBackground(new Color(255, 205, 130));
		mainPanel.setLayout(null);		
		viewPanel = new JPanel();
		viewPanel.setOpaque(false);
		viewPanel.setBounds(290, 74, 1050, 596);
		mainPanel.add(viewPanel);
		viewPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 237, 191));
		panel.setBounds(0, 0, 283, 747);
		mainPanel.add(panel);
		JButton btn_AddSale = new JButton("ADD SALE");
		btn_AddSale.setBorderPainted(false);
		btn_AddSale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AddSale.setForeground(Color.BLACK);
		btn_AddSale.setBackground(new Color(255, 205, 130));
		btn_AddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddSale();
			}
		});
		btn_AddSale.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 20));
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setBounds(50, 488, 196, 45);
		panel.add(btn_AddSale);
		
		JButton btn_Stock = new JButton("VIEW STOCKS");
		btn_Stock.setBorderPainted(false);
		btn_Stock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Stock.setIcon(null);
		btn_Stock.setForeground(Color.BLACK);
		btn_Stock.setBackground(new Color(255, 205, 130));
		btn_Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStocks();
			}
		});
		lblTitle = new JLabel("VIEW STOCKS");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 73, 273, 54);
		panel.add(lblTitle);
		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 20));
		btn_Stock.setBounds(50, 432, 196, 45);
		panel.add(btn_Stock);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.BLACK);
		comboBox.addItem("English");
		comboBox.addItem("မြန်မာ");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int lan = comboBox.getSelectedIndex();
				if(lan == 0)
					lblChooseLanguage.setText("Choose Language");
				else lblChooseLanguage.setText("ဘာသာစကား‌‌ရွေးရန်");
				if (viewPanel.getComponent(0).getClass() == StocksPanel.class) {
					((StocksPanel)viewPanel.getComponent(0)).setText(lan);
				}
				if(viewPanel.getComponent(0).getClass() == AddSalePanel.class) {
					((AddSalePanel)viewPanel.getComponent(0)).setText(lan);
					((AddSalePanel)viewPanel.getComponent(0)).updatelanguage(lan);
				}
			}
		});
		comboBox.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(1143, 24, 197, 39);
		mainPanel.add(comboBox);
		
		lblChooseLanguage = new JLabel("Choose Language");
		lblChooseLanguage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblChooseLanguage.setVerticalAlignment(SwingConstants.TOP);
		lblChooseLanguage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseLanguage.setForeground(new Color(47, 79, 79));
		lblChooseLanguage.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblChooseLanguage.setBackground(new Color(112, 128, 144));
		lblChooseLanguage.setBounds(883, 31, 247, 24);
		mainPanel.add(lblChooseLanguage);
		
		JButton btn_ViewSales = new JButton("VIEW SALES");
		btn_ViewSales.setBorderPainted(false);
		btn_ViewSales.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ViewSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSales();
			}
		});
		btn_ViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_ViewSales.setForeground(Color.BLACK);
		btn_ViewSales.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 20));
		btn_ViewSales.setBackground(new Color(255, 205, 130));
		btn_ViewSales.setBounds(50, 544, 196, 45);
		panel.add(btn_ViewSales);
		
		setContentPane(mainPanel);
		
		
		
		
		panel.setLayout(null);
		showStocks();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/resources/boy-removebg-preview.png")));
		lblNewLabel.setBounds(0, 127, 280, 280);
		panel.add(lblNewLabel);
		
		

	}
	
	protected void showSales() {
	// TODO Auto-generated method stub
		lblTitle.setText("View Sales");
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		viewPanel.add(new ReportView());
		viewPanel.setVisible(true);
}

	protected void showAddSale() {
	// TODO Auto-generated method stub

		lblTitle.setText("Add Sale");
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		viewPanel.add(new AddSalePanel(comboBox.getSelectedIndex()));
		viewPanel.setVisible(true);
}

	protected void showStocks() {
		// TODO Auto-generated method stub
		lblTitle.setText("View Stocks");
		viewPanel.setVisible(false);
		viewPanel.removeAll();
		StocksPanel stocksPanel = new StocksPanel(comboBox.getSelectedIndex());
		stocksPanel.setLocation(0, 11);
		viewPanel.add(stocksPanel);
		viewPanel.setVisible(true);
	}

	public JFrame getDashboard() {
		return this;
	}
}
