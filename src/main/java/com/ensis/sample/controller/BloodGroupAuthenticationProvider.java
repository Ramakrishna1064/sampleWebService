/**
 * 
 */
package com.ensis.sample.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ensis.sample.dto.UsersDTO;


/**
 * @author Krishna
 *
 */
@Repository
public class BloodGroupAuthenticationProvider implements UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	/**
	 * loadUserByUsername
	 */
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		String password = getUserPassword(userName);
		if(password == null){
			throw new UsernameNotFoundException("Invalid Username");
		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.
				createAuthorityList("ROLE_APP", "ROLE_APP",
				"ROLE_APP");
		return new User(userName, password, authorities);
	}

	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public String getUserPassword(String userName) {

		String password = null;
		String hql = "FROM UsersDTO p WHERE " + "p.userName = :userName";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);

		List<?> results = query.list();
		Iterator<?> itr = results.iterator();
		if (itr.hasNext()) {
			UsersDTO users = (UsersDTO) itr.next();
			password = users.getPassword();
		}
		session.flush();
		session.clear();
		return password;
	}
}
