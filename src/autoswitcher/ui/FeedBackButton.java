package autoswitcher.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FeedBackButton extends JButton{
	
	public FeedBackButton() {
		setText("Feedback");
		setBorder(null);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FeedBackFrame();
			}
		});
	}

}
