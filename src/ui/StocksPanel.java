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
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JTextField txtSearch;
	protected int language;
	/**
	 * Create the panel.
	 * @param i 
	 */
	public StocksPanel(int i) {
		setLayout(null);
		setSize(1050,596);
		this.language = i;
		
		instantiateContentPane();
		instantiatePanel();
		instantiateLabels();
		instantiateTextFields();
		instantiateButtons();
		instantiateTable();
		addEventListeners();
		setText(i);
		add(contentPane);
		
		JButton btnReset = new JButton("Refresh");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
				table.setRowSorter(null);
			}
		});
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset.setVerticalAlignment(SwingConstants.BOTTOM);
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnReset.setBorder(null);
		btnReset.setBackground(Color.DARK_GRAY);
		btnReset.setBounds(476, 38, 91, 38);
		contentPane.add(btnReset);
		

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
				TableRowSorter<TableModel> sort = new TableRowSorter<TableModel>(table.getModel());
				sort.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
				table.setRowSorter(sort);
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
				AddStock as = new AddStock(language);
				wrapper.getContentPane().add(as);
				wrapper.setSize(as.getSize());
				wrapper.setVisible(true);
				wrapper.setLocationRelativeTo(null);
				wrapper.setBackground(getBackground());
			}
		});
		
	}

	private void instantiateContentPane() {
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 1050, 596);
		contentPane.setPreferredSize(new Dimension(1050, 596));
		contentPane.setBackground(new Color(255, 205, 130));
		contentPane.setLayout(null);
	}

	private void instantiatePanel() {
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 59));
		panel.setBounds(579, 83, 463, 502);
		panel.setLayout(null);
		contentPane.add(panel);
		
	}

	private void instantiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBackground(new Color(255, 205, 130));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 83, 557, 502);
		
		contentPane.add(scrollPane);
	}

	@SuppressWarnings("static-access")
	private void instantiateTable() {
		instantiateScrollPane();

		table = new RowTable();
		table.setOpaque(false);
		table.setDoubleBuffered(true);
		table.setForeground(new Color(0, 0, 0));
		table.setGridColor(Color.WHITE);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setSelectionMode(table.getSelectionModel().SINGLE_SELECTION);
		DefaultTableModel d = StocksController.getData(table);
		table.setModel(d);
		table.setBackground(new Color(255, 205, 130));
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
		btnCancel = new JButton("Clear");
		btnCancel.setBorder(null);
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setVerticalTextPosition(SwingConstants.TOP);
		
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnCancel.setBackground(new Color(204, 0, 51));
		btnCancel.setBounds(352, 85, 89, 36);
		panel.add(btnCancel);
		
		btnOk = new JButton("OK");
		btnOk.setBorder(null);
		btnOk.setVerticalAlignment(SwingConstants.BOTTOM);
		btnOk.setVerticalTextPosition(SwingConstants.TOP);
		
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnOk.setBackground(Color.DARK_GRAY);
		btnOk.setBounds(61, 453, 185, 38);
		panel.add(btnOk);
		
		
		btnDeleteData = new JButton("DELETE");
		btnDeleteData.setBorder(null);
		btnDeleteData.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDeleteData.setVerticalTextPosition(SwingConstants.TOP);
		btnDeleteData.setForeground(new Color(255, 255, 255));
		btnDeleteData.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnDeleteData.setBackground(new Color(204, 51, 0));
		btnDeleteData.setBounds(256, 454, 185, 37);
		panel.add(btnDeleteData);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(StocksPanel.class.getResource("/resources/rsz_1photo_2022-09-08_10-21-32-removebg-preview.png")));
		lblNewLabel_1.setBounds(41, 0, 100, 100);
		panel.add(lblNewLabel_1);
		
		btnAddStock = new JButton("Add New Product");
		btnAddStock.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAddStock.setVerticalTextPosition(SwingConstants.TOP);
		btnAddStock.setBackground(Color.DARK_GRAY);
		btnAddStock.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddStock.setBorder(null);

		btnAddStock.setForeground(new Color(255, 255, 255));
		btnAddStock.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnAddStock.setBounds(861, 38, 159, 38);
		contentPane.add(btnAddStock);
		
		btnCheck = new JButton("Check");
		btnCheck.setBorder(null);
		btnCheck.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCheck.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCheck.setForeground(new Color(255, 255, 255));
		btnCheck.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		btnCheck.setBackground(Color.DARK_GRAY);
		btnCheck.setBounds(382, 38, 91, 38);
		contentPane.add(btnCheck);
	}

	private void instantiateLabels() {
		
		lblEditStockDetails = new JLabel("Edit Stock Details");
		lblEditStockDetails.setBackground(new Color(112, 128, 144));
		lblEditStockDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditStockDetails.setForeground(new Color(47, 79, 79));
		lblEditStockDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStockDetails.setFont(new Font("Myanmar Text", Font.BOLD, 30));
		lblEditStockDetails.setBounds(131, 27, 275, 60);
		panel.add(lblEditStockDetails);
		
		lblCode = new JLabel("Product Code");
		lblCode.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCode.setBackground(new Color(112, 128, 144));
		lblCode.setForeground(new Color(47, 79, 79));
		lblCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCode.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCode.setBounds(61, 189, 165, 33);
		panel.add(lblCode);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblProductName.setBackground(new Color(112, 128, 144));
		lblProductName.setForeground(new Color(47, 79, 79));
		lblProductName.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductName.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblProductName.setBounds(61, 102, 171, 33);
		panel.add(lblProductName);
		
		lblCategory = new JLabel("Category");
		lblCategory.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCategory.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategory.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCategory.setBackground(new Color(112, 128, 144));
		lblCategory.setForeground(new Color(47, 79, 79));
		lblCategory.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategory.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblCategory.setBounds(61, 281, 144, 33);
		panel.add(lblCategory);
		
		lblStock = new JLabel("Stock");
		lblStock.setHorizontalTextPosition(SwingConstants.LEFT);
		lblStock.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblStock.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStock.setBackground(new Color(112, 128, 144));
		lblStock.setForeground(new Color(47, 79, 79));
		lblStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblStock.setBounds(256, 281, 144, 33);
		panel.add(lblStock);
		
		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUnitPrice.setBackground(new Color(112, 128, 144));
		lblUnitPrice.setForeground(new Color(47, 79, 79));
		lblUnitPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitPrice.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnitPrice.setBounds(61, 358, 127, 33);
		panel.add(lblUnitPrice);
	
		lblUnit = new JLabel("Unit");
		lblUnit.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUnit.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUnit.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUnit.setBackground(new Color(112, 128, 144));
		lblUnit.setForeground(new Color(47, 79, 79));
		lblUnit.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnit.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblUnit.setBounds(256, 189, 80, 33);
		panel.add(lblUnit);

		lblDiscount = new JLabel("Discount (%)");
		lblDiscount.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDiscount.setBackground(new Color(112, 128, 144));
		lblDiscount.setForeground(new Color(47, 79, 79));
		lblDiscount.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiscount.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblDiscount.setBounds(256, 358, 106, 33);
		panel.add(lblDiscount);
		
		lblNewLabel = new JLabel("Enter Name or Product Code to Check Stock:");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(new Color(112, 128, 144));
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 397, 24);
		contentPane.add(lblNewLabel);
	}

	private void instantiateTextFields() {
		txtCode = new JTextField();
		txtCode.setForeground(new Color(0, 0, 0));
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(61, 217, 185, 42);
		panel.add(txtCode);
		
		txtUnit = new JTextField();
		txtUnit.setForeground(new Color(0, 0, 0));
		txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnit.setColumns(10);
		txtUnit.setBounds(256, 217, 185, 42);
		panel.add(txtUnit);

		txtName = new JTextField();
		txtName.setForeground(new Color(0, 0, 0));
		txtName.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(63, 130, 378, 42);
		panel.add(txtName);
		
		txtCategory = new JComboBox<String>();
		txtCategory.setBackground(new Color(153, 102, 0));
		txtCategory.setForeground(new Color(0, 0, 0));
		txtCategory.setOpaque(false);
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setEditable(true);
		txtCategory.setBounds(61, 312, 185, 42);
		Populator.setComboData(txtCategory);
		panel.add(txtCategory);
		
		txtStock = new JTextField();
		txtStock.setForeground(new Color(0, 0, 0));
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStock.setColumns(10);
		txtStock.setBounds(256, 312, 185, 42);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(0, 0, 0));
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(61, 389, 185, 42);
		panel.add(txtPrice);

		txtDiscount = new JTextField();
		txtDiscount.setForeground(new Color(0, 0, 0));
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(256, 389, 185, 42);
		panel.add(txtDiscount);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				TableRowSorter<TableModel> sort = new TableRowSorter<TableModel>(table.getModel());
				sort.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
				table.setRowSorter(sort);
			}
		});
		
		txtSearch.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		txtSearch.setBounds(10, 38, 362, 38);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

	}



	public void setText(int selectedIndex) {
		// TODO Auto-generated method stub
		if(selectedIndex == 0) {
			btnCancel.setText("Clear");
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
			lblDiscount.setText("Discount (%)");
			lblEditStockDetails.setText("Edit Stock Details");
			lblEditStockDetails.setFont(new Font("Tahoma", Font.BOLD, 30));
		}else if(selectedIndex == 1) {
			btnCancel.setText("ရှင်းလင်းမည်");
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
			lblDiscount.setText("လျှော့စျေး(%)");
			lblEditStockDetails.setText("ပစ္စည်းအချက်အလက်များပြင်ဆင်ရန်");
			lblEditStockDetails.setFont(new Font("Myanmar Text", Font.BOLD, 17));
		}
	}
}
