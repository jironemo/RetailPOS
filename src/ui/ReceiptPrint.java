package ui;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import java.util.Date;

public class ReceiptPrint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane receipt;

	/**
	 * Create the frame.
	 */
	public ReceiptPrint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		receipt = new JTextPane();
		receipt.setEditable(false);

		scrollPane.setViewportView(receipt);
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
			StyledDocument doc = receipt.getStyledDocument();
			Style style = receipt.addStyle("", null);
			StyleConstants.setFontSize(charattr, 14);
			doc.insertString(doc.getLength(), new Date().toString(), style);
			receipt.setStyledDocument(doc);
			PageFormat pf = new PageFormat();
			Paper paper = new Paper();
			pf.setPaper(paper);
			receipt.getPrintable(null, null).print(receipt.getGraphics(), pf, ABORT);
			receipt.print();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
