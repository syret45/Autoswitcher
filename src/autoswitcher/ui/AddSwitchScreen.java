package autoswitcher.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import autoswitcher.switcher.FullSwitch;
import autoswitcher.switcher.SwitchItem;
import autoswitcher.switcher.SwitchManager;

public class AddSwitchScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private Dimension ScreenSize = new Dimension(300, 350);
	private String Title = "OSRS autoSwitcher";

	/**
	 * constructer it will make all the components on the screen and make the
	 * screen visible
	 */
	public AddSwitchScreen() {
		setSize(ScreenSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(Title);
		setResizable(false);
		addComponents();
		setVisible(true);
	}

	private void addComponents() {

		JPanel panel = new JPanel(null);
		// all labels
		JLabel item1label = new JLabel("Item 1");
		item1label.setBounds(20, 20, 100, 20);
		panel.add(item1label);

		JLabel item2label = new JLabel("Item 2");
		item2label.setBounds(20, 50, 100, 20);
		panel.add(item2label);

		JLabel item3label = new JLabel("Item 3");
		item3label.setBounds(20, 80, 100, 20);
		panel.add(item3label);

		JLabel item4label = new JLabel("Item 4");
		item4label.setBounds(20, 110, 100, 20);
		panel.add(item4label);

		JLabel item5label = new JLabel("Item 5");
		item5label.setBounds(20, 140, 100, 20);
		panel.add(item5label);

		JLabel item6label = new JLabel("Item 6");
		item6label.setBounds(20, 170, 100, 20);
		panel.add(item6label);

		JLabel item7label = new JLabel("Item 7");
		item7label.setBounds(20, 200, 100, 20);
		panel.add(item7label);

		JLabel item8label = new JLabel("Item 8");
		item8label.setBounds(20, 230, 100, 20);
		panel.add(item8label);

		// all comboboxes
		JComboBox<String> item1Combo = new JComboBox<String>();
		item1Combo.setBounds(80, 20, 200, 20);
		panel.add(item1Combo);

		JComboBox<String> item2Combo = new JComboBox<String>();
		item2Combo.setBounds(80, 50, 200, 20);
		panel.add(item2Combo);

		JComboBox<String> item3Combo = new JComboBox<String>();
		item3Combo.setBounds(80, 80, 200, 20);
		panel.add(item3Combo);

		JComboBox<String> item4Combo = new JComboBox<String>();
		item4Combo.setBounds(80, 110, 200, 20);
		panel.add(item4Combo);

		JComboBox<String> item5Combo = new JComboBox<String>();
		item5Combo.setBounds(80, 140, 200, 20);
		panel.add(item5Combo);

		JComboBox<String> item6Combo = new JComboBox<String>();
		item6Combo.setBounds(80, 170, 200, 20);
		panel.add(item6Combo);

		JComboBox<String> item7Combo = new JComboBox<String>();
		item7Combo.setBounds(80, 200, 200, 20);
		panel.add(item7Combo);

		JComboBox<String> item8Combo = new JComboBox<String>();
		item8Combo.setBounds(80, 230, 200, 20);
		panel.add(item8Combo);

		for (int i = 0; i < SwitchItem.allItemNames.size(); i++) {
			item1Combo.addItem(SwitchItem.allItemNames.get(i));
			item2Combo.addItem(SwitchItem.allItemNames.get(i));
			item3Combo.addItem(SwitchItem.allItemNames.get(i));
			item4Combo.addItem(SwitchItem.allItemNames.get(i));
			item5Combo.addItem(SwitchItem.allItemNames.get(i));
			item6Combo.addItem(SwitchItem.allItemNames.get(i));
			item7Combo.addItem(SwitchItem.allItemNames.get(i));
			item8Combo.addItem(SwitchItem.allItemNames.get(i));
		}

		JButton addSwitchButton = new JButton("Add switch");
		addSwitchButton.setBounds(80, 260, 100, 20);
		addSwitchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!item1Combo.getSelectedItem().equals(SwitchItem.Empty.getItemName())) {
					FullSwitch s = new FullSwitch();
					s.addItem(getItemFromName((String) item1Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item2Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item3Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item4Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item5Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item6Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item7Combo.getSelectedItem()));
					s.addItem(getItemFromName((String) item8Combo.getSelectedItem()));
					SwitchManager.getInstance().addSwitch(s);
				}
			}
		});
		panel.add(addSwitchButton);

		add(panel);

	}

	private SwitchItem getItemFromName(String name) {
		for (SwitchItem item : SwitchItem.allItems) {
			if (item.getItemName().equals(name)) {
				return item;
			}
		}
		return SwitchItem.Empty;
	}

}
