package autoswitcher.input;

/**
 * this will get the input from the player and decides what to do with it<br>
 * this works with the jni
 * @author No
 *
 */
public class InputManager {
	
	private static InputManager instance;
	//options
	private static boolean noDrag = false;
	
	private InputManager(){
		
	}
	
	/**
	 * gets the instance of the inputmanager
	 * @return instance of {@link InputManager}
	 */
	public static InputManager getInstance(){
		if(instance == null){
			instance = new InputManager();
		}
		return instance;
	}
	
	public void setNoDrag(boolean active){
		noDrag = active;
	}

}
