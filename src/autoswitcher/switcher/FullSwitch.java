package autoswitcher.switcher;

import java.awt.Point;
import java.util.ArrayList;

public class FullSwitch {
	
	private ArrayList<SwitchItem> items = new ArrayList<>();
	
	public void addItem(SwitchItem item){
		items.add(item);
	}
	
	/**
	 * performs the switch
	 */
	public void doSwitch(){
		
	}
	
	/**
	 * checks if the mouseclick is on the first item of the switch
	 * @param mouseClick - the original mouseclick
	 * @return boolean - active switch
	 */
	public boolean checkActive(Point mouseClick){
		return false;
	}

}
