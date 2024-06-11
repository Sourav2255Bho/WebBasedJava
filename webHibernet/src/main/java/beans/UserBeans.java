package beans;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class UserBeans {
	// Properties : state(non static n no transient data members)
	private String email;
	private String pass;
	
	private User validatedUser;
	
	private UserDaoImpl dao;
	
	private String mesg;

	public UserBeans(){
		System.out.println("in User Bean constr");
		dao = new UserDaoImpl();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User getValidatedUser() {
		return validatedUser;
	}

	public void setValidatedUser(User validatedUsers) {
		this.validatedUser = validatedUsers;
	}

	public UserDaoImpl getDao() {
		return dao;
	}

	public void setDao(UserDaoImpl dao) {
		this.dao = dao;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	
	// B.L method
	public String authenticateUser() {
		
		System.out.println("In B.L : auth "+ email+" "+pass);
		
		try {
			validatedUser = dao.userLogin(email, pass);	
		} catch (RuntimeException e) {
			System.out.println("err in bean "+e);// NoResultException
			mesg = "Invalid Login, Please Retry...";
			return "login";
		}
		//--> Valid Login
		if(validatedUser.getUserRole().equals(UserRole.CUSTOMER)) {
			mesg = "Customer Login Successfull...";
			return "candidate_list";
		}
		
		mesg = "Login Successfull...";
		return "candidate_list";
		
	}
	
	
}
