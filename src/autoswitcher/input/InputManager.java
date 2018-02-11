package autoswitcher.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

import autoswitcher.ouput.OutputManager;
import autoswitcher.switcher.SwitchManager;

/**
 * this will get the input from the player and decides what to do with it<br>
 * this works with the jni
 * 
 * @author No
 *
 */
public class InputManager implements NativeMouseListener, NativeMouseMotionListener, NativeKeyListener {

	private static InputManager instance;
	private SwitchManager switchManager = SwitchManager.getInstance();
	private OutputManager outputManager = OutputManager.getInstance();
	// options
	public static Point overLayLocation;
	private static boolean returnMouse = false;
	private static boolean noDrag = false;
	private static boolean f4Barrage = false;
	private static boolean autoSwitch = false;

	private InputManager() {
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeMouseListener(this);
			GlobalScreen.addNativeKeyListener(this);
			GlobalScreen.addNativeMouseMotionListener(this);
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets the instance of the inputmanager
	 * 
	 * @return instance of {@link InputManager}
	 */
	public static InputManager getInstance() {
		if (instance == null) {
			instance = new InputManager();
		}
		return instance;
	}

	public void setNoDrag(boolean active) {
		noDrag = active;
	}

	public void setF4Barrage(boolean active) {
		f4Barrage = active;
	}

	public void setReturnMouse(boolean active) {
		returnMouse = active;
	}

	public void setAutoswitch(boolean active) {
		autoSwitch = active;
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		if (autoSwitch && e.getButton() == NativeMouseEvent.BUTTON1) {
			switchManager.trySwitch(e.getPoint(),returnMouse);
		}
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if (f4Barrage && e.getKeyCode() == NativeKeyEvent.VC_F4) {
			dof4Barrage();
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		if (noDrag) {
			doNoDrag(e);
		}
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * checks if the nodrag should activate and releases left click when it
	 * should
	 * 
	 * @param e
	 *            - the mouseEvent
	 */
	private void doNoDrag(NativeMouseEvent e) {
		// TODO checks for when to nodrag
		if (e.getModifiers() == 256) {
			outputManager.fastleftRelease();
		}
	}

	/**
	 * clicks on the barrage when you click f4
	 */
	private void dof4Barrage() {
		Point oldMouse = null;
		if (returnMouse) {
			oldMouse = MouseInfo.getPointerInfo().getLocation();
		}
		int barX = (int) (Math.random() * 15) + 13 + overLayLocation.x;
		int barY = (int) (Math.random() * 15) + 173 + overLayLocation.y;
		outputManager.moveMouseNormal(new Point(barX, barY));
		outputManager.leftClickNormal();
		if (oldMouse != null) {
			outputManager.moveMouseNormal(oldMouse);
		}
	}

}
