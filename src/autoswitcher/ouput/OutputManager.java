package autoswitcher.ouput;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

/**
 * This can do all the output for this program made to be as humanlike as
 * possible
 * 
 * @author No
 *
 */
public class OutputManager extends Robot {

	private static OutputManager instance;
	private int mouseSpeedMedMs = 100;
	private int mouseSpeedGaus = 10;
	private MovementType movementType = MovementType.straight;
	private Random random;

	private OutputManager() throws AWTException {
		super();
		random = new Random();
	}

	/**
	 * gets the instance of the outputManager
	 * 
	 * @return {@link outputManager}
	 * @throws AWTException
	 */
	public static OutputManager getInstance() {
		if (instance == null) {
			try {
				instance = new OutputManager();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * moves the mouse to the specified point. it uses a small delay for the
	 * movement and makes it as human as possible
	 * 
	 * @param movePoint
	 *            - the point to move the mouse to
	 */
	public void moveMouseNormal(Point movePoint) {
		Point oldMousePosition = MouseInfo.getPointerInfo().getLocation();
		long startSystemTime = System.currentTimeMillis();
		// calculates the distance between the points
		double distance = oldMousePosition.distance(movePoint);
		double xdiff = (movePoint.x - oldMousePosition.x) / distance;
		double ydiff = (movePoint.y - oldMousePosition.y) / distance;
		int movementDeviation = (int) (Math.abs(random.nextGaussian() * 25) - 10);
		int timeToMoveInMs = mouseSpeedMedMs + (int) random.nextGaussian() * mouseSpeedGaus;
		MovementType tempMove = movementType;
		// gets the movementType
		if (tempMove == MovementType.random) {
			switch (random.nextInt(2)) {
			case 0:
				tempMove = MovementType.straight;
				break;

			case 1:
				tempMove = MovementType.circle;
				break;
			case 2:
				tempMove = MovementType.sinus;
				break;
			}
		}
		// moves the mouse accordingly
		for (int i = 0; i < distance; i++) {
			switch (tempMove) {
			case straight:
				mouseMove((int) (oldMousePosition.x + i * xdiff), (int) (oldMousePosition.y + i * ydiff));
				break;
			case circle:
				double rad = distance / 2;
				double startAngle = getAngle(movePoint, oldMousePosition);
				mouseMove((int) (oldMousePosition.x + (xdiff * distance / 2) + Math.cos(Math.toRadians(startAngle + (i / distance) * 180)) * rad),
						(int) (oldMousePosition.y + (ydiff * distance / 2) + Math.sin(Math.toRadians(startAngle + (i / distance) * 180)) * rad));
				break;
			case sinus:
				mouseMove((int) (oldMousePosition.x + xdiff * i + Math.sin(i / distance * Math.PI) * movementDeviation),
						(int) (oldMousePosition.y + ydiff * i + Math.sin(i / distance * Math.PI) * movementDeviation));
				break;
			default:
				break;
			}
			long timeElapsed = System.currentTimeMillis() - startSystemTime;
			// waits till the movement is on track with the timing again
			while (timeElapsed < timeToMoveInMs * (i / distance)) {
				timeElapsed = System.currentTimeMillis() - startSystemTime;
			}

		}
		// this last mousemove is to make sure it goes to the right place
		mouseMove(movePoint.x, movePoint.y);
	}
	
	/**
	 * sets the mouse speed for all mouse movements
	 * @param medMS - medium for the mousespeed
	 * @param gaus - the gausian for the mousespeed
	 */
	public void setMouseSpeed(int medMS , int gaus){
		mouseSpeedMedMs = medMS;
		mouseSpeedGaus = gaus;
	}
	
	/**
	 * sets the movement type for all mouse movements
	 * @param movementtype - the type of movement {@link MovementType}
	 */
	public void setMovementType(MovementType movementtype){
		this.movementType = movementtype;
	}

	/**
	 * calculates the angle between two points
	 * 
	 * @param target
	 *            the point where the mouse needs to go
	 * @param current
	 *            the point where the mouse is currently
	 * @return
	 */
	private float getAngle(Point target, Point current) {
		float angle = (float) Math.toDegrees(Math.atan2(current.y - target.y, current.x - target.x));
		return angle;
	}

	/**
	 * left click on a normal delay between press and release<br>
	 * delay: 10 + |gaus * 5|
	 * 
	 */
	public void leftClickNormal() {
		mousePress(InputEvent.BUTTON1_MASK);
		delay((int) (10 + Math.abs(random.nextGaussian() * 5)));
		mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * left click without a delay, tries to release as soon as possible
	 */
	public void fastleftClick() {
		mousePress(InputEvent.BUTTON1_MASK);
		mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * releases left click button
	 */
	public void fastleftRelease() {
		mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * right click on a normal delay between press and release<br>
	 * delay: 10 + |gaus * 5|
	 * 
	 */
	public void rightClickNormal() {
		mousePress(InputEvent.BUTTON2_MASK);
		delay((int) (10 + Math.abs(random.nextGaussian() * 5)));
		mouseRelease(InputEvent.BUTTON2_MASK);
	}

	/**
	 * right click without a delay, tries to release as soon as possible
	 */
	public void fastrightClick() {
		mousePress(InputEvent.BUTTON2_MASK);
		mouseRelease(InputEvent.BUTTON2_MASK);
	}

}
