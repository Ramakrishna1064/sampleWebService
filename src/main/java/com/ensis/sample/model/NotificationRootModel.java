/**
 * 
 */
package com.ensis.sample.model;

import java.util.ArrayList;

/**
 * @author Venu
 *
 */
public class NotificationRootModel {

	private ArrayList<String> registration_ids;
	private String priority;
	private NotificationMessageModel notification;
	
	
	public ArrayList<String> getRegistration_ids() {
		return registration_ids;
	}
	public void setRegistration_ids(ArrayList<String> registration_ids) {
		this.registration_ids = registration_ids;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public NotificationMessageModel getNotification() {
		return notification;
	}
	public void setNotification(NotificationMessageModel notification) {
		this.notification = notification;
	}
}
