package autoswitcher.image.recognition;

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
	
	//Get all processes
	
	//Find client process
	
	//Get OSBuddy Resolution
	
	//Bring forth the client (if is isn't already)
	
	//Screenshot the client
	
	//Write the screenshot somewhere
	
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

	public int FindOSBuddyProcessId(List<String> processes) {	
		for(int i = 0; i < processes.size(); i++) 
		{
			if(processes.get(i).startsWith(OSBuddyProcess)) 
			{
				String OSBuddyProcess[] = processes.get(i).split("\\s+");
				return Integer.parseInt(OSBuddyProcess[1]);
			}
		}
		return 0;			
	}
}
