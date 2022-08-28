package ui;

import utilities.WindowBridge;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controllers.AddSaleController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.PrintJob;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
@SuppressWarnings("serial")
public class AddSale extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Code,txt_Qty, txtPaid;
	private double subtotal = 0.0;
	private double tax = 0.0;
	private AddSaleController asc = new AddSaleController();
	private JLabel lblCode,lblSubtotal,lblTax,lblDate,lblQty,lblPaid;
	private JTable table;
	private JButton btnCancel,btnPrintReceipt;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public AddSale(JFrame prev_frame) {

		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 580);
		contentPane = new JPanel();
		contentPane.setFocusTraversalKeysEnabled(false);
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		instantiateLabels();
		instantiateTextFields();
		addTextBoxListeners();
		instantiateButtons();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				txt_Code.grabFocus();
			}
		});
		
		instantiateTable();
		

		
		
	}
	
	private void instantiateButtons() {
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(89, 539, 118, 30);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowBridge.switchWindowsTemp(getAddSale(), new ReceiptPrint());
			}

		});
		btnPrintReceipt.setBounds(267, 539, 118, 30);
		contentPane.add(btnPrintReceipt);
		
	}

	private void instantiateTable() {
		instatiateScrollPane();
		table = new JTable();
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

	private JFrame getAddSale() {
		// TODO Auto-generated method stub
		return this;
	}
	private void columnResize() {
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(2).setPreferredWidth(37);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
		
	}

	private void instatiateScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 115, 380, 307);
		contentPane.add(scrollPane);
	}

	private void addTextBoxListeners() {
		txt_Qty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				codeEnter(e,2); 
				
				}
		});
		txt_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				codeEnter(e,1);
			}
		});
		
	}

	protected void codeEnter(KeyEvent e, int box) {
		// TODO Auto-generated method stub
		DefaultTableModel t = (DefaultTableModel)(table.getModel());
		int keyCode = e.getKeyCode();
		String code = txt_Code.getText();
		if(keyCode == KeyEvent.VK_ENTER) {
			String[] k = null;
			if(box == 1) {
				k = asc.getRowOfData(txt_Code.getText(), 1);
				subtotal += Double.parseDouble(k[3]);
			}
			else if (box ==2) {
				if(!txt_Qty.getText().isBlank())
				{
					int qty = Integer.parseInt(txt_Qty.getText());
					k = asc.getRowOfData(code, qty);
					subtotal += Double.parseDouble(k[3]) * qty;
				}
			}
			if(k!= null) {
				t.addRow(k);
				tax = subtotal *0.02;
				lblTax.setText("Tax: " + tax +"Kyats");
				lblSubtotal.setText("Subtotal: " + subtotal + "Kyats");
				txt_Code.setText(null);
				txt_Qty.setText(null);
			}else {
				
			}
		}
		else if(keyCode == KeyEvent.VK_TAB) {
			if(box == 1) {
				if(!code.isBlank()) {
					txt_Qty.grabFocus();
				}else txtPaid.grabFocus();
			}else txtPaid.grabFocus();
		}
	}


	private void instantiateTextFields() {
		// TODO Auto-generated method stub
		txt_Code = new JTextField();
		txt_Code.setFocusTraversalKeysEnabled(false);
		txt_Code.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Code.setBounds(5, 58, 171, 30);
		contentPane.add(txt_Code);

		
		txt_Qty = new JTextField();
		txt_Qty.setFocusTraversalKeysEnabled(false);
		txt_Qty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Qty.setBounds(250, 58, 58, 30);
		contentPane.add(txt_Qty);
		txtPaid = new JTextField();
		txtPaid.setFocusTraversalKeysEnabled(false);
		txtPaid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPaid.setBounds(91, 471, 58, 30);
		contentPane.add(txtPaid);
		JTextField[] fields = {txt_Code,txt_Qty,txtPaid};
		
		for(JTextField field : fields) {
			Border k = field.getBorder();
			field.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					field.setBorder(new LineBorder(Color.GREEN,3 ));
				}
				public void focusLost(FocusEvent e) {
					field.setBorder(k);
				}
			});
		}
		
	}
	
	private void instantiateLabels() {
		lblCode = new JLabel("Item Code\r\n");
		lblCode.setFocusable(false);
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCode.setBounds(5, 36, 72, 14);
		contentPane.add(lblCode);
		
		lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFocusable(false);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtotal.setBounds(5, 445, 202, 30);
		contentPane.add(lblSubtotal);
		
		lblDate = new JLabel("New label");
		lblDate.setBounds(5, 11, 202, 14);
		lblDate.setText(new Date().toString());
		contentPane.add(lblDate);
		
		lblTax = new JLabel("Tax:");
		lblTax.setFocusable(false);
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTax.setBounds(230, 451, 160, 20);
		contentPane.add(lblTax);
		
		lblQty = new JLabel("Quantity");
		lblQty.setFocusable(false);
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQty.setBounds(250, 36, 72, 20);
		contentPane.add(lblQty);
		
		lblPaid = new JLabel("Paid Amount:");
		lblPaid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaid.setBounds(5, 477, 84, 20);
		contentPane.add(lblPaid);
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
