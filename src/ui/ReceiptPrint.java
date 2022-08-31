package ui;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import custom_objects.ItemList;

import javax.swing.JScrollPane;
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
	/**
	 * Create the frame.
	 */

	
	public ReceiptPrint(ItemList i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			pf.setPaper(paper);
			receipt.getPrintable(null, null).print(receipt.getGraphics(), pf, ABORT);
			receipt.print();
		}  catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			receipt.setParagraphAttributes(parattr, true);
			int maxlength = items.getMaxLength();
			for(int i = 0; i < items.getLength();i++) {
				String[] item = items.getItem(i);
				String spaces  = "";
					for(int k = item[0].length(); k< maxlength;k++) {
						spaces+=" ";
					}
				doc.insertString(doc.getLength(),item[0] + item[1]+"MMK"+spaces+"\t    " + item[2] +"\t"+ item[3]+"\n", parattr);
			}

			receipt.setStyledDocument(doc);
		}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
