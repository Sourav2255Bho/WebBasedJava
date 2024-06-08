package beans;

import java.sql.SQLException;

import com.app.dao.UserDao;
import com.app.dao.UserDaoImpl;
import com.app.entities.User;

public class UserBean {
//properties : state (non static n no transient data members)
	//clnts conversional state(clnt specific info)
	private String email;
	private String password;
	
	// DAO layer ref
	private UserDaoImpl userDao;
	
	//validated user details
	private User validatedUserDetails;
	
	// default arg-less constr
	public UserBean() throws ClassNotFoundException, SQLException {
		System.out.println("In user bean contr");
		//create instance of the DAO
		userDao = new UserDaoImpl();
	}
	//setter n getters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	} 

	public User getValidatedUserDetails() {
		return validatedUserDetails;
	}

	public void setValidatedUserDetails(User validatedUserDetails) {
		this.validatedUserDetails = validatedUserDetails;
	}
	
	// add B.L method : for user validation 
	public String authenticateUser() throws SQLException
	{
		System.out.println("in B.L : auth "+email+" "+ password);
		//invoke Dao's method for validation
		validatedUserDetails = userDao.signIn(email, password);
		//null checking
		if(validatedUserDetails == null) {
			return "login";
		}
		//=> valid login
		// role based authorization
		if(validatedUserDetails.getRole().equals("voter"))//voter login
			return "candidate_list";
		
		return "result_list";
	}
	 
}
