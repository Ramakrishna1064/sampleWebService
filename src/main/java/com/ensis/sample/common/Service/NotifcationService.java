package com.ensis.sample.common.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ensis.sample.dto.UserNotificationDTO;
import com.ensis.sample.model.NotificationMessageModel;
import com.ensis.sample.model.NotificationRootModel;
import com.ensis.sample.utils.Constants;
import com.ensis.sample.utils.MessageResources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class NotifcationService extends MessageResources {
	
	/**
	 * 
	 * @param name
	 * @param devicesList
	 * @param treatmentQuestionId
	 */
	public void sendCloseTreatmentNotification(ArrayList<UserNotificationDTO> devicesList) {

		try {
			sendCommonNotification(devicesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 
	 * @param notificationQuestionModel
	 * @param devicesList
	 */
	public void sendCommonNotification(ArrayList<UserNotificationDTO> devicesList) {

		try {

			System.out.println("-devices size--->"+devicesList.size());
			ArrayList<String> androidGcmList=new ArrayList<String>();			
			for (UserNotificationDTO ideviceListObject : devicesList) {
				System.out.println("-devices gcm id--->"+ideviceListObject.getGcmId());
				androidGcmList.add(ideviceListObject.getGcmId());
			}
			
			//Android Notification
			if (androidGcmList.size() > 0) {
				
				NotificationMessageModel childObj=new NotificationMessageModel();
				childObj.setId(10);
				childObj.setMessage("Hello Ramakrishna Welcome");
				
				NotificationRootModel rootObj = new NotificationRootModel();
				rootObj.setPriority("high");
				rootObj.setRegistration_ids(androidGcmList);
				rootObj.setNotification(childObj);
								
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String message = ow.writeValueAsString(rootObj);
				System.out.println("---------final---android"+message);
				
				sendNotificationtoDevice(message,Constants.SAMPLE_APP_ANDROID_GCM_API_KEY);
							
			}
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * sendNotificationtoDevice
	 * @param json
	 */
	public void sendNotificationtoDevice(String json,String apiKey) {

		try {

			URL obj = new URL(Constants.FCM_SERVER_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "key="+apiKey);

			if (json != null) {

				con.setDoOutput(true);
				OutputStream os = con.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				osw.write(json.toString());
				osw.flush();
				osw.close();
			}

			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
}
