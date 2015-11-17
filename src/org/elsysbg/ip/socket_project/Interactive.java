package org.elsysbg.ip.socket_project;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interactive implements Runnable {

	private final Socket socket;
	private final Server server;
	
	private Scanner scanner;
	private PrintStream out;
	
	private Map<String, String> userMeta = new HashMap<>();
	private Parse parser = new Parse(this);
	private Reference r = new Reference(this);
	
	public Interactive(Server server, Socket socket) {
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			out = new PrintStream(socket.getOutputStream());
			scanner = new Scanner(socket.getInputStream());
			out.println("Enter command: ");
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				try {
					userMeta = parser.parse(line);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if ("shutdown".equals(line)) {
					server.stopServer();
					break;
				}
				r.make_reference(userMeta);
			}
			scanner.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Shit");
			e.printStackTrace();
		}
	}

	public void stopClient() throws IOException {
		socket.close();
	}
	
	public void msgOut(String msg) {
		out.print(msg);
	}
	
}
