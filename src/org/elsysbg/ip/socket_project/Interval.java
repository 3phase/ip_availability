package org.elsysbg.ip.socket_project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {

	private Date from;
	private Date to;
	
	private final String PATTERN = "yyyy­MM­dd'T'HH'_'mm'_'ss.SSSZ";
	
	public Interval(Date fromDate) {
		this.from = fromDate;
	}
	
	public void setTo(Date toDate) {
		this.to = toDate;
	}
	
	private String applyPattern(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);
		return DATE_FORMAT.format(date);
	}
	
	public String getFrom() {
		return applyPattern(this.from);
	}
	
	public String getTo() {
		return applyPattern(this.to);
	}
	
}
