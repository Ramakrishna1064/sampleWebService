package com.ensis.sample.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.ensis.sample.common.Service.NotifcationService;
import com.ensis.sample.dto.Country;
import com.ensis.sample.dto.Images;
import com.ensis.sample.dto.StatesId;
import com.ensis.sample.dto.UserNotificationDTO;
import com.ensis.sample.dto.Users;
import com.ensis.sample.model.UserModel;

@Repository
public class UsersDao {

	@Autowired
	HibernateUtil hibernateUtil;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	NotifcationService notifcationService;
	
	
	public void setNotifcationService(NotifcationService notifcationService) {
		this.notifcationService = notifcationService;
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	
	/**
	 * insertObjectIntoDatabase
	 * @param userOj
	 * @return
	 */
	public int insertObjectIntoDatabase(Users userOj){
		
		Object obj = hibernateUtil.create(userOj);
		if (obj != null) {
			Session session = sessionFactory.getCurrentSession();
			int userID = (Integer) session.createCriteria(Users.class).setProjection(Projections.max("user_id")).uniqueResult();
			session.flush();
			session.clear();
			return userID;
		} else {
			return 0;
		}
	}
	
	
	/**
	 * getListOfStudents
	 * @return
	 */
	  public List<?> getListOfStudents() {
		  
	        Session session = sessionFactory.getCurrentSession();
	        Criteria criteria = session.createCriteria(Users.class);
	        List<?> list = criteria.list();
	        session.flush();
	        session.clear();
	        return list;
	    }
	  
	  
	  
	  /**
	   * getListOfStiudentById
	   * @param userModel
	   * @return
	   */
	  public List<?> getListOfStiudentById(UserModel userModel){
		  
		  Session session = sessionFactory.getCurrentSession();
		  Criteria criteria = session.createCriteria(Users.class);
		  criteria.add(Restrictions.eq("user_id",userModel.getId()));
		  criteria.add(Restrictions.eq("name",userModel.getUserName()));
		  List<?> list = criteria.list();
	      session.flush();
	      session.clear();
		  return list;
	  }
	  
	  
	  
	  /**
	   * updateStudentDetailsById
	   * @param userId
	   * @return
	   */
	  public Users updateStudentDetailsById(int userId){
		  
		  String hql = "FROM Users u WHERE " + "u.user_id = :userId";
		  Session session = sessionFactory.getCurrentSession();
		  Query query = session.createQuery(hql);
		  query.setParameter("userId", userId);
		  List<?>list = query.list();
		  Iterator<?>iterator = list.iterator();
		  if(iterator.hasNext()){
			  Users users = (Users)iterator.next();
			  return users;
		  }
		  session.flush();
		  session.clear();
		  return null;
	  }
	  
	  
	     /**
		 * updateUser
		 * @param userObj
		 * @return
		 */
		public boolean updateUser(Users userObj){
		
			Object obj=hibernateUtil.update(userObj);
			if(obj != null)
				return true;
			return false;
		}
		
		
		
	
		
		
		/**
		 * getListOfStates
		 * @return
		 */
		public List<?>getListOfStates(int userId){
		
		   String hql = "FROM States s WHERE " + "s.studentId = :userId";
		   Session session = sessionFactory.getCurrentSession();
		   Query query = session.createQuery(hql);
		   query.setParameter("userId", userId);
		   session.flush();
		   session.clear();
		   return query.list();
		}
		
		
		/**
		 * getAddressById
		 * @param userId
		 * @return
		 */
		public List<?>getAddressById(int userId){
			
			String hql = "FROM Address a WHERE " + "a.user_id = :userId";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			session.flush();
			session.clear();
			return query.list();
		}
		
		
		/**
		 * 
		 * @param userId
		 * @return
		 */
		public List<?>getMembersList(int userId){
			
			String hql = "FROM Members a WHERE " + "a.user_id = :userId";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			session.flush();
			session.clear();
			return query.list();
		}
		
		
		/**
		 * getListOfStatesWithId
		 * @return
		 */
		public String getListOfStatesWithId(int stateId){
		
		   String stateName=null;
		   Session session = sessionFactory.getCurrentSession();
		   Criteria criteria = session.createCriteria(StatesId.class);
		   criteria.add(Restrictions.eq("stateId", stateId));
		   List<?> list = criteria.list();
		   if(list.size()>0){
			   StatesId s = (StatesId) list.get(0);
			   stateName = s.getName();
		   }
		   session.flush();
		   session.clear();
		   return stateName;
		}
		
		
		/**
		 * getListOFCountries
		 * @return
		 */
		public List<?>getListOfCountries(){
			
			 Session session = sessionFactory.getCurrentSession();
		     Criteria criteria = session.createCriteria(Country.class);
		     List<?> list = criteria.list();
		     session.flush();
		     session.clear();
		     return list;
		}
		
		/**
		 * getListOFCountries
		 * @return
		 */
		public List<Country>getListOfCountriesWithQuery(){
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From Country where cid = :cid");
			query.setInteger("cid", 1);
			List<Country>list = query.list();
			return list;
		}
		
		
		
		/**
		 * getListofStates
		 * @return
		 */
		public List<?>getListofStates(){
			return hibernateUtil.fetchAll(StatesId.class);
		}
		
		
		/**
		 * 
		 * @param userNotificationDTO
		 * @return
		 */
		public boolean saveUserNotificationObj(UserNotificationDTO userNotificationDTO) {

			//Check whether UserId available or Not
			UserNotificationDTO user = checkUserIdAvailableOrNot(userNotificationDTO.getUserId());
			
			if(user != null){
				System.out.println("Ramakrishna in update block");
				user.setGcmId(userNotificationDTO.getGcmId());
				user.setDeviceId(userNotificationDTO.getDeviceId());
				Object obj = hibernateUtil.update(user);
				if (obj != null) {
					//notifcationService.sendCloseTreatmentNotification(getDevicesList(userNotificationDTO.getUserId()));
					return true;
				}
				return false;
			}else{
				System.out.println("Ramakrishna in create block");
				Object obj = hibernateUtil.create(userNotificationDTO);
				if (obj != null) {
					//notifcationService.sendCloseTreatmentNotification(getDevicesList(userNotificationDTO.getUserId()));
					return true;
				}
				return false;
			}	
		}
		
		
		  /**
		   * updateStudentDetailsById
		   * @param userId
		   * @return
		   */
		  public UserNotificationDTO checkUserIdAvailableOrNot(int userId){
			 
			  String hql = "FROM UserNotificationDTO u WHERE " + "u.userId = :userId";
			  Session session = sessionFactory.getCurrentSession();
			  Query query = session.createQuery(hql);
			  query.setParameter("userId", userId);
			  List<?>list = query.list();
			  Iterator<?>iterator = list.iterator();
			  if(iterator.hasNext()){
				  UserNotificationDTO users = (UserNotificationDTO)iterator.next();
				  return users;
			  }
			  session.flush();
			  session.clear();
			  return null;
		  }
		  
		  
		  /**
		   * updateStudentDetailsById
		   * @param userId
		   * @return
		   */
		@SuppressWarnings("unchecked")
		public ArrayList<UserNotificationDTO> getDevicesList(int userId){
			 
			  String hql = "FROM UserNotificationDTO u WHERE " + "u.userId = :userId";
			  Session session = sessionFactory.getCurrentSession();
			  Query query = session.createQuery(hql);
			  session.flush();
			  session.clear();
			  return (ArrayList<UserNotificationDTO>) query.list();
		  }
		
		
		
		/**
		 * 
		 * @param fileName
		 * @return
		 */
		public int uploadProfileImage(String fileName) {

			int imgid = 0;
			try {
				Images images = new Images();
				images.setImagename(fileName);
				images.setCreatedby("admin");
				images.setCreateddate(new Date());
				hibernateUtil.create(images);

				Session session = sessionFactory.getCurrentSession();
				imgid = (Integer) session.createCriteria(Images.class)
						.setProjection(Projections.max("id")).uniqueResult();
				System.out.println("maxid-->" + imgid);
				session.flush();
				session.clear();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			return imgid;
		}
		  
}
