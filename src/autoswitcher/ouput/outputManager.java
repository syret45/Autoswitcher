package autoswitcher.ouput;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class outputManager extends Robot {

	private static outputManager instance;

	private outputManager() throws AWTException {
		super();
	}

	public static outputManager getInstance() throws AWTException {
		if (instance == null) {
			instance = new outputManager();
		}
		return instance;
	}

	/**
	 * moves the mouse to the specified point. it uses a small delay for the
	 * movement and makes it as human as possible
	 * 
	 * @param movePoint
	 *            - the point to move the mouse to
	 * @param timeToMoveInMs
	 *            - The time the movement of the mouse should take
	 */
	public void moveMouseNormal(Point movePoint, int timeToMoveInMs) {
		Point oldMousePosition = MouseInfo.getPointerInfo().getLocation();
		long startSystemTime = System.currentTimeMillis();
		// calculates the distance between the points
		double distance = oldMousePosition.distance(movePoint);
		double xdiff = (movePoint.x - oldMousePosition.x) / distance;
		double ydiff = (movePoint.y - oldMousePosition.y) / distance;
		for (int i = 0; i < distance; i++) {
			mouseMove((int) (oldMousePosition.x + i * xdiff), (int) (oldMousePosition.y + i * ydiff));
			long timeElapsed = System.currentTimeMillis() - startSystemTime;
			//waits till the movement is on track with the timing again
			while(timeElapsed < timeToMoveInMs * (i / distance)){
				timeElapsed = System.currentTimeMillis() - startSystemTime;
			}
			
		}
		// this last mousemove is to make sure it goes to the right place
		mouseMove(movePoint.x, movePoint.y);
	}

}
