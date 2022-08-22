package ui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utilities.WindowBridge;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.JTable;
public class ViewSales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;



	/**
	 * Create the frame.
	 */
	public ViewSales(JFrame prev_frame) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				WindowBridge.revert(null, prev_frame);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1920, 1080);
		contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setSize(getSize());
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(0, 0, 1920, 60);
		contentPane.add(panel);
		
		JLabel lblDashboard = new JLabel("SALES");
		lblDashboard.setIcon(null);
		lblDashboard.setIconTextGap(2);
		lblDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Roboto Black", Font.PLAIN, 50));
		lblDashboard.setAlignmentY(0.0f);
		lblDashboard.setBounds(10, 0, 151, 60);
		panel.add(lblDashboard);
		
		table = new JTable();
		table.setBounds(10, 71, 721, 911);
		contentPane.add(table);
	}
}
