package autoswitcher.image.recognition;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import autoswitcher.switcher.SwitchItem;
import autoswitcher.ui.OverlayScreen;

public class ItemScanner implements Runnable {

	private SwitchItem item;
	private Robot r;
	int pixelCorrection = 10;

	public ItemScanner(SwitchItem item) {
		this.item = item;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Point screenloc = OverlayScreen.getInstance().getLocation();
		boolean found = false;
		// get image from screen
		for (int y = 0; y < 7 && !found; y++) {
			for (int x = 0; x < 4 && !found; x++) {
				BufferedImage screen = r.createScreenCapture(new Rectangle(screenloc.x + x * 43, screenloc.y + y * 36, 43, 36));
				if (imageOnScreen(screen)) {
					item.setPos(x + y * 4);
					System.out.println("found " + item.getItemName() + " x:" + x + " y:" + y);
					found = true;
				}
			}
		}
		// imagescanning in here

		if (!found) {
			item.setPos(-1);
		}
	}

	/**
	 * checks if item is in screen
	 * 
	 * @param screen
	 * @return
	 */
	private boolean imageOnScreen(BufferedImage screen) {
		BufferedImage sprite = item.getSprite();
		for(int y = 0; y < screen.getHeight() - sprite.getHeight();y++){
			for(int x = 0; x < screen.getWidth() - sprite.getWidth();x++){
				if (checkItem(screen.getSubimage(x, y, sprite.getWidth(), sprite.getHeight()))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * checks one specific picture
	 * 
	 * @return
	 */
	private boolean checkItem(BufferedImage screenportion) {
		int pixels = screenportion.getHeight() * screenportion.getWidth();
		int neededCorrectPixels = pixels;
		int correctPixels = 0;
		BufferedImage sprite = item.getSprite();
		for (int i = 0; i < pixels; i++) {
			int x = i % sprite.getWidth();
			int y = i / sprite.getWidth();

			// screenportion rgbs
			Color S = new Color(screenportion.getRGB(x, y));
			int SR = S.getRed();
			int SG = S.getGreen();
			int SB = S.getBlue();
			// image rgbs
			Color I = new Color(sprite.getRGB(x, y));
			int IR = I.getRed();
			int IG = I.getGreen();
			int IB = I.getBlue();
			// comparing
			if (I.getRGB() == 16777215) {
				neededCorrectPixels--;
			} else {
				if (SR >= IR - pixelCorrection && SR <= IR + pixelCorrection && SG >= IG - pixelCorrection && SG <= IG + pixelCorrection && SB >= IB - pixelCorrection && SB <= IB + pixelCorrection) {
					correctPixels++;
				}
			}
		}
		return (neededCorrectPixels * 0.6 < correctPixels);

	}
	
	private void makePicture(BufferedImage img, String name) {
		File outputfile = new File("C:/Users/No/Desktop/" + name + ".png");
		try {
			if (img != null) {
				ImageIO.write(img, "png", outputfile);
			} else {
				System.out.println("nulled");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
