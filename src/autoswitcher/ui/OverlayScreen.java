package autoswitcher.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JWindow;

import autoswitcher.input.InputManager;


public class OverlayScreen extends JWindow implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static OverlayScreen instance;
	private int mx, my;

	public OverlayScreen() {
		// setGraphics();
		setSize(500,500);
		setBackground(new Color(0, 255, 0, 0));
		setVisible(true);
		setAlwaysOnTop(true);
		addMouseMotionListener(this);
		repaint();
		instance = this;
	}

	public static OverlayScreen getInstance(){
		if(instance == null){
			instance = new OverlayScreen();;
		}
		return instance;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//draw inv
		g.setColor(Color.green);
		
	    g.drawRect(0, 0, 10, 10);
	    g.fillRect(0, 0, 10, 10);
	    
	    InputManager.getInstance();
		for(int y = 0; y < InputManager.gridHeight; y++){
	    	for(int x = 0 ; x < InputManager.gridWidth ; x++){
	    		g.drawRect(x * 43, y * 36, 43, 36);
	    	}
	    }
	    /*
		int i;
		int width = 175;
	    int height = 250;
	    int rows = 3;
	    int cols = 4;
	    
	    // draw the rows
	    int rowHt = 36;// height / (rows);
	    for (i = 0; i <= rows; i++)
	      g.drawLine(0, i * rowHt, width, i * rowHt);

	    // draw the columns
	    int rowWid = 43;//width / (cols);
	    for (i = 0; i <= cols; i++)
	      g.drawLine(i * rowWid, 0, i * rowWid, height);*/
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = this.getLocation();
		p.x += e.getXOnScreen() - mx;
		p.y += e.getYOnScreen() - my;
		mx = e.getXOnScreen();
		my = e.getYOnScreen();
		InputManager.overLayLocation = p;
		this.setLocation(p);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getXOnScreen();
		my = e.getYOnScreen();
	}

}
