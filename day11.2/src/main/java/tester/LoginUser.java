package tester;
import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class LoginUser {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)){
			System.out.println("Enter the email and password : ");
			//create dao instance n test APi
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println(userDao.userLogin(sc.next(), sc.next()));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
