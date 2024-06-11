package tester;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.UserRole;

public class GetSelectedUserNames {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)){
			System.out.println("Enter strt n end date and role : ");
			//create dao instance 
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println();
//			dao.getAllUserDetails().forEach(System.out::println);
			dao.getSelectedUserNames(LocalDate.parse(sc.next()), LocalDate.parse(sc.next()), UserRole.valueOf(sc.next().toUpperCase())).forEach(u -> System.out.println(u));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
