package org.elsysbg.ip.socket_project;

import java.util.HashMap;
import java.util.Map;

public class Parse {

	private String stringToParse;
	private Map<String, String> userMeta = new HashMap<>();
	Interactive interactive;
	
	public Parse(Interactive interactive) {
		this.interactive = interactive;
	}
	
	public Map<String, String> parse(String s) throws Exception {
		this.stringToParse = s;
		this.split(); 
		return userMeta;
	}
	
	private void split() throws Exception {
		String[] parts;
		try {
			parts = stringToParse.split(":");
			userMeta.put("command", parts[0]);
			userMeta.put("username", parts[1]);
		} catch (Exception e) {
			interactive.msgOut("The format of the arguments is invalid");
			return;
		}
		if (parts.length > 2 && parts[2] != null)
			userMeta.put("checked_username", parts[2]);
	}
	
}