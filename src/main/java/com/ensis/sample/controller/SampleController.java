package com.ensis.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ensis.sample.model.Attenence;
import com.ensis.sample.model.InsertStudentData;
import com.ensis.sample.model.SampleNotificationModel;
import com.ensis.sample.model.StatusObject;
import com.ensis.sample.model.UploadImageModel;
import com.ensis.sample.model.UserModel;
import com.ensis.sample.services.UserService;

@Controller
public class SampleController {
	
	@Autowired 
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	/**
	 * getEmployeeList
	 * @return
	 */
	@RequestMapping(value="/User/getList", method = RequestMethod.GET,produces={"application/json"})
	@ResponseBody
	public List<?> getEmployeeList(){
		return userService.setTransactionObject();
	}
	
	/**
	 * getEmployeessListBasedonId
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value="/getListById", method = RequestMethod.POST,produces={"application/json"})
	@ResponseBody
	public List<?> getEmployeessListBasedonId(@RequestBody UserModel userModel){
		return userService.setTransactionObjectById(userModel);
	}
	
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/insertData", method = RequestMethod.POST,produces={"application/json"})
	@ResponseBody
	public StatusObject insertStudentData(@RequestBody InsertStudentData data){
		return userService.insertStudentData(data);
	}
	
	
	/**
	 * updateStudentData
	 * @return
	 */
	@RequestMapping(value="/updateData", method = RequestMethod.POST,produces={"application/json"})
	@ResponseBody
	public StatusObject updateStudentData(@RequestParam int userId){
		return userService.updateStudentDetails(userId);
	}
	
	
	/**
	 * @RequestMapping
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getAttenenceList",method = RequestMethod.POST,produces={"application/json"})
	@ResponseBody
	public Attenence getAttenenceList(@RequestParam int userId) {
		return userService.getAttenenceList(userId);
	}
	
	
	
	/**
	 * getListOFStudents
	 * @return
	 */
	@RequestMapping(value="/getListOfStudents", method = RequestMethod.GET,produces={"application/json"})
	@ResponseBody
	public List<?> getListOFStudents(){
		return userService.getListOfStudentsWithStates();
	}
	
	
	/**
	 * getListOfCountries
	 * @return
	 */
	@RequestMapping(value="/getListStates", method = RequestMethod.GET,produces={"application/json"})
	@ResponseBody
	public List<?>getListOfCountries(){
		return userService.getListofStates();
	}
	
	/**
	 * getListOfCountries
	 * @return
	 */
	@RequestMapping(value="/getListCountriesWithQuery", method = RequestMethod.GET,produces={"application/json"})
	@ResponseBody
	public List<?>getListOfCountriesWithQuery(){
		return userService.getListOfCountriesWithQuery();
	}
	
	
	/**
	 * getLiostOfStudentsWithStates
	 * @return
	 */
	@RequestMapping(value="/getListofStudentsWithStates",method = RequestMethod.GET,produces= {"application/json"})
	@ResponseBody
	public List<?>getLiostOfStudentsWithStates(){
		return userService.getListOfStudentsWithStates();
	}
	
	
	
	/**
	 * 
	 * @param physicianNotificationModel
	 * @return
	 */
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST, headers = {
			"Content-type=application/json" }, produces = { "application/json" })
	@ResponseBody
	public StatusObject addDevice(@RequestBody SampleNotificationModel sampleNotificationModel) {
		return userService.registerPatientDevice(sampleNotificationModel);
	}
	
	
	/**
	 * uploadImage
	 * @param multipartFile
	 * @return
	 */
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	@ResponseBody
	public UploadImageModel uploadImage(@RequestParam("imagename") MultipartFile multipartFile) {
		return userService.profileUploadImage(multipartFile);
	}
}
