package org.elsysbg.ip.socket_project;

public class Main {	 
	
	private final static Integer PORT_NUMBER = 31000;
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(PORT_NUMBER);
		server.startServer();
	}
	
}