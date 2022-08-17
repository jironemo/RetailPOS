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
@SuppressWarnings("serial")
public class AddSale extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private double subtotal = 0.0;
	private double tax = 0.0;
	private AddSaleController asc = new AddSaleController();
	private JTable table;
	/**
	 * Create the frame.
	 */
	public AddSale(JFrame prev_frame) {

		setUndecorated(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
		});
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setFocusTraversalKeysEnabled(false);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.transferFocus();
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(111, 57, 340, 30);
		contentPane.add(textField);
		
		JLabel lblNewLabel = new JLabel("Enter Item Code\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(5, 66, 112, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtotal.setBounds(488, 662, 202, 30);
		contentPane.add(lblSubtotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 99, 685, 557);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product code", "Product Name", "Unit", "Unit Price"
			}
		));
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setBounds(488, 68, 202, 14);
		lblDate.setText(new Date().toString());
		contentPane.add(lblDate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCancel.setBounds(488, 735, 202, 54);
		JFrame k1 = this;
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k1.dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(488, 735, 202, 54);
		contentPane.add(btnCancel);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTax.setBounds(530, 703, 160, 20);
		contentPane.add(lblTax);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					DefaultTableModel t = (DefaultTableModel)(table.getModel());
					String[] k = asc.getRowOfData(textField.getText());
					if(k!=null) {
						t.addRow(k);
						textField.setText(null);
						subtotal += Double.parseDouble(k[3]);

						tax = subtotal *0.05;
						lblTax.setText("Tax: " + tax +"Kyats");
						lblSubtotal.setText("Subtotal: " + subtotal + "Kyats");

					}else {
						JOptionPane.showMessageDialog( null, "Item does not exist!", "Error",1, null);
						textField.setText(null);
					}
				}
			}
		});

	}
}
