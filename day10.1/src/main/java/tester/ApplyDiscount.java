package tester;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class ApplyDiscount {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)){
			System.out.println("Enter Date n discount ");
			//create dao instance n test APi
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println(userDao.applyDiscount(LocalDate.parse(sc.next()), sc.nextDouble()));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
