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
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import controllers.StocksController;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StocksPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtStock;
	private JTextField txtPrice;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public StocksPanel() {
		setLayout(null);
		setSize(1366,786);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 5, 1366, 781);
		contentPane.setPreferredSize(new Dimension(1366, 786));
		contentPane.setBackground(new Color(255, 204, 0));
		add(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(748, 81, 586, 689);
		panel.setLayout(null);
		contentPane.add(panel);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(171, 153, 368, 47);
		panel.add(txtCode);
		
		JLabel lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 66));
		lblEditStockDetails.setBounds(33, 37, 506, 80);
		panel.add(lblEditStockDetails);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(171, 232, 368, 47);
		panel.add(txtName);
		
		JComboBox<String> txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(171, 314, 368, 47);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(171, 397, 368, 47);
		panel.add(txtStock);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(171, 486, 368, 47);
		panel.add(txtPrice);
		
		JLabel lblNewLabel = new JLabel("Product Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(52, 167, 109, 33);
		panel.add(lblNewLabel);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(46, 246, 115, 33);
		panel.add(lblProductName);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(87, 328, 74, 33);
		panel.add(lblCategory);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(116, 411, 45, 33);
		panel.add(lblStock);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnitPrice.setBounds(81, 500, 80, 33);
		panel.add(lblUnitPrice);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(171, 590, 115, 42);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(424, 590, 115, 42);
		panel.add(btnOk);
		
		JButton btnDeleteData = new JButton("Delete");
		btnDeleteData.setForeground(Color.WHITE);
		btnDeleteData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteData.setBackground(Color.ORANGE);
		btnDeleteData.setBounds(299, 590, 115, 42);
		panel.add(btnDeleteData);
		
		JButton btn_AddCategory = new JButton("Add New Category");
		btn_AddCategory.setBounds(1044, 11, 259, 51);
		btn_AddCategory.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddCategory.setForeground(Color.WHITE);
		btn_AddCategory.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_AddCategory.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_AddCategory.setBackground(new Color(255, 204, 102));
		contentPane.add(btn_AddCategory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 728, 759);
		contentPane.add(scrollPane);

		JButton btn_AddSale = new JButton("Add New Category");
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_AddSale.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_AddSale.setBackground(new Color(255, 204, 102));
		btn_AddSale.setBounds(748, 11, 259, 51);
		contentPane.add(btn_AddSale);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Component[] textfield = {txtCode,txtName,txtCategory,txtStock,txtPrice};
				StocksController.getStocks(table,textfield);
			}
		});
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		
		DefaultTableModel d = StocksController.getSales();

		table.setModel(d);

		StocksController.setComboData(txtCategory);

	}
}
