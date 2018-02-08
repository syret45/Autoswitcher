package autoswitcher.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Jframe with all the settings in it, you can activate certain options with this screen
 * @author No
 *
 */
public class SettingsScreen extends JFrame{
	
	private Dimension ScreenSize = new Dimension(300, 300);
	private String Title = "OSRS autoSwitcher";

	/**
	 * constructer it will make all the components on the screen and make the screen visible
	 */
	public SettingsScreen(){
		setSize(ScreenSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Title);
		setResizable(false);
		addComponents();
		setVisible(true);
	}
	
	/**
	 * add all components to the screen
	 */
	private void addComponents(){
		//makes the panel to put components on
		JPanel panel = new JPanel(null);
		
		add(panel);
	}

}
