package org.elsysbg.ip.socket_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

	private static Integer PORT_NUMBER;
	private static Integer running = 0;
	private ServerSocket serverSocket;
	private List<Interactive> clients = new LinkedList<Interactive>();
	
	public Server(Integer port) {
		Server.PORT_NUMBER = port;
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(31000);
		server.startServer();
	}
	
	public void startServer() throws Exception {
		final ServerSocket localServerSocket = createServerSocket();
		
		while (running == 1) {
			final Socket socket;
			try {
				socket = localServerSocket.accept();
			} catch (Exception e) {
				if (running != 0) 
					throw e;
				break;
			}
			
			final Interactive client = new Interactive(this, socket);
			clients.add(client);
			client.run();
		}
		
	}
	
	public synchronized void stopServer() throws IOException {
		if (running != 1) {
			throw new IllegalStateException("Server not running");
		}

		running = 0;
		
		serverSocket.close();
		serverSocket = null;
		
		for (Interactive next : clients) {
			next.stopClient();
		}
	}
	
	private ServerSocket createServerSocket() throws IOException {
		if (running == 1)
			throw new IllegalStateException("Server already running");
		
		running = 1;
		serverSocket = new ServerSocket(PORT_NUMBER);
		return serverSocket;
	}
	
}
