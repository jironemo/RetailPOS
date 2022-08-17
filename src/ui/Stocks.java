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
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.util.List;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
@SuppressWarnings("serial")
public class Stocks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0,0,1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setSize(1920,1080);
		contentPane.setBackground(new Color(51, 204, 102));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 944, 867);
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
		panel.setBounds(1002, 163, 871, 867);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCode = new JTextField();
		txtCode.setBounds(359, 202, 368, 47);
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		panel.add(txtCode);
		
		lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setBounds(221, 86, 506, 80);
		lblEditStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 66));
		panel.add(lblEditStockDetails);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(359, 281, 368, 47);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setEditable(true);
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setBounds(359, 363, 368, 47);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(359, 446, 368, 47);
		panel.add(txtStock);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(359, 535, 368, 47);
		panel.add(txtPrice);
		
		lblNewLabel = new JLabel("Product Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(240, 216, 109, 33);
		panel.add(lblNewLabel);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(234, 295, 115, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(275, 377, 74, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(304, 460, 45, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnitPrice.setBounds(269, 549, 80, 33);
		panel.add(lblUnitPrice);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(359, 639, 115, 42);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(612, 639, 115, 42);
		panel.add(btnOk);
		
		JButton btnDeleteData = new JButton("Delete");
		btnDeleteData.setForeground(Color.WHITE);
		btnDeleteData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteData.setBackground(Color.ORANGE);
		btnDeleteData.setBounds(487, 639, 115, 42);
		panel.add(btnDeleteData);
		

		List<String> combodata = StocksController.getComboData();
		for(int i = 0; i < combodata.size();i++) {
			txtCategory.addItem(combodata.get(i));
		}
		txtCategory.setSelectedItem("");
		
		JButton btn_Stock = new JButton("Add Item");

		btn_Stock.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn_Stock.setForeground(Color.WHITE);
		btn_Stock.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_Stock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Stock.setBackground(new Color(0, 139, 139));
		btn_Stock.setActionCommand("ADD STOCK");
		btn_Stock.setBounds(10, 12, 205, 71);
		contentPane.add(btn_Stock);
		
		JButton btn_AddSale = new JButton("Add New Category");
		btn_AddSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_AddSale.setForeground(Color.WHITE);
		btn_AddSale.setFont(new Font("Tahoma", Font.BOLD, 33));
		btn_AddSale.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_AddSale.setBackground(new Color(0, 139, 139));
		btn_AddSale.setBounds(225, 11, 377, 72);
		contentPane.add(btn_AddSale);
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mousePressed(MouseEvent e) {

				Component[] textfield = {txtCode,txtName,txtCategory,txtStock,txtPrice};
				for(int i = 0; i < 5;i++) {
					Object selected = table.getValueAt(table.getSelectedRow(), i);
					if(i == 2) {
						((JComboBox<String>)textfield[i]).setSelectedItem(selected.toString());

					}
					else ((JTextField)textfield[i]).setText(selected.toString());
				}
			}
		});
	}
}
