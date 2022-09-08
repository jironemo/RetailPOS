package ui;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.AddSaleController;
import custom_objects.ItemList;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
@SuppressWarnings("serial")
public class AddSalePanel extends JPanel {

	private JTextField txt_Code,txt_Qty, txt_Paid;
	private double subtotal = 0.0;
	private double tax = 0.0;
	private double paid = 0.0;
	private AddSaleController asc = new AddSaleController();
	private JLabel lblCode,lblSubtotal,lblTax,lblDate,lblQty,lblPaid,subtotal_text,tax_text;
	private JTable table;
	private JButton btnPrintReceipt;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JScrollPane scrollPane_1;
	private int language;
	private JPanel panel;
	private JPanel panel_1;
	/**
	 * Create the frame.
	 */
	public AddSalePanel(int language) {
		setOpaque(false);
		setSize(1050, 596);
		setFocusTraversalKeysEnabled(false);
		setFocusTraversalPolicyProvider(true);
		setBackground(new Color(252,225,59));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		this.language  = language;
		instantiateLabels();
		instantiateTextFields();
		instantiateButtons();
		instantiateTable();
		
		addTextBoxListeners();
		setText(language);
	}
	
	void setText(int language) {
		// TODO Auto-generated method stub
		if(language == 0) {
			lblCode.setText("Scan Barcode or Enter Name");
			subtotal_text.setText("Subtotal:");
			tax_text.setText("Tax:");
			lblQty.setText("Qty");
			lblPaid.setText("Paid");
			btnPrintReceipt.setText("Print Receipt");
		}
		else if(language == 1) {
			lblCode.setText("ပစ္စည်းအမည် (သို့) ဘားကုဒ်:");
			subtotal_text.setText("ပစ္စည်းတန်ဖိုး:");
			lblQty.setText("အရေအတွက်");
			tax_text.setText("အခွန်:");
			lblPaid.setText("ပေးထားငွေ");
			btnPrintReceipt.setText("ဘောက်ချာဖြတ်မည်");
		}
	}
	public void updatelanguage(int language) {
		this.language = language;
	}
	private void instantiateButtons() {

		btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPrintReceipt.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		btnPrintReceipt.setBorder(null);
		btnPrintReceipt.setBackground(Color.BLACK);
		btnPrintReceipt.setForeground(Color.WHITE);
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Paid.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Error: Paid Amount is Null");
				}else{
					double total_payable = subtotal + tax;
					
					paid = Double.parseDouble(txt_Paid.getText());
					ItemList items = getItemList();
					if(paid >= total_payable) {
						ReceiptPrint r = new ReceiptPrint(items,subtotal,tax,paid,table,language);
						r.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error: Paid Amount is not enough");
					}
				}
			}
		});
		btnPrintReceipt.setBounds(685, 557, 141, 36);
		add(btnPrintReceipt);
		
	}

	private void instantiateTable() {
		instatiateScrollPane();
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setSelectionBackground(Color.GRAY);
		table.setOpaque(false);
		table.setDoubleBuffered(true);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.WHITE);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product code", "Product Name", "Unit", "Unit Price", "Qty"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(152);
		table.getColumnModel().getColumn(1).setPreferredWidth(252);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
		columnResize();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane.setViewportView(table);
		
		subtotal_text = new JLabel();
		subtotal_text.setHorizontalTextPosition(SwingConstants.LEFT);
		subtotal_text.setVerticalAlignment(SwingConstants.BOTTOM);
		subtotal_text.setHorizontalAlignment(SwingConstants.LEFT);
		subtotal_text.setText("Subtotal:");
		subtotal_text.setForeground(Color.BLACK);
		subtotal_text.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		subtotal_text.setFocusable(false);
		subtotal_text.setBounds(20, 361, 181, 37);
		add(subtotal_text);
		
		tax_text = new JLabel();
		tax_text.setHorizontalTextPosition(SwingConstants.LEFT);
		tax_text.setVerticalAlignment(SwingConstants.BOTTOM);
		tax_text.setText("Tax:");
		tax_text.setHorizontalAlignment(SwingConstants.LEFT);
		tax_text.setForeground(Color.BLACK);
		tax_text.setFont(new Font("Myanmar Text", Font.BOLD, 15));
		tax_text.setFocusable(false);
		tax_text.setBounds(20, 433, 181, 36);
		add(tax_text);
		
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(8, 361, 2, 62);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(8, 433, 2, 62);
		add(panel_1);
		
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					 deleteRow();
				 }
			}
		});
		
	}

	private void columnResize() {
	}

	private void instatiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.control);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(234, 63, 592, 444);
		add(scrollPane);
	}

	private void addTextBoxListeners() {
		
		txt_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() ==KeyEvent.VK_ENTER) 
						codeEnter(e,1);
				else if(e.getKeyCode() == KeyEvent.VK_TAB) {
					focusGrabber(2);
				}
				else{
					getSuggestions();
				}
			}
		});
		txt_Qty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				codeEnter(e,2); 
			}
		});
		txt_Paid.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnPrintReceipt.getActionListeners()[0].actionPerformed(null);
				}
			}
		});
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2) {
					txt_Code.setText(list.getSelectedValue());
					list.setVisible(false);
					scrollPane_1.setVisible(false);
					focusGrabber(1);
					list.clearSelection();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
	}


	

	protected void getSuggestions() {
		// TODO Auto-generated method stub
		String[] items = asc.getSuggestions(txt_Code.getText());
		DefaultListModel<String> k = new DefaultListModel<String>();
		for(String item : items) {
			k.addElement(item);
		}
		list.setModel(k);
		list.setVisible(true);
		scrollPane_1.setVisible(true);
		list.clearSelection();
	}

	protected void codeEnter(KeyEvent e, int box) {
		// TODO Auto-generated method stub
		
		DefaultTableModel t = (DefaultTableModel)(table.getModel());
		int qty = 1;
		String code = txt_Code.getText();
		String item[] = null;
		list.setVisible(false);
		scrollPane_1.setVisible(false);
		list.clearSelection();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(box == 3) {
				btnPrintReceipt.getActionListeners()[0].actionPerformed(null);
			}
			int remainingQuantity = asc.getRemainingQuantity(code);
			////if the enter event occurred in txt_Qty,
			////the qty value is set to that of txt_Qty
			/////otherwise, the qty is left as it is;
			if (box == 2) {
				if(txt_Code.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "empty product code!" );
					return;
				}
				if(!txt_Qty.getText().isBlank()) {
					qty = Integer.parseInt(txt_Qty.getText());
				}
			}
			if(alrdyExists(code)==false) {
				item = asc.getRowOfData(code);
				if(item != null) {
					item[4] = Integer.toString(qty);
					if(remainingQuantity- qty > 0) {
						asc.addRowToTable(table, item);
						updateLabels(item[3],qty);
					}else outOfStockError();
				}
			}	
			else {
				int row_to_change = getExistingRow(code);
				String or_qty_Str = t.getValueAt(row_to_change, 4).toString();
				int or_qty = Integer.parseInt(or_qty_Str);
				int changed_qty = or_qty+qty;	
				if(remainingQuantity -changed_qty>0) {
					t.setValueAt(changed_qty, row_to_change, 4);
					updateLabels((String) t.getValueAt(row_to_change, 3),qty);
				}else outOfStockError();
			}
			txt_Code.setText(null);
			txt_Qty.setText(null);
			focusGrabber(1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			if(box == 3) {
				focusGrabber(1);
			}
			else focusGrabber(box+1);
		}
	}
	
	private void outOfStockError() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is out of stock!");
	}

	ItemList getItemList() {
		ItemList i = new ItemList();
		TableModel m = table.getModel();
		for(int k = 0; k < table.getRowCount();k++) {
			i.addItem(new String[]{m.getValueAt(k, 1)+" "+m.getValueAt(k, 2),m.getValueAt(k,3).toString(),m.getValueAt(k,4).toString()});
		}
		return i;
	}

	private void updateLabels(String price, int qty) {
		// TODO Auto-generated method stub
		subtotal += Double.parseDouble(price)*qty;
		tax = subtotal * 0.02;
		lblSubtotal.setText(new DecimalFormat("#.00").format(subtotal));
		lblTax.setText(new DecimalFormat("#.00").format(tax));
	}

	private void focusGrabber(int box) {
		// TODO Auto-generated method stub
		if(box == 1) {
			txt_Code.grabFocus();
		}
		else if(box ==2) {
			txt_Qty.grabFocus();
		}else if (box == 3) {
			txt_Paid.grabFocus();
		}
	}

	private int getExistingRow(String string) {
		// TODO Auto-generated method stub
		for(int i = 0; i < table.getModel().getRowCount();i++) {
			if(table.getValueAt(i,0).toString().equals(string)) {
				return i;
			}else if(table.getValueAt(i, 1).toString().equals(string)) {
				return i;
			}
		}
		return 0;
	}

	private boolean alrdyExists(String string) {
		// TODO Auto-generated method stub
		int row = table.getModel().getRowCount();
		for(int i =  0; i < row;i++) {
			if(table.getValueAt(i,0).toString().equals(string)) {
				return true;
			}else if(table.getValueAt(i, 1).toString().equals(string)) {
				return true;
			}
		}
		return false;
	}

	private void instantiateTextFields() {
		// TODO Auto-generated method stub
		txt_Code = new JTextField();
		txt_Code.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		txt_Code.setForeground(SystemColor.windowText);
		txt_Code.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Code.setBounds(10, 133, 214, 42);
		add(txt_Code);
		txt_Qty = new JTextField();
		txt_Qty.setForeground(SystemColor.windowText);
		txt_Qty.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Qty.setBounds(10, 222, 214, 42);
		add(txt_Qty);
		txt_Paid = new JTextField();
		txt_Paid.setForeground(SystemColor.windowText);
		txt_Paid.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Paid.setBounds(10, 308, 214, 42);
		add(txt_Paid);
		JTextField[] fields = {txt_Code,txt_Qty,txt_Paid};

		for(JTextField field : fields) {
			field.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
			field.setFocusTraversalKeysEnabled(false);
			Border org_border = field.getBorder();
			field.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					field.setBorder(new LineBorder(Color.GREEN,3));
				}
				public void focusLost(FocusEvent e) {
					field.setBorder(org_border);
				}
			});
		}
		
		
	}
	
	private void instantiateLabels() {
		setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 175, 286, 152);
		scrollPane_1.setVisible(false);
		add(scrollPane_1);
		
		list = new JList<String>();
		list.setLocation(481, 0);
		scrollPane_1.setViewportView(list);
		list.setVisible(false);
		
		
		lblCode = new JLabel("Scan Barcode or Enter Name\n");
		lblCode.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCode.setForeground(SystemColor.windowText);
		lblCode.setFocusable(false);
		lblCode.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		lblCode.setBounds(10, 107, 291, 28);
		add(lblCode);
		
		lblSubtotal = new JLabel();
		lblSubtotal.setForeground(SystemColor.windowText);
		lblSubtotal.setFocusable(false);
		lblSubtotal.setFont(new Font("Myanmar Text", Font.PLAIN, 20));
		lblSubtotal.setBounds(20, 394, 181, 42);
		add(lblSubtotal);
		
		lblDate = new JLabel("New label");
		lblDate.setForeground(SystemColor.windowText);
		lblDate.setFont(new Font("Myanmar Text", Font.BOLD, 20));
		lblDate.setBounds(10, 11, 266, 85);
		Calendar c = Calendar.getInstance();
		
		
		String[] days_of_week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		String[] months_of_year = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String[] am_pm = {"AM","PM"};
		int day_of_week_int = c.get(Calendar.DAY_OF_WEEK);
		int month_of_year_int = c.get(Calendar.MONTH);
		
		String day_of_week = days_of_week[day_of_week_int];
		String month_of_year = months_of_year[month_of_year_int];
		String second = "";
		if(c.get(Calendar.SECOND) < 10) {
			second = "0"+c.get(Calendar.SECOND);
		}else second = Integer.toString(c.get(Calendar.SECOND));
		String year = Integer.toString(c.get(Calendar.YEAR));
		String time = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE)+":"+second+am_pm[c.get(Calendar.AM_PM)];
		String date = day_of_week +" "+ month_of_year +" "+c.get(Calendar.DAY_OF_MONTH)+" "+ year;
		lblDate.setText("<html>"+date+"<br/>"+time+"</html>");
		add(lblDate);
		
		lblTax = new JLabel();
		lblTax.setForeground(SystemColor.windowText);
		lblTax.setFocusable(false);
		lblTax.setFont(new Font("Myanmar Text", Font.PLAIN, 20));
		lblTax.setBounds(20, 468, 181, 42);
		add(lblTax);
		
		lblQty = new JLabel("Qty");
		lblQty.setHorizontalTextPosition(SwingConstants.LEFT);
		lblQty.setVerticalAlignment(SwingConstants.TOP);
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setForeground(SystemColor.windowText);
		lblQty.setFocusable(false);
		lblQty.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		lblQty.setBounds(10, 194, 102, 28);
		add(lblQty);
		
		lblPaid = new JLabel("Paid Amount:");
		lblPaid.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPaid.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaid.setForeground(SystemColor.windowText);
		lblPaid.setFont(new Font("Myanmar Text", Font.PLAIN, 15));
		lblPaid.setBounds(10, 275, 148, 27);
		add(lblPaid);
	}

	private void deleteRow() {
		int selectedRow = table.getSelectedRow();
		DefaultTableModel d = ((DefaultTableModel)table.getModel());
		subtotal -= Double.parseDouble(table.getValueAt(selectedRow, 3).toString()) * Double.parseDouble(table.getValueAt(selectedRow, 4).toString());
		table.clearSelection();
		d.removeRow(selectedRow);
		lblSubtotal.setText(Double.toString(subtotal));
		tax = subtotal * 0.02;
		lblTax.setText(Double.toString(tax));
	}
}	
