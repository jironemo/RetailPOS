package ui;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.AddSaleController;
import custom_objects.ItemList;
import java.util.Date;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
@SuppressWarnings("serial")
public class AddSalePanel extends JPanel {

	private JTextField txt_Code,txt_Qty, txt_Paid;
	private double subtotal = 0.0;
	private double tax = 0.0;
	private double paid = 0.0;
	private AddSaleController asc = new AddSaleController();
	private JLabel lblCode,lblSubtotal,lblTax,lblDate,lblQty,lblPaid;
	private JTable table;
	private JButton btnPrintReceipt;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public AddSalePanel() {

		setSize(1050, 596);
		setFocusTraversalKeysEnabled(false);
		setFocusTraversalPolicyProvider(true);
		setBackground(new Color(102, 102, 225));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		
		instantiateLabels();
		instantiateTextFields();
		addTextBoxListeners();
		instantiateButtons();
		instantiateTable();
		

		
		
	}
	
	private void instantiateButtons() {

		btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnPrintReceipt.setBackground(new Color(0, 120, 215));
		btnPrintReceipt.setForeground(new Color(255, 255, 255));
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Paid.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Error: Paid Amount is Null");
				}else{
					double total_payable = subtotal + tax;
					paid = Double.parseDouble(txt_Paid.getText());
					ItemList items = getItemList();
					if(paid >= total_payable) {
						ReceiptPrint r = new ReceiptPrint(items,subtotal,tax,paid,table);
						r.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error: Paid Amount is not enough");
					}
				}
			}
		});
		btnPrintReceipt.setBounds(685, 549, 141, 36);
		add(btnPrintReceipt);
		
	}

	private void instantiateTable() {
		instatiateScrollPane();
		table = new JTable();
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setGridColor(new Color(0, 128, 0));
		table.setBackground(new Color(153,204,225));
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product code", "Product Name", "Unit", "Unit Price", "Qty"
			}
		));
		columnResize();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane.setViewportView(table);
		
		
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

		table.getColumnModel().getColumn(0).setPreferredWidth(152);
		table.getColumnModel().getColumn(1).setPreferredWidth(252);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
	}

	private void instatiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(234, 63, 592, 444);
		add(scrollPane);
	}

	private void addTextBoxListeners() {
		
		txt_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				codeEnter(e,1);
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
				codeEnter(e,3);
			}
		});
		
	}


	
	
	@SuppressWarnings("null")
	protected void codeEnter(KeyEvent e, int box) {
		// TODO Auto-generated method stub
		
		DefaultTableModel t = (DefaultTableModel)(table.getModel());
		int qty = 1;
		String code = txt_Code.getText();
		String item[] = null;
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {

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
				item[4] = Integer.toString(qty);
				if(item != null) {
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
		for(int k = 0; k < table.getRowCount();k++) {
			TableModel m = table.getModel();
			i.addItem(new String[]{m.getValueAt(k, 1)+" "+m.getValueAt(k, 2),m.getValueAt(k,3).toString(),m.getValueAt(k,4).toString()});
		}
		return i;
	}

	private void updateLabels(String price, int qty) {
		// TODO Auto-generated method stub
		subtotal += Double.parseDouble(price)*qty;
		tax = subtotal * 0.02;
		lblSubtotal.setText("Subtotal:\t"+subtotal);
		lblTax.setText("Tax:\t"+tax);
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
			}
		}
		return false;
	}

	private void instantiateTextFields() {
		// TODO Auto-generated method stub
		txt_Code = new JTextField();
		txt_Code.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Code.setBounds(531, 28, 162, 28);
		add(txt_Code);
		txt_Qty = new JTextField();
		txt_Qty.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Qty.setBounds(767, 28, 59, 28);
		add(txt_Qty);
		txt_Paid = new JTextField();
		txt_Paid.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		txt_Paid.setBounds(685, 512, 141, 28);
		add(txt_Paid);
		JTextField[] fields = {txt_Code,txt_Qty,txt_Paid};

		for(JTextField field : fields) {
			field.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		lblCode = new JLabel("Item Code\r\n");
		lblCode.setForeground(new Color(248, 248, 255));
		lblCode.setFocusable(false);
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCode.setBounds(454, 34, 64, 17);
		add(lblCode);
		
		lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setForeground(new Color(248, 248, 255));
		lblSubtotal.setFocusable(false);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtotal.setBounds(234, 515, 238, 22);
		add(lblSubtotal);
		
		lblDate = new JLabel("New label");
		lblDate.setForeground(new Color(248, 248, 255));
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(234, 35, 210, 17);
		lblDate.setText(new Date().toString());
		add(lblDate);
		
		lblTax = new JLabel("Tax:");
		lblTax.setForeground(new Color(248, 248, 255));
		lblTax.setFocusable(false);
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTax.setBounds(234, 557, 180, 20);
		add(lblTax);
		
		lblQty = new JLabel("Quantity");
		lblQty.setForeground(new Color(248, 248, 255));
		lblQty.setFocusable(false);
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQty.setBounds(703, 35, 53, 17);
		add(lblQty);
		
		lblPaid = new JLabel("Paid Amount:");
		lblPaid.setForeground(new Color(248, 248, 255));
		lblPaid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaid.setBounds(596, 518, 83, 17);
		add(lblPaid);
	}

	private void deleteRow() {
		int selectedRow = table.getSelectedRow();
		DefaultTableModel d = ((DefaultTableModel)table.getModel());
		subtotal -= Double.parseDouble(table.getValueAt(selectedRow, 3).toString()) * Double.parseDouble(table.getValueAt(selectedRow, 4).toString());
		table.clearSelection();
		d.removeRow(selectedRow);
		lblSubtotal.setText("Subtotal: "+Double.toString(subtotal));
		tax = subtotal * 0.02;
		lblTax.setText("Tax: "+Double.toString(tax));
	}
}	
