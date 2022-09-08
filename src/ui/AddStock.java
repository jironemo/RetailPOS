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
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

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
	private JLabel lblCode,lblProductName,lblUnit,lblCategory,lblStock,lblUnitPrice,lblDiscount,lblAddStock;
	private JLabel[] labels = null;
	private String[] values = {"","","","","","",""};
	/**
	 * Create the panel.
	 */
	public AddStock(int language) {
		setSize(515,530);
		setLayout(null);
		setBackground(new Color(255, 205, 130));
		instantiateLabels();
		instantiateTextBoxes();
		
		txtCategory = new JComboBox<String>();
		txtCategory.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(49, 292, 185, 42);
		Populator.setComboData(txtCategory);
		add(txtCategory);
		
		JButton btnAdd = new JButton("Add Stock");
		btnAdd.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(242, 437, 185, 42);
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
					boolean success = AddStockController.addToDB(values);
					if(success == true) {
						clearTextBoxes();
					}
				}
			}	
		});
		setText(language);
	}

	protected void clearTextBoxes() {
		// TODO Auto-generated method stub
		  txtCode.setText("");
		  txtName.setText("");
		txtUnit.setText("");
		txtStock.setText("");
		txtPrice.setText("");
		txtDiscount.setText("");
		txtCategory.setSelectedIndex(-1);
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
		txtCode.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(49, 128, 378, 42);
		add(txtCode);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtName.setColumns(10);
		txtName.setBounds(49, 212, 185, 42);
		add(txtName);
		
		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(242, 212, 185, 42);
		add(txtUnit);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(242, 292, 185, 42);
		add(txtStock);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(49, 384, 185, 42);
		add(txtPrice);
		
		
		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(242, 384, 185, 42);
		add(txtDiscount);
		
	}

	private void instantiateLabels() {
		lblCategory = new JLabel("Category");
		lblCategory.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategory.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblCategory.setBounds(49, 262, 183, 33);
		add(lblCategory);
		lblAddStock = new JLabel("Add Stock");
		lblAddStock.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAddStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStock.setFont(new Font("Myanmar Text", Font.BOLD, 28));
		lblAddStock.setBounds(49, 34, 378, 42);
		add(lblAddStock);
		
		lblCode = new JLabel("Product Code");
		lblCode.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCode.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblCode.setBounds(49, 94, 378, 33);
		add(lblCode);
		lblProductName = new JLabel("Product Name");
		lblProductName.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProductName.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblProductName.setBounds(49, 181, 183, 33);
		add(lblProductName);
		lblUnit = new JLabel("Unit");
		lblUnit.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUnit.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblUnit.setBounds(242, 181, 185, 33);
		add(lblUnit);
		lblStock = new JLabel("Stock");
		lblStock.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStock.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblStock.setBounds(242, 262, 185, 33);
		add(lblStock);
		lblDiscount = new JLabel("Discount(%)");
		lblDiscount.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDiscount.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblDiscount.setBounds(242, 350, 185, 33);
		add(lblDiscount);
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUnitPrice.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		lblUnitPrice.setBounds(49, 350, 183, 33);
		add(lblUnitPrice);
		labels = new JLabel[]{lblCode,lblProductName,lblUnit,lblCategory,lblStock,lblUnitPrice,lblDiscount};
	}
	
	
	public void setText(int language) {
		if(language == 1) {
			lblCode.setText("ပစ္စည်းအမှတ်"); 
			lblStock.setText("ပစ္စည်းလက်ကျန်");
			lblProductName.setText("ပစ္စည်းအမည်");
			lblUnitPrice.setText("တခုချင်းစျေးနှုန်း");
			lblCategory.setText("ပစ္စည်းအမျိုးအစား");
			lblUnit.setText("ပမာဏ");
			lblDiscount.setText("လျှော့စျေး(%)");
			lblAddStock.setText("ပစ္စည်းအသစ် ထည့်ရန်");
		}else {
			lblCode.setText("Product Code"); 
			lblStock.setText("Stock");
			lblProductName.setText("Product Name");
			lblUnitPrice.setText("Price");
			lblCategory.setText("Category");
			lblUnit.setText("Unit");
			lblDiscount.setText("Discount (%)");

			lblAddStock.setText("Add Stock");
		}
	}
}
