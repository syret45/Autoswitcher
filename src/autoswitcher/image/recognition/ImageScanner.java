package autoswitcher.image.recognition;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageScanner {
	public static void main(String[] args) throws HeadlessException, AWTException {
        try {
               BufferedImage dragonAxeImage = ImageIO.read(ImageScanner.class.getResourceAsStream("/autoswitcher/resources/" + "Dragon_axe.png"));
               boolean isOnScreen = isOnScreen(dragonAxeImage);
               System.out.print(isOnScreen);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
	
    private static boolean isOnScreen(BufferedImage bi) throws IOException, HeadlessException, AWTException{
    	BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File("/screenshot.png"));
               
        for(int x = 0; x< image.getWidth();x++){
            for(int y = 0; y< image.getHeight();y++){
               
                boolean invalid = false;
                int k = x,l = y;
                for(int a = 0;a<bi.getWidth();a++){
                    l = y;
                    for(int b = 0;b<bi.getHeight();b++){
                        if(bi.getRGB(a, b) != image.getRGB(k, l)){
                            invalid = true;
                            break;
                        }
                        else{
                            l++;
                        }
                    }
                    if(invalid){
                        break;
                    }else{
                        k++;
                    }
                       
                }
               
                if(!invalid){
                    return true;
                }
            }
        }
        return false; //If no image is found
       
    }
}
