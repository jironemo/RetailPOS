package utilities;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RowTable extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	     Map<Integer, Color> rowColor = new HashMap<Integer, Color>();
	     Map<Integer,Color> foregroundColor = new HashMap<Integer,Color>();
	     public RowTable()
	     {
	    	 setSelectionBackground(Color.YELLOW);
	     }

	     @Override
	     public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	     {
	          Component c = super.prepareRenderer(renderer, row, column);
	          Color color =rowColor.get(row);
	          c.setBackground(color);
	          c.setForeground(foregroundColor.get(row));
	          return c;
	     }

	     public void setRowColor(int row, Color color,Color foreground)
	     {
	          rowColor.put(row, color);
	          foregroundColor.put(row,foreground);
	     }
	     
	     
	     

}
