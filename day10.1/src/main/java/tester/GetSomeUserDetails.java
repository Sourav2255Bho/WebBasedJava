package tester;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.UserRole;

public class GetSomeUserDetails {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)){
			// Create a dao instance
			UserDaoImpl dao = new UserDaoImpl();
			
			System.out.println("Enter the start Date and the End Date and the role");
			dao.getPartialDetails(LocalDate.parse(sc.next()), LocalDate.parse(sc.next()), UserRole.valueOf(sc.next().toUpperCase())).forEach(u -> System.out.println("Name : "+u.getName()+", Email : "+u.getEmail()+", Amount : "+u.getRegAmount()+", Reg Date : "+u.getRegDate()));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
