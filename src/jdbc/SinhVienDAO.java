package jdbc;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.SinhVien;

public class SinhVienDAO {
	public static void Insert(int mssv, String maLop, String hoTen, String gioiTinh, String cmnd) {
		SinhVien sv = new SinhVien(mssv, maLop, hoTen, gioiTinh, cmnd);
		Session session = HibernateUtility.getSession();
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(sv);
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
		}
		finally {
			session.close();
		}
	}
}
