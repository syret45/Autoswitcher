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
		s.addItem(new SwitchItem("dragon axe","Dragon_axe.png"));
		s.addItem(new SwitchItem("bruma root","Bruma_root.png"));
		SwitchManager.getInstance().addSwitch(s);
	}
}
