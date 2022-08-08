package utilities;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class NonEditTableModel extends DefaultTableModel {
	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }

}
