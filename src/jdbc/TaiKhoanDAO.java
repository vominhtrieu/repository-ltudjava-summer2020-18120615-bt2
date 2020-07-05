package jdbc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public static void changePassword(String tenDangNhap, String matKhauMoi) {
		Session session = HibernateUtility.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			TaiKhoan tk = session.get(TaiKhoan.class, tenDangNhap);
			tk.setMatKhau(matKhauMoi);
			session.update(tk);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}
}
