package org.elsysbg.ip.socket_project;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Register {

	private static Map<String, User> users = new HashMap<>();
	
	public Register() {
		// TODO Auto-generated constructor stub
	}
	
	public void request_login(String username) {
		if (users.get(username) == null)
			users.put(username, new User(username));
			
		if (users.get(username).isLogged() != 1)
			users.get(username).login();
		
		System.out.println("ok");
	}
	
	public void request_logout(String username) {
		if (check_if_logged(username) == 0) 
			return;
		
		if (users.get(username).isLogged() == 0)
			return;
		
		try {
			users.get(username).logout();
		} catch (Exception e) {
			System.out.println("No such username.");
			return;
		}
		
		System.out.println("ok");
	}
	
	public Integer get_attendances(String username_checker, String username_checked) {
		if (users.get(username_checker).isLogged() == 0)
			return -1;
		
		try {
			return users.get(username_checked).returnEntriesCount();
		} catch (Exception e) {
			System.out.println("The checked username is not avilable");
		}
		return -1;

	}
	
	private Integer check_if_logged(String username) {
		return users.get(username).isLogged();
	}

	public void get_absent(String username) {
		if (users.get(username).isLogged() == 0) 
			return;
		
		System.out.print("ok");
		
		for (Entry<String, User> entry : users.entrySet()) {
			String key = entry.getKey();
			User value = entry.getValue();
			if (value.isLogged() == 0) {
				System.out.print(":" + key);
			}
		}
		
	}

	public void get_info(String username, String username_checked) {
		if (users.get(username).isLogged() == 0) {
			System.out.println("error:notlogged");
			return;
		}
		System.out.print("ok:" + username_checked + ":" + 
				username + ":" + users.get(username_checked).returnEntriesCount());
		users.get(username_checked).getAllIntervals();
		System.out.println();
		
	}
	
}
