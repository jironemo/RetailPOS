package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AddNewItem extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JComboBox<String> category;
	private JTextField unit;
	private JTextField stock;
	private JTextField price;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AddNewItem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id = new JTextField();
		id.setBounds(127, 83, 268, 41);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(127, 157, 268, 41);
		contentPane.add(name);
		
		category = new JComboBox<String>();
		category.setBounds(127, 240, 268, 41);
		contentPane.add(category);
		
		unit = new JTextField();
		unit.setColumns(10);
		unit.setBounds(127, 306, 268, 41);
		contentPane.add(unit);
		
		stock = new JTextField();
		stock.setColumns(10);
		stock.setBounds(127, 375, 268, 41);
		contentPane.add(stock);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(127, 451, 268, 41);
		contentPane.add(price);
	}
}
