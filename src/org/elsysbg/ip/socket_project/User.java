package org.elsysbg.ip.socket_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private Interactive interactive;
	private String username;
	private Integer count_of_entries = 0;
	private Integer currently_logged = 0;
	private List<Interval> visit_interval = new ArrayList<Interval>();
	
	public User(String user, Interactive interactive) {
		this.username = user;
		this.interactive = interactive;
	}
	
	public void new_user(String user) {
		this.username = user;
	}
	
	public Integer returnEntriesCount() {
		return count_of_entries;
	}
	
	public Integer isLogged() {
		return currently_logged;
	}
	
	public void logout() {
		this.currently_logged = 0;
		visit_interval.get(visit_interval.size()-1).setTo(new Date());
	}
	
	public void login() {
		this.currently_logged = 1;
		this.count_of_entries += 1;
		visit_interval.add(new Interval(new Date()));
	}
	
	public void getAllIntervals() {
		for (Interval interval : visit_interval) {
			interactive.msgOut(":"+interval.getFrom());
			try {
				interactive.msgOut(":"+interval.getTo());
			} catch(Exception e) {
				// Do nothing
			}
		}
	}
	
}
