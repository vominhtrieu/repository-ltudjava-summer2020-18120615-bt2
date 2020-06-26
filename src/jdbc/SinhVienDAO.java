package jdbc;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.SinhVien;

public class SinhVienDAO {
	public static void Insert(int stt, int mssv, String hoTen, String gioiTinh, String cmnd, String lop) {
		SinhVien sv = new SinhVien(stt, mssv, hoTen, gioiTinh, cmnd, lop);
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
