package autoswitcher.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import autoswitcher.connection.Connector;
import autoswitcher.input.InputManager;
import autoswitcher.ouput.MovementType;
import autoswitcher.ouput.OutputManager;
import autoswitcher.switcher.SwitchManager;

@SuppressWarnings("serial")
/**
 * Jframe with all the settings in it, you can activate certain options with
 * this screen
 * 
 * @author No
 *
 */
public class SettingsScreen extends JFrame {

	private Dimension ScreenSize = new Dimension(300, 430);
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
		//setTitle(Title + " for "+  Connector.getInstance().username);
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
		mouseMedField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int med = Integer.parseInt(mouseMedField.getText());
					int gaus = Integer.parseInt(mouseGausField.getText());
					setMouseSpeed(med, gaus);
				} catch (Exception e1) {
					System.out.println("oops");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(mouseMedField);

		mouseGausField = new JTextField("10");
		mouseGausField.setToolTipText("gausian of the mouse speed in ms");
		mouseGausField.setBounds(20, 50, 100, 20);
		mouseGausField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int med = Integer.parseInt(mouseMedField.getText());
					int gaus = Integer.parseInt(mouseGausField.getText());
					setMouseSpeed(med, gaus);
				} catch (Exception e1) {
					System.out.println("oops");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(mouseGausField);

		JTextField gridHeight = new JTextField("3");
		gridHeight.setToolTipText("the height of the grid in the overlay");
		gridHeight.setBounds(150, 20, 100, 20);
		gridHeight.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					InputManager.gridHeight = Integer.parseInt(gridHeight.getText());
					OverlayScreen.getInstance().repaint();
				} catch (Exception e1) {
					System.out.println("oops");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(gridHeight);

		JTextField gridWidth = new JTextField("4");
		gridWidth.setToolTipText("the width of the grid in the overlay");
		gridWidth.setBounds(150, 50, 100, 20);
		gridWidth.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					InputManager.gridWidth = Integer.parseInt(gridWidth.getText());
					OverlayScreen.getInstance().repaint();
				} catch (Exception e1) {
					System.out.println("oops");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(gridWidth);

		JComboBox<MovementType> movementTypeBox = new JComboBox<>(MovementType.values());
		movementTypeBox.setBounds(20, 80, 100, 20);
		movementTypeBox.setToolTipText("Sets the way the mouse should move");
		movementTypeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OutputManager.getInstance().setMovementType((MovementType) movementTypeBox.getSelectedItem());
			}
		});
		panel.add(movementTypeBox);

		JRadioButton noOverlayButton = new JRadioButton("Hide overlay");
		noOverlayButton.setBounds(20, 110, 200, 20);
		noOverlayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OverlayScreen.getInstance().setVisible((!noOverlayButton.isSelected()));
			}
		});
		panel.add(noOverlayButton);

		JRadioButton noDragButton = new JRadioButton("No drag (f6 to temp disable)");
		noDragButton.setBounds(20, 140, 200, 20);
		noDragButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNoDrag(noDragButton.isSelected());
			}
		});
		panel.add(noDragButton);

		JRadioButton returnMouseButton = new JRadioButton("return mouse");
		returnMouseButton.setBounds(20, 170, 200, 20);
		returnMouseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setReturnMouse(returnMouseButton.isSelected());
			}
		});
		panel.add(returnMouseButton);

		JRadioButton f4Button = new JRadioButton("f4 barrage");
		JRadioButton f4Buttonblitz = new JRadioButton("f4 blitz");
		f4Button.setBounds(20, 200, 200, 20);
		f4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (f4Button.isSelected()) {
					f4Buttonblitz.setSelected(false);
				}
				manager.setF4Barrage(f4Button.isSelected());
				manager.setF4Blitz(f4Buttonblitz.isSelected());
			}
		});
		panel.add(f4Button);

		f4Buttonblitz.setBounds(20, 230, 200, 20);
		f4Buttonblitz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (f4Buttonblitz.isSelected()) {
					f4Button.setSelected(false);
				}
				manager.setF4Barrage(f4Button.isSelected());
				manager.setF4Blitz(f4Buttonblitz.isSelected());
			}
		});
		panel.add(f4Buttonblitz);

		JRadioButton AutoSwitchButton = new JRadioButton("autoSwitch");
		AutoSwitchButton.setBounds(20, 260, 200, 20);
		AutoSwitchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setAutoswitch(AutoSwitchButton.isSelected());
			}
		});
		panel.add(AutoSwitchButton);

		JButton newSwitchButton = new JButton("Add new switch");
		newSwitchButton.setBounds(20, 290, 150, 20);
		newSwitchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddSwitchScreen();
			}
		});
		panel.add(newSwitchButton);

		JButton DeleteSwitchButton = new JButton("Delete all switches");
		DeleteSwitchButton.setBounds(20, 320, 150, 20);
		DeleteSwitchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwitchManager.getInstance().DeleteAllSwitches();
			}
		});
		panel.add(DeleteSwitchButton);
		
		FeedBackButton fbButton = new FeedBackButton();
		fbButton.setBounds(20, 350, 100, 30);
		panel.add(fbButton);

		add(panel);
	}

	/**
	 * sets the speed of the mouse movement
	 * 
	 * @param med
	 *            - the medium of the movetime
	 * @param gaus
	 *            - the gausian of the movetime
	 */
	private void setMouseSpeed(int med, int gaus) {
		OutputManager.getInstance().setMouseSpeed(med, gaus);
	}
}
