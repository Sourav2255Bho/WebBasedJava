package utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	//how to ensure creation of single instance of the session factory? : EAGER singleton pattern
	static {
		System.out.println("int static init block");
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	// getter for SF (SessionFactory) 
	public static SessionFactory getFactory() {
		return factory;
	}
	
}
