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
		
		int i;
		int width = 175;
	    int height = 250;
	    int rows = 7;
	    int cols = 4;
	    
	    // draw the rows
	    int rowHt = height / (rows);
	    for (i = 0; i <= rows; i++)
	      g.drawLine(0, i * rowHt, width, i * rowHt);

	    // draw the columns
	    int rowWid = width / (cols);
	    for (i = 0; i <= cols; i++)
	      g.drawLine(i * rowWid, 0, i * rowWid, height);
	    g.drawRect(0, 0, 10, 10);
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
