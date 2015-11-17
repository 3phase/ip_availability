package org.elsysbg.ip.socket_project;

import java.util.HashMap;
import java.util.Map;

public class Reference {
	
	private Map<String, String> userMeta = new HashMap<>();
	private Map<String, Runnable> funcMap = new HashMap<>();
	private Register studentRegister = new Register();
	
	public Reference() {
		funcMap.put("login", () -> login());
		funcMap.put("logout", () -> logout());
		funcMap.put("info", () -> info());
		funcMap.put("shutdown", () -> shutdown());
		funcMap.put("listabsent", () -> listabsent());
		
	}
	
	public Reference(Interactive interactive) {
		// TODO Auto-generated constructor stub
	}

	public void make_reference(Map<String, String> user) {
		this.userMeta = user;
		try {
			funcMap.get(userMeta.get("command")).run();
		} catch (Exception e) {
			System.out.println("Make sure that you have passed a valid argument. " + e);
		}
	}
	
	private void shutdown() {
		// TODO Auto-generated method stub
	}

	private void info() {
		studentRegister.get_info(userMeta.get("username"), userMeta.get("checked_username"));
	}


	private void logout() {
		studentRegister.request_logout(userMeta.get("username"));
	}


	private void login() {
		studentRegister.request_login(userMeta.get("username"));
	}
	
	private void listabsent() {
		studentRegister.get_absent(userMeta.get("username"));
	}

}
