package autoswitcher.connection;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Connector implements Runnable {

	private static Connector instance;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ArrayList<Object> nulledPackets = new ArrayList<>();
	public String username;

	private Connector() {
		username = getUser();
		if (connectToServer()) {
			new Thread(this).start();
		} else {
			reconnect();
		}
	}

	public static Connector getInstance() {
		if (instance == null) {
			instance = new Connector();
		}
		return instance;
	}

	private boolean connectToServer() {
		try {
			socket = new Socket("84.25.19.170", 50000);
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			for (Object object : nulledPackets) {
				SendPacket(object);
			}
			return true;
		} catch (IOException e) {
			System.out.println("Cant connect");
			return false;
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object read = input.readObject();
				checkRead(read);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				try {
					socket.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		reconnect();
	}

	/**
	 * deletes this instance and tries to reconnect
	 */
	private void reconnect() {
		try {
			Thread.sleep(1000);
			System.out.println("Trying to reconnect");
			instance = null;
			getInstance();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * send a packet
	 * 
	 * @param object
	 */
	public void SendPacket(Object object) {
		if (output != null) {
			try {
				System.out.println(object.getClass().getName());
				output.writeObject(object);
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			nulledPackets.add(object);
		}
	}

	/**
	 * check on which packet has been received
	 * 
	 * @param read
	 */
	private void checkRead(Object read) {
		switch (read.getClass().getName()) {
		case "com.gmail.nomoonen.toxic.teamwork.packets.launcher.LauncherVersionPacket":
			// dealWithLauncherVersionPacket((LauncherVersionPacket) read);
			break;
		default:
			break;
		}
	}

	private String getUser() {
		List<String> lines = readLinesFromTxtFile(new File(System.getProperty("user.home") + "/TTLauncher/Programs/Program/User.txt"));
		if (lines.get(0) != null) {
			return lines.get(0);
		}
		return null;
	}

	/**
	 * reads all the lines from a txt file
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readLinesFromTxtFile(File file) {
		List<String> versionLines = null;
		if (file.exists()) {
			try {
				versionLines = Files.readAllLines(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return versionLines;
	}
}
