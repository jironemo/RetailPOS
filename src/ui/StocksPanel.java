package ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import controllers.StocksController;
import utilities.NonEditTableModel;
import utilities.Populator;
import utilities.RowTable;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class StocksPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCode,txtName,txtStock,txtPrice,txtDiscount, txtUnit;
	private JLabel lblCode,lblEditStockDetails ,lblProductName,lblStock,lblUnitPrice,lblCategory,lblDiscount,lblUnit,lblNewLabel,lblChooseLanguage;
	public static RowTable table;
	private JComboBox<String> txtCategory;
	private JPanel panel,contentPane;
	private JButton btnCancel,btnDeleteData,btnOk,btnAddStock,btnCheck;
	private JScrollPane scrollPane;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public StocksPanel() {
		setLayout(null);
		setSize(1050,596);
		instantiateContentPane();
		instantiatePanel();
		instantiateLabels();
		instantiateTextFields();
		instantiateButtons();
		instantiateTable();
		addEventListeners();
		add(contentPane);
		
		lblNewLabel = new JLabel("Enter Name or Product Code to Check Stock:");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 593, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 39, 262, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Search Result:\n";
				List<String> items = StocksController.checkStock(textField.getText());
				int i = 1;
				for(String k : items) {
					message+= "Item"+ i +": " + k+"\n\n";
					i++;
				}
				JOptionPane.showMessageDialog(null,message);
			}
		});
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		btnCheck.setBackground(new Color(255, 99, 71));
		btnCheck.setBounds(281, 37, 91, 38);
		contentPane.add(btnCheck);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				buttonText(comboBox.getSelectedIndex());
			}
		});
		comboBox.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"English", "မြန်မာ"}));
		comboBox.setBounds(834, 11, 206, 39);
		contentPane.add(comboBox);
		
		lblChooseLanguage = new JLabel("Choose Language");
		lblChooseLanguage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseLanguage.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblChooseLanguage.setBounds(706, 18, 118, 24);
		contentPane.add(lblChooseLanguage);
		

	}
	
	

	protected void buttonText(int selectedIndex) {
		// TODO Auto-generated method stub
		if(selectedIndex == 0) {
			btnCancel.setText("Cancel");
			btnDeleteData.setText("Delete");
			btnOk.setText("OK");
			btnAddStock.setText("Add New Product");
			btnCheck.setText("Check");
			lblChooseLanguage.setText("Choose Language");
			lblNewLabel.setText("Enter Product Name or Product Code");
			lblCode.setText("Product Code"); 
			lblStock.setText("Stock");
			lblProductName.setText("Product Name");
			lblUnitPrice.setText("Unit Price");
			lblCategory.setText("Category");
			lblUnit.setText("Unit");
			lblDiscount.setText("Discount");
			lblEditStockDetails.setText("Edit Stock Details");
		}else if(selectedIndex == 1) {
			btnCancel.setText("ထွက်");
			btnDeleteData.setText("ဖျက်မည်");
			btnOk.setText("အိုကေ");
			btnAddStock.setText("ပစ္စည်းအသစ်ထည့်မည်");
			btnCheck.setText("ရှာမည်");
			lblChooseLanguage.setText("ဘာသာစကား");
			lblNewLabel.setText("ပစ္စည်းအမည် (သို့) ပစ္စည်းအမှတ် (barcode) ထည့်၍ စာရင်းစစ်ရန်");
			lblCode.setText("ပစ္စည်းအမှတ်"); 
			lblStock.setText("ပစ္စည်းလက်ကျန်");
			lblProductName.setText("ပစ္စည်းအမည်");
			lblUnitPrice.setText("တခုချင်းစျေးနှုန်း");
			lblCategory.setText("ပစ္စည်းအမျိုးအစား");
			lblUnit.setText("ပမာဏ");
			lblDiscount.setText("လျှော့စျေး");
			lblEditStockDetails.setText("ပစ္စည်းအချက်အလက်များပြင်ဆင်ရန်");
			lblEditStockDetails.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		}
	}



	private void addEventListeners() {
		Component[] textfields = {txtCode,txtName,txtUnit,txtCategory,txtStock,txtPrice,txtDiscount};
		JLabel[] labels = {lblCode,lblProductName,lblUnit,lblCategory,lblStock,lblUnitPrice,lblDiscount};
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StocksController.setTextBoxesNull(textfields);
			}
		});
		btnDeleteData.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				StocksController.deleteData(table, textfields);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StocksController.updateData(labels, textfields);
				table.setModel(StocksController.getData(table));
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				StocksController.getStocks(table,textfields);
			}
		});
		
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame wrapper = new JFrame();
				wrapper.getContentPane().add(new AddStock());
				wrapper.setSize(panel.getSize());
				wrapper.setVisible(true);
				wrapper.setLocationRelativeTo(null);
			}
		});
		
	}

	private void instantiateContentPane() {
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 1050, 596);
		contentPane.setPreferredSize(new Dimension(1050, 596));
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setLayout(null);
	}

	private void instantiatePanel() {
		panel = new JPanel();
		panel.setBounds(577, 83, 463, 502);
		panel.setLayout(null);
		contentPane.add(panel);
		
	}

	private void instantiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 83, 557, 502);
		contentPane.add(scrollPane);
	}

	@SuppressWarnings("static-access")
	private void instantiateTable() {
		instantiateScrollPane();

		table = new RowTable();
		table.setSelectionMode(table.getSelectionModel().SINGLE_SELECTION);
		NonEditTableModel d = (NonEditTableModel) StocksController.getData(table);
		table.setModel(d);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

		
	}

	private void instantiateButtons() {
		btnCancel = new JButton("Cancel");
		
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(129, 435, 89, 36);
		panel.add(btnCancel);
		
		btnOk = new JButton("OK");
		
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(315, 435, 89, 38);
		panel.add(btnOk);
		
		
		btnDeleteData = new JButton("Delete");
		btnDeleteData.setForeground(Color.WHITE);
		btnDeleteData.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		btnDeleteData.setBackground(Color.ORANGE);
		btnDeleteData.setBounds(229, 435, 80, 37);
		panel.add(btnDeleteData);
		
		btnAddStock = new JButton("Add New Product");
		
		btnAddStock.setForeground(Color.WHITE);
		btnAddStock.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		btnAddStock.setBackground(Color.GREEN);
		btnAddStock.setBounds(383, 37, 184, 38);
		contentPane.add(btnAddStock);
	}

	private void instantiateLabels() {
		
		lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStockDetails.setFont(new Font("Myanmar Text", Font.PLAIN, 30));
		lblEditStockDetails.setBounds(0, 11, 463, 51);
		panel.add(lblEditStockDetails);
		
		lblCode = new JLabel("Product Code");
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCode.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCode.setBounds(44, 81, 165, 33);
		panel.add(lblCode);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblProductName.setBounds(38, 130, 171, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCategory.setBounds(65, 233, 144, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblStock.setBounds(65, 286, 144, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnitPrice.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnitPrice.setBounds(82, 339, 127, 33);
		panel.add(lblUnitPrice);
		
		JLabel lblper = new JLabel("%");
		lblper.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblper.setBounds(385, 391, 19, 33);
		panel.add(lblper);
	
		lblUnit = new JLabel("Unit");
		lblUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnit.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnit.setBounds(129, 183, 80, 33);
		panel.add(lblUnit);

		lblDiscount = new JLabel("Discount");
		lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscount.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblDiscount.setBounds(129, 391, 80, 33);
		panel.add(lblDiscount);
	}

	private void instantiateTextFields() {
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(219, 69, 185, 42);
		panel.add(txtCode);
		
		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(219, 175, 185, 42);
		panel.add(txtUnit);

		txtName = new JTextField();
		txtName.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(219, 122, 185, 42);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(219, 225, 185, 42);
		Populator.setComboData(txtCategory);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(219, 278, 185, 42);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(219, 331, 185, 42);
		panel.add(txtPrice);

		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(219, 386, 158, 42);
		panel.add(txtDiscount);

	}
}
