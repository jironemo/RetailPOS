package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.WindowBridge;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.StocksController;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import java.awt.Dimension;
@SuppressWarnings("serial")
public class Stocks extends JFrame {

	private  JPanel contentPane;
	private JTable table;
	private  JPanel panel;
	private JLabel lblEditStockDetails;
	private JTextField txtCode;
	private JTextField txtName;
	private JComboBox<String> txtCategory;
	private JTextField txtStock;
	private JTextField txtPrice;
	private JLabel lblNewLabel;
	private JLabel lblProductName;
	private JLabel lblCategory;
	private JLabel lblStock;
	private JLabel lblUnitPrice;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Stocks(JFrame prev_frame) {
		setBounds(new Rectangle(0, 0, 1366, 786));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0,0,1816, 933);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1366, 786));

		contentPane.setBackground(new Color(255, 204, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 944, 666);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		DefaultTableModel d = StocksController.getSales();

		table.setModel(d);
		
		panel = new JPanel();
		panel.setBounds(1002, 163, 586, 689);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCode = new JTextField();
		txtCode.setBounds(171, 153, 368, 47);
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		panel.add(txtCode);
		
		lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setBounds(33, 37, 506, 80);
		lblEditStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 66));
		panel.add(lblEditStockDetails);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(171, 232, 368, 47);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setEditable(true);
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		
		lblNewLabel = new JLabel("Product Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(52, 167, 109, 33);
		panel.add(lblNewLabel);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(46, 246, 115, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(87, 328, 74, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(116, 411, 45, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
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
		

		StocksController.setComboData(txtCategory);

		
		JButton btn_Stock = new JButton("Add Item");

		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_Stock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Stock.setBackground(new Color(255, 204, 102));
		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setBounds(10, 12, 205, 71);
		contentPane.add(btn_Stock);
		
		JButton btn_AddSale = new JButton("Add New Category");
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_AddSale.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_AddSale.setBackground(new Color(255, 204, 102));
		btn_AddSale.setBounds(225, 11, 377, 72);
		contentPane.add(btn_AddSale);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				Component[] textfield = {txtCode,txtName,txtCategory,txtStock,txtPrice};
				StocksController.getStocks(table,textfield);
			}
		});
	}


	public  JPanel getPanel() {
		// TODO Auto-generated method stub
		return contentPane;
	}
}
