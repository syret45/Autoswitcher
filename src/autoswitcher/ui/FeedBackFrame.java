package autoswitcher.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import autoswitcher.connection.Connector;
import toxic.teamwork.packets.FeedBackPacket;

@SuppressWarnings("serial")
public class FeedBackFrame extends JFrame {

	private FeedBackFrame instance;

	public FeedBackFrame() {
		initFrame();
		instance = this;
		initPanels();
		setVisible(true);
	}

	private void initFrame() {
		setTitle(" Autoswitcher- feedback");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

	}

	private void initPanels() {
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.BLACK);
		add(panel);

		JTextArea area = new JTextArea("Feedback:");
		area.setForeground(Color.BLACK);
		area.setBounds(10, 10, 500 - 20, 200);
		panel.add(area);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				instance.dispose();
			}
		});
		closeButton.setBounds(250, 220, 100, 30);
		panel.add(closeButton);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FeedBackPacket packet = new FeedBackPacket();
				packet.program = "Autoswitcher";
				packet.username = Connector.getInstance().username;
				packet.feedback = area.getText();
				Connector.getInstance().SendPacket(packet);
				instance.dispose();
			}
		});
		sendButton.setBounds(50, 220, 100, 30);
		panel.add(sendButton);
	}

}
