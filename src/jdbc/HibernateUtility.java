package jdbc;

import org.hibernate.cfg.Configuration;
import org.hibernate.*;

public class HibernateUtility {
	private static SessionFactory factory;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			factory = configuration.buildSessionFactory();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
}
