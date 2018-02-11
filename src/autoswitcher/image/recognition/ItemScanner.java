package autoswitcher.image.recognition;

import java.awt.image.BufferedImage;

import autoswitcher.switcher.SwitchItem;

public class ItemScanner implements Runnable{
	
	private SwitchItem item;
	
	public ItemScanner(SwitchItem item) {
		this.item = item;
	}

	@Override
	public void run() {
		BufferedImage sprite = item.getSprite();
	}

}
