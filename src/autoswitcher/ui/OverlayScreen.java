package autoswitcher.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JWindow;


public class OverlayScreen extends JWindow implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private int mx, my;

	public OverlayScreen() {
		// setGraphics();
		setSize(300,300);
		setBackground(new Color(0, 255, 0, 0));
		setVisible(true);
		setAlwaysOnTop(true);
		addMouseMotionListener(this);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//draw inv
		g.setColor(Color.green);
		g.fillRect(0, 0, 10, 10);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = this.getLocation();
		p.x += e.getXOnScreen() - mx;
		p.y += e.getYOnScreen() - my;
		mx = e.getXOnScreen();
		my = e.getYOnScreen();
		this.setLocation(p);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getXOnScreen();
		my = e.getYOnScreen();
	}

}
