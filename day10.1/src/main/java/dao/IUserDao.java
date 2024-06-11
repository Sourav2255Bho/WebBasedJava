package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.User;
import pojos.UserRole;

public interface IUserDao {
	String registerUser(User user); // open session
	String registerUserWithGetCurrentSession(User user); // get curnt session
	// add a method to fetch user details from the supplied user id
	User getUserDetails(int userId);
	//add a method to fetch list of all users' details
	List<User> getAllUserDetails();
	// add a method to fetch list of selected users' details
	List<User> getSelectedUserDetails(LocalDate strt, LocalDate end, UserRole role);
	// add a method for user login
	String userLogin(String email, String password);
	// add a method to fetch the list of selected users' names
	List<String> getSelectedUserNames(LocalDate strt, LocalDate end, UserRole role);
	// add a method to fetch the list of selected user's some details 
	List<User> getPartialDetails(LocalDate strt, LocalDate end, UserRole role);
	// email, regAmount, regDate
	//add a method to change user's password
	String changePassword(int userId , String newPassword);
	// add a method to apply discount to some user
	String applyDiscount(LocalDate regDate, double discount);
	// add a method to delete user details
	String unsubscribeUser(String email);
	// add a method to delete in bulk
	String deleteUser(LocalDate date, double amount);
}
