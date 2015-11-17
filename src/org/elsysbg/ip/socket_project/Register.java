package org.elsysbg.ip.socket_project;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Register {

	private Interactive interactive;
	private static Map<String, User> users = new HashMap<>();
	
	public Register(Interactive interactive) {
		this.interactive = interactive;
	}
	
	public void request_login(String username) {
		if (users.get(username) == null)
			users.put(username, new User(username, interactive));
			
		if (users.get(username).isLogged() != 1)
			users.get(username).login();
		
		interactive.msgOut("ok\n");
	}
	
	public void request_logout(String username) {
		if (check_if_logged(username) == 0) 
			return;
		
		if (users.get(username).isLogged() == 0)
			return;
		
		try {
			users.get(username).logout();
		} catch (Exception e) {
			interactive.msgOut("No such username.\n");
			return;
		}
		
		interactive.msgOut("ok\n");
	}
	
	public Integer get_attendances(String username_checker, String username_checked) {
		if (users.get(username_checker).isLogged() == 0)
			return -1;
		
		try {
			return users.get(username_checked).returnEntriesCount();
		} catch (Exception e) {
			interactive.msgOut("The checked username is not avilable\n");
		}
		return -1;

	}
	
	private Integer check_if_logged(String username) {
		return users.get(username).isLogged();
	}

	public void get_absent(String username) {
		if (users.get(username).isLogged() == 0) 
			return;
		
		interactive.msgOut("ok\n");
		
		for (Entry<String, User> entry : users.entrySet()) {
			String key = entry.getKey();
			User value = entry.getValue();
			if (value.isLogged() == 0) {
				interactive.msgOut(":" + key);
			}
		}
		
	}

	public void get_info(String username, String username_checked) {
		if (users.get(username).isLogged() == 0) {
			interactive.msgOut("error:notlogged\n");
			return;
		}
		interactive.msgOut("ok:" + username_checked + ":" + 
				username + ":" + users.get(username_checked).returnEntriesCount());
		users.get(username_checked).getAllIntervals();
		interactive.msgOut("\n");
		
	}
	
}
