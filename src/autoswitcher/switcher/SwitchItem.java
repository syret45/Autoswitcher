package autoswitcher.switcher;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
		allItems.add(this);
		allItemNames.add(itemName);
	}

	//all items in static below
	public static ArrayList<SwitchItem> allItems = new ArrayList<>();
	public static ArrayList<String> allItemNames = new ArrayList<>();
	//items
 	public static SwitchItem Empty = new SwitchItem("", "");
 	public static SwitchItem Black_Dhide_Body = new SwitchItem("black d'hide body", "Black_d'hide_body.png");
 	public static SwitchItem Black_Dhide_chaps = new SwitchItem("black dhide chaps", "Black_dhide_chaps.png");
 	public static SwitchItem Enchanted_robe_top = new SwitchItem("Enchanted robe top", "Enchanted_robe_top.png");
 	public static SwitchItem Enchanted_robe_bottom = new SwitchItem("Enchanted robe bottom", "Enchanted_robe_bottom.png");
 	public static SwitchItem Rune_Platelegs = new SwitchItem("rune platelegs", "Rune_platelegs.png");
 	public static SwitchItem Avas_Accumulator = new SwitchItem("Avas accumulator", "Avas_Accumulator.png");
 	public static SwitchItem Rune_Crossbow = new SwitchItem("Rune Crossbow", "Rune_Crossbow.png");
 	public static SwitchItem Dragon_boots = new SwitchItem("Dragon boots", "Dragon_boots.png");
 	public static SwitchItem Dragon_defender = new SwitchItem("Dragon defender", "Dragon_defender.png");
 	public static SwitchItem Tentacle_whip = new SwitchItem("Tentacle whip", "Tent_whip.png");
 	public static SwitchItem Torag_platebody = new SwitchItem("Torag platebody", "Torag_platebody.png");
 	public static SwitchItem Torag_platelegs = new SwitchItem("Torag platelegs", "Torag_platelegs.png");
 	public static SwitchItem Armadyl_godsword = new SwitchItem("Armadyl godsword", "Armadyl_godsword.png");
 	public static SwitchItem Infernal_cape = new SwitchItem("Infernal cape", "Infernal_cape.png");


}
