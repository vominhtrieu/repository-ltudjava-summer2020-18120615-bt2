package jdbc;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.TaiKhoan;

public class TaiKhoanDAO {
	public static TaiKhoan get(String tenDangNhap) {
		TaiKhoan tk = null;
		Session session = HibernateUtility.getSession();
		try {
			tk = session.get(TaiKhoan.class, tenDangNhap);
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
		return tk;
	}
}
