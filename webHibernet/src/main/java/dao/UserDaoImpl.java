package dao;

import pojos.User;
import pojos.UserRole;

import static utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.*;


public class UserDaoImpl implements IUserDao {
	//NO data members, no constr, no cleanup
	@Override
	public String registerUser(User user) {
		String mesg = "User Registration Failed...";
		//user : state : TRANSIANT
		//get The Session from SF
		Session session = getFactory().openSession();
		Session session2 = getFactory().openSession();
		System.out.println(session == session2); // false
		//begin a tx
		Transaction tx = session.beginTransaction();//db cn is pooled out --wrapped in Session obj, L1 cache is created
		System.out.println("session is open "+ session.isOpen()+" is connected to db cn "+session.isConnected());// true true
		try {
			//org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
			Serializable userId = session.save(user);//user ref is added in the cache : PERSISTENT
			tx.commit();//Upon commit : Hibernate performs "auto dirty checking" : by comparing the state L1 cache with of DB : DML will be fired(insert)
			mesg = "user registration successfully with ID = "+userId;
			System.out.println("session is open "+ session.isOpen()+" is connected to db cn "+session.isConnected());//true true
		}catch(RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}finally {
			if(session != null) 
				session.close();// pooled out DB cn rets to the pool n L1 cache is destroyed
		}
		System.out.println("session is open "+ session.isOpen()+" is connected to db cn "+session.isConnected());// false false
		return mesg;
	}
	
	@Override
	public String registerUserWithGetCurrentSession(User user) {
		String mesg = "User Registration Failed...";
		//user : state : TRANSIANT
		//get The Session from SF
		Session session = getFactory().getCurrentSession();
		//begin a tx
		Transaction tx = session.beginTransaction();//db cn is pooled out --wrapped in Session obj, L1 cache is created
		try {
			//org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
//			Serializable userId = session.save(user);//user ref is added in the cache : PERSISTENT
			session.persist(user);
			tx.commit();//Upon commit : Hibernate performs "auto dirty checking" : by comparing the state L1 cache with of DB : DML will be fired(insert)
			// pooled out DB cn rets to the pool n L1 cache is destroyed
			mesg = "user registration successfully with ID = "+user.getUserId();
		}catch(RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public User getUserDetails(int userId) {
		User user = null;
		//get Session from SF : getCurrentSession
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// get 
			user = session.get(User.class, userId);// int --> Integer --> Serializable
			tx.commit();
			
		}catch(RuntimeException e){
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return user;
	}

	@Override
	public List<User> getAllUserDetails() {
		List<User> users = null;
		String jpql = "select u from User u"; 
		//get session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx 
		Transaction tx = session.beginTransaction();
		try {
			//session --> create Query ---> getResultList
			users = session.createQuery(jpql, User.class).getResultList(); 
			//users = session.createQuery(jpql, User.class).getResultList();
			// users : list of PERSISTENT entities
			tx.commit();
		}catch(RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return users; // users : list of DETACHED entities
	}

	@Override
	public List<User> getSelectedUserDetails(LocalDate strt, LocalDate end, UserRole role) {
		List<User> users = null;
		String jpql = "select u from User u where u.regDate between :strtDate and :endDate and u.userRole = :userRole";
		//get session from SF 
		Session session = getFactory().getCurrentSession();
		//begin transaction
		Transaction tx = session.beginTransaction();
		try {
			// Session --> Query object --> set 3 named  IN params
			users = session.createQuery(jpql, User.class).
					setParameter("strtDate", strt).
					setParameter("endDate", end).
					setParameter("userRole", role).getResultList(); //users : list of PERSISTENT entities
			
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!= null) 
				tx.rollback();
		}
		return users;
	}

	@Override
	public User userLogin(String email, String password) { 
		User user = null;
		String jpql = "select u from User u where u.email = :em and u.password = :pass";
		//get session for SF
		Session session = getFactory().getCurrentSession();
		// begin transaction
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", password).getSingleResult();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		System.out.println(user);
		return user;
	}

	@Override
	public String deleteUser(LocalDate date, double amount) {
		StringBuilder mesg = new StringBuilder("No of users updated : ");
		String jpql = "delete from User u where u.regDate > :date and regAmount < :amount";
		//get session from SF
		Session session = getFactory().getCurrentSession();
		//begin tx 
		Transaction tx = session.getTransaction();
		try {
			int updatedRow = session.createQuery(jpql).setParameter("dt", date).setParameter("amount", amount).executeUpdate();
			tx.commit();
			mesg.append(updatedRow);
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return mesg.toString();
	}

	@Override
	public List<String> getSelectedUserNames(LocalDate strt, LocalDate end, UserRole role) {
		List<String> names = null;
		String jpql = "Select u.name from User u where u.regDate between :strtDate and :endDate and u.userRole = :userRole";
		//get session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			names = session.createQuery(jpql, String.class).
					setParameter("strtDate", strt).
					setParameter("endDate", end).
					setParameter("userRole", role).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
		return names;
	}

	@Override
	public List<User> getPartialDetails(LocalDate strt, LocalDate end, UserRole role) {
		List<User> userDetails = null;
		String jpql = "select new pojos.User(name, email, regAmount, regDate) from User u where u.regDate between :strtDate and :endDate and u.userRole = :userRole";
		//get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		
		try {
			userDetails = session.createQuery(jpql, User.class).
					setParameter("strtDate", strt).
					setParameter("endDate", end).
					setParameter("userRole", role).getResultList();
			tx.commit();
			
		} catch (RuntimeException e) {
			if(tx != null) 
				tx.rollback();
			throw e;
		}
		return userDetails;
	}

	@Override
	public String changePassword(int userId, String newPassword) {
		String mesg = "Password updation failed !!!";
		User user = null;
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// get user details by PK
			user = session.get(User.class, userId); // int --> Integer --> Serializable
			if(user != null) {
				//user : PERSISTANT (part of L1 cache + has corresponding rec in DB)
				//valid id
				user.setPassword(newPassword); // changing the state of the PERSISTANT entity
				mesg = "Password Successfully Chasnged";
				
			}
			tx.commit();// Hibernate performs auto dirty checking : finds the state of the persistant entity changed --> DML(update), L1 cache is destroyed, cn rets to pool
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e ;
		}
		user.setPassword("12411251");// user : DETACHED (NO DML will be fired!!!!)
		return mesg;
	}

	@Override
	public String applyDiscount(LocalDate regDate, double discount) {
		StringBuilder mesg = new StringBuilder("Total no of user Updated : ");
		String jpql = "update User u set u.regAmount = u.regAmount-:discount where u.regDate<:dt";
		//get Session from SF
		Session session = getFactory().getCurrentSession();
		//begin tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount= session.createQuery(jpql).
					setParameter("discount", discount).
					setParameter("dt", regDate).executeUpdate();
			mesg.append(updateCount);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return mesg.toString();
	}

	@Override
	public String unsubscribeUser(String email) {
		String mesg = "User unsubscription failed !!";
		String jpql = "select u from User u where u.email=:em";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			User user = session.createQuery(jpql, User.class).setParameter("em", email).getSingleResult();
			//=> email is valid, user : PERSISTENT 
			session.delete(user);// entity is simply marked for removal 
			tx.commit();// Hibernate performs automatic dirty checking --> DML (delete) --> L1 cache is destroyed, cn rets to the pool, User : transient
			mesg = "User unsubscribed ...";
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return mesg ;
	}//user : doesn't exist(marked for garbage collection)
	
	

}
