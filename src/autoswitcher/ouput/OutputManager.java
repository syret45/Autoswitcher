package autoswitcher.ouput;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;


/**
 * This can do all the output for this program
 * made to be as humanlike as possible
 * @author No
 *
 */
public class OutputManager extends Robot {

	private static OutputManager instance;

	private OutputManager() throws AWTException {
		super();
	}

	/**
	 * gets the instance of the outputManager
	 * @return {@link outputManager}
	 * @throws AWTException
	 */
	public static OutputManager getInstance() throws AWTException {
		if (instance == null) {
			instance = new OutputManager();
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
	 * @param movementType
	 *            -{@link MovementType} for the desired movementype
	 */
	public void moveMouseNormal(Point movePoint, int timeToMoveInMs, MovementType movementType) {
		Point oldMousePosition = MouseInfo.getPointerInfo().getLocation();
		long startSystemTime = System.currentTimeMillis();
		// calculates the distance between the points
		double distance = oldMousePosition.distance(movePoint);
		double xdiff = (movePoint.x - oldMousePosition.x) / distance;
		double ydiff = (movePoint.y - oldMousePosition.y) / distance;
		for (int i = 0; i < distance; i++) {
			mouseMove((int) (oldMousePosition.x + i * xdiff), (int) (oldMousePosition.y + i * ydiff));
			long timeElapsed = System.currentTimeMillis() - startSystemTime;
			// waits till the movement is on track with the timing again
			while (timeElapsed < timeToMoveInMs * (i / distance)) {
				timeElapsed = System.currentTimeMillis() - startSystemTime;
			}

		}
		// this last mousemove is to make sure it goes to the right place
		mouseMove(movePoint.x, movePoint.y);
	}

}
