package utilities;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class NonEditTableModel extends DefaultTableModel {
	public NonEditTableModel() {
		super();
	}

	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }

}
