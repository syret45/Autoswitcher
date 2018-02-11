import java.awt.AWTException;
import java.io.IOException;

import autoswitcher.switcher.FullSwitch;
import autoswitcher.switcher.SwitchItem;
import autoswitcher.switcher.SwitchManager;
import autoswitcher.ui.OverlayScreen;
import autoswitcher.ui.SettingsScreen;

public class Main {

	public static void main(String[] args) throws AWTException, IOException {
		// TODO Auto-generated method stub
		new SettingsScreen();
		new OverlayScreen();
		FullSwitch s = new FullSwitch();
		s.addItem(new SwitchItem(0));
		s.addItem(new SwitchItem(1));
		s.addItem(new SwitchItem(2));
		s.addItem(new SwitchItem(3));
		SwitchManager.getInstance().addSwitch(s);
	}
}
