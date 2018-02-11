package autoswitcher.switcher;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import autoswitcher.image.recognition.ImageScanner;

public class SwitchItem {

	private int position;
	private BufferedImage sprite;
	private String itemName;

	public void setPos(int pos) {
		position = pos;
	}

	public int getPos() {
		return position;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public String getItemName() {
		return itemName;
	}

	/**
	 * sets the sprite of the item
	 * @param spritePath - img name
	 * @return the sprite
	 */
	private BufferedImage setSprite(String spritePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageScanner.class.getResourceAsStream("/autoswitcher/resources/" + spritePath));
		} catch (IOException e) {
			e.printStackTrace();
		};
		return img;
	}

	public SwitchItem(String itemName, String spritePath) {
		this.itemName = itemName;
		sprite = setSprite(spritePath);
	}

	


}
