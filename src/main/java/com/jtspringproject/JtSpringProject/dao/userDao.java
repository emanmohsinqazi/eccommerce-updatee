package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;


@Repository
public class userDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
		List<User>  userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }
    
    @Transactional
	public User saveUser(User user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		System.out.println("User added" + user.getId());
        return user;
	}
    
//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }
@Transactional
public User getUser(String username,String password) {
	Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
	query.setParameter("username", username);

	User user;
	try {
		user = (User) query.getSingleResult();
	} catch (NoResultException e) {
		// User does not exist in the database
		user = null;
	}

	String errorMessage;

	if (user == null) {
		// Invalid user, set error message
		errorMessage = "Invalid username or password";
		return user;
	} else {
		// Valid user, check password
		if (password.equals(user.getPassword())) {
			return user;
		} else {
			// Invalid password, set error message
			errorMessage = "Invalid username or password";
			return null;
		}
	}

}
	@Transactional
	public boolean userExists(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
		query.setParameter("username",username);
		return !query.getResultList().isEmpty();
	}
	@Transactional
	public void deleteUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = session.load(User.class, userId);
		if (user != null) {
			session.delete(user);
		}
	}
}