package autoswitcher.image.recognition;

import java.util.ArrayList;

import autoswitcher.switcher.SwitchItem;

public class ItemFinder implements Runnable{
	
	private static ItemFinder instance;

	private ArrayList<SwitchItem> items = new ArrayList<>();

	/**
	 * gets the instance of the switchmanager
	 * 
	 * @return instance
	 */
	public static ItemFinder getInstance() {
		if (instance == null) {
			instance = new ItemFinder();
			new Thread(instance).start();
		}
		return instance;
	}
	
	public void addItem(SwitchItem item){
		items.add(item);
	}

	@Override
	public void run() {
		while(true){
			try {
				for (SwitchItem item : items) {
					if(item.getItemName().equals("dragon axe")){
						item.setPos(0);
					}
					if(item.getItemName().equals("bruma root")){
						item.setPos(1);
					}
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
