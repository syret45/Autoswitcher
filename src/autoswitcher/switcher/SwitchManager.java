package autoswitcher.switcher;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import autoswitcher.ouput.OutputManager;

public class SwitchManager {

	private static SwitchManager instance;

	private ArrayList<FullSwitch> switches = new ArrayList<>();

	/**
	 * gets the instance of the switchmanager
	 * 
	 * @return instance
	 */
	public static SwitchManager getInstance() {
		if (instance == null) {
			instance = new SwitchManager();
		}
		return instance;
	}
	
	/**
	 * adds a switch to the switchmanager
	 * @param fullswitch - the switch
	 */
	public void addSwitch(FullSwitch fullswitch){
		switches.add(fullswitch);
	}

	/**
	 * tries all the switches and does them
	 * @param mouseClick - point of the mouseclick
	 */
	public void trySwitch(Point mouseClick,boolean returnMouse) {
		Point oldmouse = MouseInfo.getPointerInfo().getLocation();
		
		for (FullSwitch fullSwitch : switches) {
			if (fullSwitch.checkActive(mouseClick)) {
				fullSwitch.doSwitch();
				break;
			}
		}
		if (returnMouse) {
			OutputManager.getInstance().moveMouseNormal(oldmouse);
		}
	}

}
