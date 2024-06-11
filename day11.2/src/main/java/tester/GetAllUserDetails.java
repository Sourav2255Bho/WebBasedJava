package tester;
import static utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class GetAllUserDetails {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory()){
			//create dao instance 
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println();
//			dao.getAllUserDetails().forEach(System.out::println);
			dao.getAllUserDetails().forEach(u -> System.out.println(u));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
