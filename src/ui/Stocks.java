package ui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.WindowBridge;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.StocksController;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
@SuppressWarnings("serial")
public class Stocks extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Stocks(JFrame prev_frame) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0,0,1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setSize(1920,1080);
		contentPane.setBackground(new Color(51, 204, 102));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 944, 867);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		DefaultTableModel d = StocksController.getSales();

		table.setModel(d);
		
	}
}
