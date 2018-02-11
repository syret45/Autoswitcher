package autoswitcher.input;

import java.awt.AWTException;
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

/**
 * this will get the input from the player and decides what to do with it<br>
 * this works with the jni
 * 
 * @author No
 *
 */
public class InputManager implements NativeMouseListener, NativeMouseMotionListener, NativeKeyListener {

	private static InputManager instance;
	// options
	private static boolean returnMouse = false;
	private static boolean noDrag = false;
	private static boolean f4Barrage = false;

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
	
	public void setF4Barrage(boolean active){
		f4Barrage = active;
	}
	
	public void setReturnMouse(boolean active){
		returnMouse = active;
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

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
		if(f4Barrage && e.getID() == NativeKeyEvent.VC_F4){
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
			OutputManager.getInstance().fastleftRelease();
		}
	}
	
	private void dof4Barrage(){
		//TODO make this
	}

}
