package ui;

import java.awt.BorderLayout;
import utilities.WindowBridge;
import controllers.AddSaleController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class AddSale extends JFrame {

	private JPanel contentPane;


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
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnNewButton.setBounds(5, 642, 590, 153);
		JFrame k = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
	}

}
