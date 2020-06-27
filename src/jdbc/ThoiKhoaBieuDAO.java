package jdbc;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.SinhVien;
import pojo.ThoiKhoaBieu;

public class ThoiKhoaBieuDAO {
	public static void Insert(int stt, String maMon, String tenMon, String phongHoc, String lop) {
		ThoiKhoaBieu tkb = new ThoiKhoaBieu(stt, maMon, tenMon, phongHoc, lop);
		Session session = HibernateUtility.getSession();
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(tkb);
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
