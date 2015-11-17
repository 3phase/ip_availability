package org.elsysbg.ip.socket_project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reference {
	
	private Interactive interactive;
	private Map<String, String> userMeta = new HashMap<>();
	private Map<String, Runnable> funcMap = new HashMap<>();
	private Register studentRegister;
	
	public Reference(Interactive interactive) {
		funcMap.put("login", () -> login());
		funcMap.put("logout", () -> logout());
		funcMap.put("info", () -> info());
		funcMap.put("shutdown", () -> shutdown());
		funcMap.put("listabsent", () -> listabsent());
		this.interactive = interactive;
		this.studentRegister = new Register(interactive);
	}
	
	public void make_reference(Map<String, String> user) {
		this.userMeta = user;
		try {
			funcMap.get(userMeta.get("command")).run();
		} catch (Exception e) {
			interactive.msgOut("Make sure that you have passed a valid argument.\n");
		}
	}
	
	private void shutdown() {
		try {
			interactive.stopClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
