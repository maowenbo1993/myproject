package com.mao.domain;

import java.util.List;

import com.mao.util.DateFormat;

public class Message {
	
	public Message() {
		date = new DateFormat().toString();
	}
	
	private List<String> users;
	private String from;
	private String date;
	private String alert;
	private String message;
	
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
