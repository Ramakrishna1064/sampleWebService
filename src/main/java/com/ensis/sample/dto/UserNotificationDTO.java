/**
 * 
 */
package com.ensis.sample.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Venu
 *
 */
@Entity
@Table(name = "usernotification")
public class UserNotificationDTO {

	@Id
	private int userId;
	private String deviceId;
	private String gcmId;
	
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the gcmId
	 */
	public String getGcmId() {
		return gcmId;
	}
	/**
	 * @param gcmId the gcmId to set
	 */
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}

}
