package autoswitcher.input;

public class InputManager {
	
	private static InputManager instance;
	
	private InputManager(){
		
	}
	
	/**
	 * gets the instance of the inputmanager
	 * @return instance of {@link InputManager}
	 */
	public InputManager getInstance(){
		if(instance == null){
			instance = new InputManager();
		}
		return instance;
	}

}
