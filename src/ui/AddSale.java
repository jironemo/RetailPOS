package ui;

import utilities.WindowBridge;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.AddSaleController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Point;
@SuppressWarnings("serial")
public class AddSale extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Code;
	private double subtotal = 0.0;
	private double tax = 0.0;
	private AddSaleController asc = new AddSaleController();
	private JTable table;
	private JTextField txt_Qty;
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.transferFocus();
		txt_Code = new JTextField();
		txt_Code.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Code.setBounds(5, 58, 171, 30);
		contentPane.add(txt_Code);
		
		JLabel lblNewLabel = new JLabel("Item Code\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(5, 36, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtotal.setBounds(188, 445, 202, 30);
		contentPane.add(lblSubtotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 99, 385, 335);
		contentPane.add(scrollPane);
		JLabel lblDate = new JLabel("New label");
		lblDate.setBounds(5, 11, 202, 14);
		lblDate.setText(new Date().toString());
		contentPane.add(lblDate);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTax.setBounds(188, 475, 160, 20);
		contentPane.add(lblTax);
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode() == KeyEvent.VK_DELETE) {

					 subtotal -= Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString()) * Double.parseDouble(table.getValueAt(table.getSelectedRow(), 4).toString());
					 lblSubtotal.setText("Subtotal: "+Double.toString(subtotal));
					 tax = subtotal * 0.02;
					 lblTax.setText("Tax: "+Double.toString(tax));
					 ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
				 }
			}
		});
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product code", "Product Name", "Unit", "Unit Price", "Qty"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(2).setPreferredWidth(42);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		

		
		txt_Qty = new JTextField();
		txt_Qty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				DefaultTableModel t = (DefaultTableModel)(table.getModel());
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] k = asc.getRowOfData(txt_Code.getText(),Integer.parseInt(txt_Qty.getText()));
					if(k!=null) {
						t.addRow(k);
						subtotal += Double.parseDouble(k[3]) * Double.parseDouble(txt_Qty.getText());
						tax = subtotal *0.02;
						lblTax.setText("Tax: " + tax +"Kyats");
						lblSubtotal.setText("Subtotal: " + subtotal + "Kyats");

						txt_Code.setText(null);
						txt_Qty.setText(null);
					}
					else {
						JOptionPane.showMessageDialog( null, "Item does not exist!", "Error",1, null);
						txt_Code.setText(null);
					}
				}
			}
		});
		txt_Qty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Qty.setBounds(250, 58, 58, 30);
		contentPane.add(txt_Qty);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQty.setBounds(250, 36, 72, 20);
		contentPane.add(lblQty);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(272, 521, 118, 30);
		contentPane.add(btnNewButton);
		txt_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				DefaultTableModel t = (DefaultTableModel)(table.getModel());

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] k = asc.getRowOfData(txt_Code.getText(),1);
					if(k!=null) {
						t.addRow(k);
						txt_Code.setText(null);
						subtotal += Double.parseDouble(k[3]);
						tax = subtotal *0.02;
						lblTax.setText("Tax: " + tax +"Kyats");
						lblSubtotal.setText("Subtotal: " + subtotal + "Kyats");
					}
					else {
						JOptionPane.showMessageDialog( null, "Item does not exist!", "Error",1, null);
						txt_Code.setText(null);
					}
				}else if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txt_Qty.grabFocus();
				}
			}
		});

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
	}
}
