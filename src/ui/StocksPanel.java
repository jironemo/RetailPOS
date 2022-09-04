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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

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
				StocksController.setTextBoxesNull(textfields);
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
		panel.setBounds(688, 43, 352, 530);
		panel.setLayout(null);
		contentPane.add(panel);
		
	}

	private void instantiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 92, 668, 481);
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
		btnCancel.setBounds(16, 473, 89, 33);
		panel.add(btnCancel);
		
		btnOk = new JButton("OK");
		
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(237, 470, 89, 38);
		panel.add(btnOk);
		
		
		btnDeleteData = new JButton("Delete");
		btnDeleteData.setForeground(Color.WHITE);
		btnDeleteData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteData.setBackground(Color.ORANGE);
		btnDeleteData.setBounds(115, 473, 109, 33);
		panel.add(btnDeleteData);
		
		btnAddStock = new JButton("Add Stock");
		
		btnAddStock.setForeground(Color.WHITE);
		btnAddStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddStock.setBackground(Color.GREEN);
		btnAddStock.setBounds(546, 43, 132, 38);
		contentPane.add(btnAddStock);
	}

	private void instantiateLabels() {
		
		JLabel lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEditStockDetails.setBounds(16, 11, 316, 81);
		panel.add(lblEditStockDetails);
		
		lblCode = new JLabel("Product Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCode.setBounds(22, 107, 109, 33);
		panel.add(lblCode);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(16, 161, 115, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(57, 264, 74, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(86, 317, 45, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnitPrice.setBounds(51, 370, 80, 33);
		panel.add(lblUnitPrice);
		
		JLabel lblper = new JLabel("%");
		lblper.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblper.setBounds(307, 425, 19, 33);
		panel.add(lblper);
	
		lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnit.setBounds(97, 214, 34, 33);
		panel.add(lblUnit);

		lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiscount.setBounds(61, 425, 70, 33);
		panel.add(lblDiscount);
	}

	private void instantiateTextFields() {
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(141, 103, 185, 42);
		panel.add(txtCode);
		
		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(141, 209, 185, 42);
		panel.add(txtUnit);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(141, 156, 185, 42);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(141, 259, 185, 42);
		Populator.setComboData(txtCategory);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(141, 312, 185, 42);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(141, 365, 185, 42);
		panel.add(txtPrice);

		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(141, 420, 158, 42);
		panel.add(txtDiscount);

	}
}
