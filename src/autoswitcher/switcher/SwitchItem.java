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
	private boolean hardPos = false;

	public void setPos(int pos) {
		if (!hardPos) {
			position = pos;
		}
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
	
	public boolean getHardPos() {
		return hardPos;
	}

	/**
	 * sets the sprite of the item
	 * 
	 * @param spritePath
	 *            - img name
	 * @return the sprite
	 */
	private BufferedImage setSprite(String spritePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageScanner.class.getResourceAsStream("/autoswitcher/resources/" + spritePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return img;
	}

	public SwitchItem(String itemName, String spritePath) {
		this.itemName = itemName;
		sprite = setSprite(spritePath);
		allItems.add(this);
		allItemNames.add(itemName);
	}

	public SwitchItem(String itemName, String spritePath, boolean hardPos, int pos) {
		this.itemName = itemName;
		this.hardPos = hardPos;
		this.position = pos;
		sprite = setSprite(spritePath);
		allItems.add(this);
		allItemNames.add(itemName);
	}

	// all items in static below
	public static ArrayList<SwitchItem> allItems = new ArrayList<>();
	public static ArrayList<String> allItemNames = new ArrayList<>();
	// items
	public static SwitchItem Empty = new SwitchItem("", "");
	public static SwitchItem Pos0 = new SwitchItem("Position 0", "", true, 0);
	public static SwitchItem Pos1 = new SwitchItem("Position 1", "", true, 1);
	public static SwitchItem Pos2 = new SwitchItem("Position 2", "", true, 2);
	public static SwitchItem Pos3 = new SwitchItem("Position 3", "", true, 3);
	public static SwitchItem Pos4 = new SwitchItem("Position 4", "", true, 4);
	public static SwitchItem Pos5 = new SwitchItem("Position 5", "", true, 5);
	public static SwitchItem Pos6 = new SwitchItem("Position 6", "", true, 6);
	public static SwitchItem Pos7 = new SwitchItem("Position 7", "", true, 7);
	public static SwitchItem Pos8 = new SwitchItem("Position 8", "", true, 8);
	public static SwitchItem Pos9 = new SwitchItem("Position 9", "", true, 9);
	public static SwitchItem Pos10 = new SwitchItem("Position 10", "", true, 10);
	public static SwitchItem Pos11 = new SwitchItem("Position 11", "", true, 11);
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
