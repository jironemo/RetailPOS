package ui;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controllers.AddSaleController;
import custom_objects.ItemList;
import utilities.WindowBridge;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceiptPrint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane receipt;
	private JButton btnPrintReceipt;
	private ItemList items;
	private double subtotal;
	private double tax;
	private double paid;
	/**
	 * Create the frame.
	 * @param table 
	 */

	private AddSaleController saleController = new AddSaleController();
	public ReceiptPrint(ItemList i,double subtotal, double tax,double paid, JTable table) {
		this.subtotal = subtotal;
		this.tax = tax;
		this.paid=paid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 458, 546);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		receipt = new JTextPane();
		receipt.setAutoscrolls(false);
		receipt.setEditable(false);
	
		scrollPane.setViewportView(receipt);
		
		btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReceipt();
				saleController.processSale(table);
				
			}
		});
		btnPrintReceipt.setBounds(39, 573, 169, 42);
		contentPane.add(btnPrintReceipt);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(294, 573, 169, 42);
		contentPane.add(btnCancel);
		items = i;
		setUpDocument();
		// TODO Auto-generated constructor stub
	}

	public void printReceipt() {
		try {
			PageFormat pf = new PageFormat();
			Paper paper = new Paper();
			paper.setSize(72* 80/ 25.4, receipt.getHeight());
			pf.setPaper(paper);
			receipt.print();

		}  catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();
	}

	private void setUpDocument() {
		// TODO Auto-generated method stub
		try {
			SimpleAttributeSet charattr = new SimpleAttributeSet();
			SimpleAttributeSet parattr = new SimpleAttributeSet();
			StyleConstants.setBold(charattr, true);
			StyleConstants.setAlignment(parattr, StyleConstants.ALIGN_CENTER);
			StyleConstants.setFontFamily(charattr,"Arial");
			StyleConstants.setFontSize(charattr, 20);
			receipt.setParagraphAttributes(parattr, true);
			receipt.setCharacterAttributes(charattr, true);
			receipt.setText("AKS Conveinence Store\n");
			StyledDocument doc = receipt.getStyledDocument();;
			StyleConstants.setFontSize(charattr, 14);
			doc.insertString(doc.getLength(), new Date().toString()+"\n\n\n", null);
			StyleConstants.setAlignment(parattr, StyleConstants.ALIGN_LEFT);
			StyleConstants.setLeftIndent(parattr, (float) 5.0);
			StyleConstants.setBold(parattr,true);
			receipt.setParagraphAttributes(parattr, true);
			doc.insertString(doc.getLength(), String.format("%-21s         %s        %s     %s\n", "Name","Price","Qty","Subtotal"), parattr);
			StyleConstants.setBold(parattr, false);
			for(int i = 0; i < items.getLength();i++) {
				String[] item = items.getItemsForReceipt(i);
				String truncate = "%.20s...";
				String itemformat = String.format(truncate,item[0]);
				String format = "%-25s  %6s\t%3s	 %5s\n";
				String formattedString = String.format(format,itemformat,item[1],item[2],item[3]);
				doc.insertString(doc.getLength(),formattedString, parattr);
			}
			receipt.setStyledDocument(doc);
			String dash = "    ";
			for(int i =0; i < 49;i++) {
				dash+= "-";
			}
			doc.insertString(doc.getLength(), dash+"\n", parattr);
			StyleConstants.setAlignment(parattr,StyleConstants.ALIGN_RIGHT);
			StyleConstants.setBold(parattr,true);
			StyleConstants.setFontSize(parattr, 14);
			receipt.setParagraphAttributes( parattr,true);
			String footer = "Subtotal: " + subtotal + "  \n" +
							"Tax: "+tax + "  \n"+
							"Total Payable: " + (subtotal+tax)+"  \n"+
							"Paid Amount: " + paid + "  \n" +
							"Change: " + (paid - (subtotal+tax))+"  ";
			doc.insertString(doc.getLength(),footer, parattr);
		}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
