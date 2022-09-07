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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class StocksPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCode,txtName,txtStock,txtPrice,txtDiscount, txtUnit;
	private JLabel lblCode,lblEditStockDetails ,lblProductName,lblStock,lblUnitPrice,lblCategory,lblDiscount,lblUnit,lblNewLabel;
	public static RowTable table;
	private JComboBox<String> txtCategory;
	private JPanel panel,contentPane;
	private JButton btnCancel,btnDeleteData,btnOk,btnAddStock,btnCheck;
	private JScrollPane scrollPane;
	private JTextField textField;
	/**
	 * Create the panel.
	 * @param i 
	 */
	public StocksPanel(int i) {
		setLayout(null);
		setSize(1050,596);
		
		
		instantiateContentPane();
		instantiatePanel();
		instantiateLabels();
		instantiateTextFields();
		instantiateButtons();
		instantiateTable();
		addEventListeners();
		setText(i);
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
		btnDeleteData.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(((JTextField)textfields[0]).getText().isEmpty() == false) {

					StocksController.deleteData(table, textfields);
				}else JOptionPane.showMessageDialog(null,"No Product is selected");
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
				wrapper.setSize(360,540);
				wrapper.setVisible(true);
				wrapper.setLocationRelativeTo(null);
			}
		});
		
	}

	private void instantiateContentPane() {
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 1050, 596);
		contentPane.setPreferredSize(new Dimension(1050, 596));
		contentPane.setBackground(new Color(255, 255, 59));
		contentPane.setLayout(null);
	}

	private void instantiatePanel() {
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 59));
		panel.setBounds(579, 83, 463, 502);
		panel.setLayout(null);
		contentPane.add(panel);
		
	}

	private void instantiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBackground(new Color(102, 255, 153));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 83, 557, 502);
		
		contentPane.add(scrollPane);
	}

	@SuppressWarnings("static-access")
	private void instantiateTable() {
		instantiateScrollPane();

		table = new RowTable();
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setDoubleBuffered(true);
		table.setForeground(new Color(0, 0, 0));
		table.setGridColor(new Color(255, 255, 204));
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setSelectionMode(table.getSelectionModel().SINGLE_SELECTION);
		DefaultTableModel d = StocksController.getData(table);
		table.setModel(d);
		table.setBackground(new Color(255, 255, 59));
		table.setSelectionBackground(new Color(0, 204, 204));
		table.setSelectionForeground(new Color(252, 225, 59));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		
	}

	private void instantiateButtons() {
		btnCancel = new JButton("Cancel");
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setVerticalTextPosition(SwingConstants.TOP);
		
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnCancel.setBackground(new Color(112, 128, 144));
		btnCancel.setBounds(99, 432, 89, 36);
		panel.add(btnCancel);
		
		btnOk = new JButton("OK");
		btnOk.setVerticalAlignment(SwingConstants.TOP);
		btnOk.setVerticalTextPosition(SwingConstants.TOP);
		
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnOk.setBackground(new Color(0, 204, 0));
		btnOk.setBounds(294, 431, 89, 38);
		panel.add(btnOk);
		
		
		btnDeleteData = new JButton("Delete");
		btnDeleteData.setVerticalAlignment(SwingConstants.TOP);
		btnDeleteData.setVerticalTextPosition(SwingConstants.TOP);
		btnDeleteData.setForeground(new Color(255, 255, 255));
		btnDeleteData.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnDeleteData.setBackground(new Color(204, 51, 0));
		btnDeleteData.setBounds(193, 432, 101, 37);
		panel.add(btnDeleteData);
		
		btnAddStock = new JButton("Add New Product");
		btnAddStock.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAddStock.setVerticalTextPosition(SwingConstants.TOP);
		btnAddStock.setBackground(new Color(0,204,0));
		btnAddStock.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddStock.setBorder(new EmptyBorder(0, 0, 0, 0));

		btnAddStock.setForeground(new Color(255, 255, 255));
		btnAddStock.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnAddStock.setBounds(786, 31, 184, 38);
		contentPane.add(btnAddStock);
		
		btnCheck = new JButton("Check");
		btnCheck.setVerticalAlignment(SwingConstants.TOP);
		btnCheck.setVerticalTextPosition(SwingConstants.TOP);
		btnCheck.setForeground(new Color(255, 255, 255));
		btnCheck.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnCheck.setBackground(new Color(51, 204, 255));
		btnCheck.setBounds(658, 33, 91, 38);
		contentPane.add(btnCheck);
	}

	private void instantiateLabels() {
		
		lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setBackground(new Color(112, 128, 144));
		lblEditStockDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditStockDetails.setForeground(new Color(47, 79, 79));
		lblEditStockDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStockDetails.setFont(new Font("Myanmar Text", Font.BOLD, 30));
		lblEditStockDetails.setBounds(61, 16, 396, 46);
		panel.add(lblEditStockDetails);
		
		lblCode = new JLabel("Product Code");
		lblCode.setBackground(new Color(112, 128, 144));
		lblCode.setForeground(new Color(47, 79, 79));
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCode.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCode.setBounds(23, 76, 165, 33);
		panel.add(lblCode);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setBackground(new Color(112, 128, 144));
		lblProductName.setForeground(new Color(47, 79, 79));
		lblProductName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblProductName.setBounds(17, 125, 171, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBackground(new Color(112, 128, 144));
		lblCategory.setForeground(new Color(47, 79, 79));
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCategory.setBounds(44, 228, 144, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setBackground(new Color(112, 128, 144));
		lblStock.setForeground(new Color(47, 79, 79));
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblStock.setBounds(44, 281, 144, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBackground(new Color(112, 128, 144));
		lblUnitPrice.setForeground(new Color(47, 79, 79));
		lblUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnitPrice.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnitPrice.setBounds(61, 334, 127, 33);
		panel.add(lblUnitPrice);
		
		JLabel lblper = new JLabel("%");
		lblper.setForeground(new Color(0, 153, 0));
		lblper.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblper.setBounds(364, 386, 19, 33);
		panel.add(lblper);
	
		lblUnit = new JLabel("Unit");
		lblUnit.setBackground(new Color(112, 128, 144));
		lblUnit.setForeground(new Color(47, 79, 79));
		lblUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnit.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnit.setBounds(108, 178, 80, 33);
		panel.add(lblUnit);

		lblDiscount = new JLabel("Discount");
		lblDiscount.setBackground(new Color(112, 128, 144));
		lblDiscount.setForeground(new Color(47, 79, 79));
		lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscount.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblDiscount.setBounds(108, 386, 80, 33);
		panel.add(lblDiscount);
		
		lblNewLabel = new JLabel("Enter Name or Product Code to Check Stock:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBackground(new Color(112, 128, 144));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 39, 300, 24);
		contentPane.add(lblNewLabel);
	}

	private void instantiateTextFields() {
		txtCode = new JTextField();
		txtCode.setForeground(new Color(0, 0, 0));
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(198, 64, 185, 42);
		panel.add(txtCode);
		
		txtUnit = new JTextField();
		txtUnit.setForeground(new Color(0, 0, 0));
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(198, 170, 185, 42);
		panel.add(txtUnit);

		txtName = new JTextField();
		txtName.setForeground(new Color(0, 0, 0));
		txtName.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(198, 117, 185, 42);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setBackground(new Color(153, 102, 0));
		txtCategory.setForeground(new Color(0, 0, 0));
		txtCategory.setOpaque(false);
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(198, 220, 185, 42);
		Populator.setComboData(txtCategory);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setForeground(new Color(0, 0, 0));
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(198, 273, 185, 42);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(0, 0, 0));
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(198, 326, 185, 42);
		panel.add(txtPrice);

		txtDiscount = new JTextField();
		txtDiscount.setForeground(new Color(0, 0, 0));
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(198, 381, 158, 42);
		panel.add(txtDiscount);
		
		textField = new JTextField();
		textField.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		textField.setBounds(322, 36, 330, 35);
		contentPane.add(textField);
		textField.setColumns(10);

	}



	public void setText(int selectedIndex) {
		// TODO Auto-generated method stub
		if(selectedIndex == 0) {
			btnCancel.setText("Cancel");
			btnDeleteData.setText("Delete");
			btnOk.setText("OK");
			btnAddStock.setText("Add New Product");
			btnCheck.setText("Check");
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
}
