/**
 * 
 */
package com.ensis.sample.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.ensis.sample.dto.UsersDTO;

/**
 * @author shyam
 *
 */
public class MyTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private List<TokenEnhancer> delegates = Collections.emptyList();

	public void setTokenEnhancers(List<TokenEnhancer> delegates) {
		this.delegates = delegates;
	}

	/**
	 * 
	 */
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		//TODO Auto-generated method stub
		DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;
		User user = (User) authentication.getPrincipal();
		final Map<String, Object> additionalInformation = new HashMap<String, Object>();
		
		int userId=getUserId(user.getUsername());
		additionalInformation.put("userId", userId);
		
		tempResult.setAdditionalInformation(additionalInformation);
		OAuth2AccessToken result = tempResult;
		for (TokenEnhancer enhancer : delegates) {
			result = enhancer.enhance(result, authentication);
		}
		return result;
	}
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public int getUserId(String userName) {

		int userId = 0;
		String hql = "FROM UsersDTO p WHERE " + "p.userName = :userName";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);

		List<?> results = query.list();
		Iterator<?> itr = results.iterator();
		if (itr.hasNext()) {
			UsersDTO users = (UsersDTO) itr.next();
			userId = users.getUserId();
		}
		session.flush();
		session.clear();
		return userId;
	}

}
