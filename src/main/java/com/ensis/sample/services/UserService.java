package com.ensis.sample.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ensis.sample.dao.UsersDao;
import com.ensis.sample.dto.Address;
import com.ensis.sample.dto.Country;
import com.ensis.sample.dto.Members;
import com.ensis.sample.dto.States;
import com.ensis.sample.dto.UserNotificationDTO;
import com.ensis.sample.dto.Users;
import com.ensis.sample.model.Attenence;
import com.ensis.sample.model.InsertStudentData;
import com.ensis.sample.model.SampleNotificationModel;
import com.ensis.sample.model.Staff;
import com.ensis.sample.model.StatusObject;
import com.ensis.sample.model.StudentData;
import com.ensis.sample.model.UploadImageModel;
import com.ensis.sample.model.UserModel;
import com.ensis.sample.utils.MessageResources;


@Service
public class UserService extends MessageResources{

	@Autowired
	UsersDao usersdao;
	
	public void setUsersdao(UsersDao usersdao) {
		this.usersdao = usersdao;
	}

	
	/**
	 * setTransactionObject
	 * @return
	 */
	@Transactional
	public List<?> setTransactionObject(){
		return usersdao.getListOfStudents();
	}
	

	/**
	 * setTransactionObjectById
	 * @return
	 */
	@Transactional
	public List<?> setTransactionObjectById(UserModel userModel){
		return usersdao.getListOfStiudentById(userModel);
	}
	
	
	/**
	 * insertStudentData
	 * @param insertStudentData
	 * @return
	 */
	@Transactional
	public StatusObject insertStudentData(InsertStudentData insertStudentData){
		
		Users users = new Users();
		users.setName(insertStudentData.getName());
		users.setMarks(insertStudentData.getMarks());
		users.setRank(insertStudentData.getRank());
		int status =  usersdao.insertObjectIntoDatabase(users);
		StatusObject statusObject = new StatusObject();
		if(status==0){
			statusObject.setStatus(true);
			statusObject.setMessage("Failed to insert data");
			return statusObject;
		}else{
			statusObject.setStatus(false);
			statusObject.setMessage("Success to insert data");
			return statusObject;
		}
	}
	
	
	
	/**
	 * updateStudentDetails
	 * @return
	 */
	@Transactional
	public StatusObject updateStudentDetails(int userId){
		
		Users users = usersdao.updateStudentDetailsById(userId);
		StatusObject statusObject= new StatusObject();;
		if(users!=null){
			
		  users.setName("Ramakri");
		  users.setMarks(0.99);
		  boolean status = usersdao.updateUser(users);
			if(status){
				statusObject.setStatus(false);
				statusObject.setMessage("Success to update data");
			}else{
				statusObject.setStatus(true);
				statusObject.setMessage("Failed to update data");
			}
		}
		return statusObject;
	}
	
	
	/**getListOfStudentsWithStates
	 * getListOfStudents
	 * @return
	 */
	@Transactional
	public List<StudentData> getListOfStudentsWithStates(){
		
		 List<StudentData>baseList = new ArrayList<StudentData>();
	     List<?> rootList =usersdao.getListOfStudents();
	     System.out.println("root list====>"+rootList.size());
	
	     for(int i=0;rootList.size()>i;i++){
	    	
	    	Users users = (Users)rootList.get(i);
	    	StudentData studentData = new StudentData();
	    	studentData.setUserId(users.getUser_id());
	    	studentData.setName(users.getName());
	    	studentData.setRank(users.getRank());
	        studentData.setMarks(users.getMarks());
	        
	        List<String>states=new ArrayList<String>();
	        List<?> statesIdList = usersdao.getListOfStates(users.getUser_id());
	        System.out.println("states list====>"+statesIdList.size());
	        
	        for(int j=0;statesIdList.size()>j;j++){
	        	
	            States s = (States)statesIdList.get(j);
	        	int stateId = s.getStateId();
	        	String stateName = usersdao.getListOfStatesWithId(stateId);
	        	System.out.println("state name is====>"+stateName);
	        	if(stateName!=null){
	        		states.add(stateName);
	        	}
	        }
	        studentData.setStates(states);
	        baseList.add(studentData);
	     }
		return baseList;
	}
	
	
	/**
	 * getAttenenceList
	 * @return
	 */
	@Transactional
	public Attenence getAttenenceList(int userId){
		
		Attenence attenence = new Attenence();
		//GET ADDRESS
		List<?>getAddressList = usersdao.getAddressById(userId);
		for(int i=0;i<getAddressList.size();i++) {
			Address address = (Address)getAddressList.get(i);
			System.out.println("adeess is==================>"+address.getAddr());
			attenence.setAddr(address.getAddr());
			attenence.setCity(address.getCity());
			attenence.setState(address.getState());
		}
		//GET MEMBERS LIST
		List<Staff>arrayList = new ArrayList<Staff>();
		List<?>membersList = usersdao.getMembersList(userId);
		for(int i=0;i<membersList.size();i++) {
			Members members = (Members)membersList.get(i);
			Staff staff = new Staff();
			staff.setName(members.getName());
			staff.setDesg(members.getDes());
			staff.setEmail(members.getEmail());
			arrayList.add(staff);
		}
		attenence.setMembers(arrayList);
		return attenence;
	}
	
	
	/**
	 * getListOFCountries
	 * @return
	 */
	@Transactional
	public List<?>getListOfCountries(){
		return usersdao.getListOfCountries();
	}
	
	
	/**
	 * getListofStates
	 * @return
	 */
	@Transactional
	public List<?>getListofStates(){
		return usersdao.getListofStates();
	}
	
	
	/**
	 * getListOfCountriesWithQuery
	 * @return
	 */
	@Transactional
	public List<?>getListOfCountriesWithQuery(){
		
		List<Country> list= usersdao.getListOfCountriesWithQuery();
		List<Country>mailList = new ArrayList<Country>();
		for(int i=0;i<list.size();i++){
			Country country = new Country();
			country.setCid(list.get(i).getCid());
			country.setName(list.get(i).getName());
			mailList.add(country);
		}
		return mailList;
	}
	
	
    /**
      * registerPatientDevice
      * @param sampleNotificationModel
      * @return
     */
	@Transactional
	public StatusObject registerPatientDevice(SampleNotificationModel sampleNotificationModel) {

		StatusObject statusObject = new StatusObject();
		
		if (sampleNotificationModel != null) {
					
					UserNotificationDTO userNotificationDTO = new UserNotificationDTO();
					userNotificationDTO.setUserId(sampleNotificationModel.getUserId());
					userNotificationDTO.setDeviceId(sampleNotificationModel.getDeviceId());					
					userNotificationDTO.setGcmId(sampleNotificationModel.getGcmId());
					boolean status = usersdao.saveUserNotificationObj(userNotificationDTO);
					if (status) {
						statusObject.setStatus(false);
						statusObject.setMessage("Device register successfull");
						return statusObject;
					} else {
						statusObject.setStatus(true);
						statusObject.setMessage("Device register fail");
						return statusObject;
					}
				}else{
					statusObject.setStatus(true);
					statusObject.setMessage("Device register fail");
					return statusObject;
				}
				
         }
	
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	@Transactional
	public UploadImageModel profileUploadImage(MultipartFile file) {
		
		
	  UploadImageModel StatusObj = new UploadImageModel();
	  
		if (!file.isEmpty()) {
			
			try {

				System.out.println(file.getOriginalFilename());

				String fileName = saveImgaeFileIntoDisk(file);
				System.out.println("File is---->"+fileName);
				if (fileName != null) {
					int imageid = usersdao.uploadProfileImage(fileName);
					StatusObj.setImageid(imageid);
					StatusObj.setError(false);
					StatusObj.setMessage("Success fully uploaded");
					return StatusObj;
				} else {
					StatusObj.setError(true);
					StatusObj
							.setMessage("Failure to uploaded");
					return StatusObj;
				}
			} catch (Exception e) {
				e.printStackTrace();
				StatusObj.setError(true);
				StatusObj
						.setMessage("Failure to uploaded");
				return StatusObj;
			}
		} else {
			StatusObj.setError(true);
			StatusObj.setMessage("Failure to uploaded");
			return StatusObj;
		}
	}

	
	/**
	 * 
	 * @param file
	 * @return
	 */
	@Transactional
	public String saveImgaeFileIntoDisk(MultipartFile file) {

		try {

			String uuid = com.ensis.sample.utils.GenerateGUID.generateGuidValue();
			String fileName = uuid + "_" + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			File savefile = new File(getMessage("imagePath") + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(savefile));
			stream.write(bytes);
			stream.close();
			return fileName;

		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
}
