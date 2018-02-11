package autoswitcher.image.recognition;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Obtains a screenshot of the OSBuddy Client
 * 
 * @author Damien
 *
 */
public class ProcessFinder {
	private String OSBuddyProcess = "OSBuddy";

	// Get OSBuddy Resolution

	// Bring forth the client (if is isn't already)

	// Screenshot the client

	// Write the screenshot somewhere

	/**
	 * gets all running processes from task manager
	 * 
	 * @return list of strings with all running processes
	 * @throws IOException
	 */
	public List<String> GetAllProcesses() throws IOException {
		List<String> processes = new ArrayList<String>();
		String line;

		Process p = Runtime.getRuntime().exec("tasklist.exe /nh");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			if (!line.trim().equals("")) {
				processes.add(line);
			}
		}
		return processes;
	}

	/**
	 * finds the osbuddy process id
	 * @return PID of osbuddy.exe
	 * @throws IOException
	 */
	public int FindOSBuddyProcessId() throws IOException {
		List<String> processes = GetAllProcesses();
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).startsWith(OSBuddyProcess)) {
				String OSBuddyProcess[] = processes.get(i).split("\\s+");
				return Integer.parseInt(OSBuddyProcess[1]);
			}
		}
		return 0;
	}
	
	private void GetScreenResolution() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	    short width = (short) size.getWidth();
	    short height = (short) size.getHeight();
	}
	
}
