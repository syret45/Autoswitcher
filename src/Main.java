import java.awt.AWTException;
import java.io.IOException;

import autoswitcher.switcher.FullSwitch;
import autoswitcher.switcher.SwitchItem;
import autoswitcher.switcher.SwitchManager;
import autoswitcher.ui.OverlayScreen;
import autoswitcher.ui.SettingsScreen;

public class Main {

	public static void main(String[] args) throws AWTException, IOException {

		new OverlayScreen();
		new SettingsScreen();
		/*
		FullSwitch s = new FullSwitch();
		s.addItem(new SwitchItem("black dhide body", "Black_d'hide_body.png"));
		s.addItem(new SwitchItem("rune platelegs", "Rune_platelegs.png"));
		s.addItem(new SwitchItem("avas accumulator", "Avas_Accumulator.png"));
		SwitchManager.getInstance().addSwitch(s);
		*/
	}
}
