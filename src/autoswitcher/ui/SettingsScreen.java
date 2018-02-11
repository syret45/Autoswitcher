package autoswitcher.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import autoswitcher.input.InputManager;
import autoswitcher.ouput.OutputManager;

@SuppressWarnings("serial")
/**
 * Jframe with all the settings in it, you can activate certain options with
 * this screen
 * 
 * @author No
 *
 */
public class SettingsScreen extends JFrame {

	private Dimension ScreenSize = new Dimension(300, 300);
	private String Title = "OSRS autoSwitcher";
	private JTextField mouseMedField, mouseGausField;
	private InputManager manager = InputManager.getInstance();

	/**
	 * constructer it will make all the components on the screen and make the
	 * screen visible
	 */
	public SettingsScreen() {
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
	private void addComponents() {
		// makes the panel to put components on
		JPanel panel = new JPanel(null);

		mouseMedField = new JTextField("100");
		mouseMedField.setToolTipText("Median of the mouse speed in ms");
		mouseMedField.setBounds(20, 20, 100, 20);
		mouseMedField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMouseSpeed();
			}
		});
		panel.add(mouseMedField);

		mouseGausField = new JTextField("10");
		mouseGausField.setToolTipText("gausian of the mouse speed in ms");
		mouseGausField.setBounds(20, 50, 100, 20);
		mouseGausField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMouseSpeed();
			}
		});
		panel.add(mouseGausField);

		JRadioButton noDragButton = new JRadioButton("No drag");
		noDragButton.setBounds(20, 80, 200, 20);
		noDragButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNoDrag(noDragButton.isSelected());
			}
		});
		panel.add(noDragButton);

		JRadioButton returnMouseButton = new JRadioButton("return mouse");
		returnMouseButton.setBounds(20, 110, 200, 20);
		returnMouseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setReturnMouse(returnMouseButton.isSelected());
			}
		});
		panel.add(returnMouseButton);

		JRadioButton f4Button = new JRadioButton("f4 barrage");
		f4Button.setBounds(20, 140, 200, 20);
		f4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setF4Barrage(f4Button.isSelected());
			}
		});
		panel.add(f4Button);

		add(panel);
	}

	private void setMouseSpeed() {
		int med = 100;
		int gaus = 10;
		med = Integer.parseInt(mouseMedField.getText());
		gaus = Integer.parseInt(mouseGausField.getText());
		OutputManager.getInstance().setMouseSpeed(med, gaus);
	}
}

