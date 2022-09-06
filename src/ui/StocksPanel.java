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
import javax.swing.JList;
import javax.swing.JOptionPane;

public class StocksPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCode,txtName,txtStock,txtPrice,txtDiscount, txtUnit;
	private JLabel lblCode,lblProductName,lblStock,lblUnitPrice,lblCategory,lblDiscount,lblUnit;
	public static RowTable table;
	private JComboBox<String> txtCategory;
	private JPanel panel,contentPane;
	private JButton btnCancel,btnDeleteData,btnOk,btnAddStock;
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
		
		JLabel lblNewLabel = new JLabel("Enter Name or Product Code to Check Stock:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 281, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 46, 206, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Search Result:\n";
				List<String> items = StocksController.checkStock(textField.getText());
				int i = 1;
				for(String k : items) {
					message+= "Item"+ i +": " + k+"\n";
					i++;
				}
				JOptionPane.showMessageDialog(null,message);
			}
		});
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCheck.setBackground(new Color(255, 99, 71));
		btnCheck.setBounds(230, 46, 129, 38);
		contentPane.add(btnCheck);
		

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
		panel.setBounds(688, 71, 352, 502);
		panel.setLayout(null);
		contentPane.add(panel);
		
	}

	private void instantiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 139, 668, 434);
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
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

		
	}

	private void instantiateButtons() {
		btnCancel = new JButton("Cancel");
		
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(20, 439, 89, 33);
		panel.add(btnCancel);
		
		btnOk = new JButton("OK");
		
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(241, 436, 89, 38);
		panel.add(btnOk);
		
		
		btnDeleteData = new JButton("Delete");
		btnDeleteData.setForeground(Color.WHITE);
		btnDeleteData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteData.setBackground(Color.ORANGE);
		btnDeleteData.setBounds(119, 439, 109, 33);
		panel.add(btnDeleteData);
		
		btnAddStock = new JButton("Add New Product");
		
		btnAddStock.setForeground(Color.WHITE);
		btnAddStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddStock.setBackground(Color.GREEN);
		btnAddStock.setBounds(759, 22, 281, 38);
		contentPane.add(btnAddStock);
	}

	private void instantiateLabels() {
		
		JLabel lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEditStockDetails.setBounds(68, 11, 235, 51);
		panel.add(lblEditStockDetails);
		
		lblCode = new JLabel("Product Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCode.setBounds(26, 73, 109, 33);
		panel.add(lblCode);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(20, 127, 115, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(61, 230, 74, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(90, 283, 45, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnitPrice.setBounds(55, 336, 80, 33);
		panel.add(lblUnitPrice);
		
		JLabel lblper = new JLabel("%");
		lblper.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblper.setBounds(311, 391, 19, 33);
		panel.add(lblper);
	
		lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnit.setBounds(101, 180, 34, 33);
		panel.add(lblUnit);

		lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiscount.setBounds(65, 391, 70, 33);
		panel.add(lblDiscount);
	}

	private void instantiateTextFields() {
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(145, 69, 185, 42);
		panel.add(txtCode);
		
		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(145, 175, 185, 42);
		panel.add(txtUnit);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(145, 122, 185, 42);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(145, 225, 185, 42);
		Populator.setComboData(txtCategory);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(145, 278, 185, 42);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(145, 331, 185, 42);
		panel.add(txtPrice);

		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(145, 386, 158, 42);
		panel.add(txtDiscount);

	}
}
