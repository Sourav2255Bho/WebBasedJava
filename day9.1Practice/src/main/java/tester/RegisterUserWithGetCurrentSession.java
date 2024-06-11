package tester;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class RegisterUserWithGetCurrentSession {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)){
			System.out.println("Enter user details : name, email, password, userRole, confirmPassword, regAmount,"
					+ " regDate(yyyy-MM-dd)");
			//create a transient POJO : exists in Heap
			User user = new User(sc.next(), sc.next(), sc.next(), UserRole.valueOf(sc.next().toUpperCase()), sc.next(), sc.nextDouble(), LocalDate.parse(sc.next()));
			//create dao instance n test APi
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println(userDao.registerUserWithGetCurrentSession(user));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
