package custom_objects;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

	List<String>codes = new ArrayList<String>();
	List<String>names = new ArrayList<String>();
	List<String>prices = new ArrayList<String>();
	List<String>qty = new ArrayList<String>();
	public String[] getItemsForReceipt(int index) {
		
		String name = names.get(index);
		
		Double unit_price = Double.parseDouble(prices.get(index));
		
		int quan = Integer.parseInt(qty.get(index));
		
		Double qty_price = unit_price * quan;
	
		
		String[] item =  {name," @ "+ unit_price,Integer.toString(quan), qty_price.toString()};
		
		return(item);
	}
	
	public void addItem(String[] item) {
		names.add(item[0]);
		prices.add(item[1]);
		qty.add(item[2]);
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return names.size();
	}
	

	
}
