package autoswitcher.switcher;

import java.awt.Point;
import java.util.ArrayList;

import autoswitcher.image.recognition.ItemFinder;
import autoswitcher.input.InputManager;
import autoswitcher.ouput.OutputManager;
import autoswitcher.ui.OverlayScreen;

public class FullSwitch {

	private ArrayList<SwitchItem> items = new ArrayList<>();
	OverlayScreen screen = OverlayScreen.getInstance();

	public void addItem(SwitchItem item) {
		if (!item.equals(SwitchItem.Empty)) {
			items.add(item);
			ItemFinder.getInstance().addItem(item);
		}
	}
	
	public ArrayList<SwitchItem> getItems(){
		return items;
	}

	/**
	 * performs the switch
	 */
	public void doSwitch() {
		OutputManager out = OutputManager.getInstance();
		for (int i = 1; i < items.size(); i++) {
			int pos = items.get(i).getPos();
			if (pos != -1) {
				int x = (int) ((pos % InputManager.gridWidth) * 43 + (Math.random() * 21) + 10) + screen.getLocation().x;
				int y = (int) ((pos / InputManager.gridWidth) * 36 + (Math.random() * 17) + 8) + screen.getLocation().y;
				out.moveMouseNormal(new Point(x, y));
				out.leftClickNormal();
				System.out.println("Clicking :" + items.get(i).getItemName() + " at " + x + "  " + y);
			}
		}
	}

	/**
	 * checks if the mouseclick is on the first item of the switch
	 * 
	 * @param mouseClick
	 *            - the original mouseclick
	 * @return boolean - active switch
	 */
	public boolean checkActive(Point mouseClick) {
		int pos = items.get(0).getPos();
		if (pos != -1) {
			int x = pos % InputManager.gridWidth;
			int y = pos / InputManager.gridWidth;
			if (mouseClick.x - screen.getLocation().x >= x * 43 && mouseClick.x - screen.getLocation().x <= (x + 1) * 43 && mouseClick.y - screen.getLocation().y >= y * 36
					&& mouseClick.y - screen.getLocation().y <= (y + 1) * 36) {
				return true;
			}
		}
		return false;
	}

}
