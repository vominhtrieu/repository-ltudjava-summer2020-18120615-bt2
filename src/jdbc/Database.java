package jdbc;

import org.hibernate.SessionFactory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import pojo.*;

public class Database {
	private String dbUrl, username, password;
	
	public Database(String url, String username, String password) {

	}
	
	public static void main(String[] args) {
		try {
			Configuration configuration = new Configuration();
			SessionFactory factory = configuration.configure().buildSessionFactory();
			Session session = factory.openSession();
			String sql = "SELECT SV FROM SinhVien SV";
			Query query = session.createQuery(sql);
			List<SinhVien> svs = query.list();
			for (SinhVien sv : svs) {
				System.out.println(sv.getMaSv() + " - " + sv.getHoTen());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
