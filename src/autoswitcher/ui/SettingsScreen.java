package autoswitcher.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import autoswitcher.input.InputManager;

@SuppressWarnings("serial")
/**
 * Jframe with all the settings in it, you can activate certain options with this screen
 * @author No
 *
 */
public class SettingsScreen extends JFrame{
	
	private Dimension ScreenSize = new Dimension(300, 300);
	private String Title = "OSRS autoSwitcher";
	private InputManager manager = InputManager.getInstance();

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
		
		JRadioButton noDragButton = new JRadioButton("No drag");
		noDragButton.setBounds(20, 20, 200, 20);
		noDragButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNoDrag(noDragButton.isSelected());
			}
		});
		panel.add(noDragButton);
		
		add(panel);
	}

}
