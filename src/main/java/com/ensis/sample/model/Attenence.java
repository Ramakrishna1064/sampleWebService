package com.ensis.sample.model;

import java.util.List;

public class Attenence {

	private String addr;
	private String city;
	private String state;
	private List<Staff>members;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Staff> getMembers() {
		return members;
	}
	public void setMembers(List<Staff> members) {
		this.members = members;
	}
}
