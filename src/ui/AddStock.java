package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;

import utilities.Populator;
import controllers.AddStockController;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStock extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtUnit;
	private JTextField txtStock;
	private JTextField txtPrice;
	private JTextField txtDiscount;
	private JComboBox<String> txtCategory;
	private JLabel[] labels = null;
	private String[] values = {"","","","","","",""};
	/**
	 * Create the panel.
	 */
	public AddStock() {
		setSize(352,530);
		setLayout(null);
		
		instantiateLabels();
		instantiateTextBoxes();
		
		txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(135, 227, 185, 42);
		Populator.setComboData(txtCategory);
		add(txtCategory);
		
		JButton btnAdd = new JButton("Add Stock");
		btnAdd.setBounds(231, 442, 89, 33);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getValues();
				String message = "Confirm the following Data:\n";
				for(int i = 0; i < values.length;i++) {
					message+= labels[i].getText()+":\t"+values[i]+"\n";
				}
				int k = JOptionPane.showConfirmDialog(null, message, "Confirm Data", JOptionPane.YES_NO_OPTION);
				if(k == JOptionPane.YES_OPTION) {
					AddStockController.addToDB(values);
					((JFrame)(getParent().getParent().getParent().getParent())).dispose();
				}
			}

		});
	}

	@SuppressWarnings("unchecked")
	private void getValues() {
		Component[] fields = {txtCode,txtName,txtUnit,txtCategory,txtStock,txtPrice,txtDiscount};
		for(int i = 0; i < fields.length;i++) {
			if (i == 3) {
				values[i] = ((JComboBox<String>)fields[i]).getSelectedItem().toString();
			}else 
				values[i] = ((JTextField)fields[i]).getText();
		}
		
	}
	private void instantiateTextBoxes() {
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(135, 71, 185, 42);
		add(txtCode);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(135, 124, 185, 42);
		add(txtName);
		
		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(135, 177, 185, 42);
		add(txtUnit);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(135, 280, 185, 42);
		add(txtStock);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(135, 333, 185, 42);
		add(txtPrice);
		
		
		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(135, 388, 158, 42);
		add(txtDiscount);
		
	}

	private void instantiateLabels() {
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(51, 232, 74, 33);
		add(lblCategory);
		JLabel lblAddStock = new JLabel("Add Stock");
		lblAddStock.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAddStock.setBounds(91, 0, 176, 60);
		add(lblAddStock);
		
		JLabel lblCode = new JLabel("Product Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCode.setBounds(16, 75, 109, 33);
		add(lblCode);
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(10, 129, 115, 33);
		add(lblProductName);
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnit.setBounds(91, 182, 34, 33);
		add(lblUnit);
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(80, 285, 45, 33);
		add(lblStock);
		JLabel lblper = new JLabel("%");
		lblper.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblper.setBounds(301, 393, 19, 33);
		add(lblper);
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiscount.setBounds(55, 393, 70, 33);
		add(lblDiscount);
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnitPrice.setBounds(45, 338, 80, 33);
		add(lblUnitPrice);
		labels = new JLabel[]{lblCode,lblProductName,lblUnit,lblCategory,lblStock,lblUnitPrice,lblDiscount};
	}
}
