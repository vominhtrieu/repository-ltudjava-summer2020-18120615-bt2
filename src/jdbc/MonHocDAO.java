package jdbc;

import org.hibernate.Transaction;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import pojo.SinhVien;
import pojo.BangDiem;
import pojo.MonHoc;

public class MonHocDAO {
	@SuppressWarnings("deprecation")
	public static void Insert(String maMon, String maLop, String tenMon, String phongHoc) {
		MonHoc monHoc = new MonHoc(maMon, maLop, tenMon, phongHoc);
		Session session = HibernateUtility.getSession();
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(monHoc);
			Query query = session.createQuery("SELECT sv FROM SinhVien sv WHERE sv.maLop= :maLop");
			query.setString("maLop", monHoc.getMaLop());
			
			
			List<SinhVien> dsSinhVien = query.list();
			
			for (SinhVien sv: dsSinhVien) {
				BangDiem bangDiem = new BangDiem(sv, monHoc, 0, 0, 0, 0);
				session.save(bangDiem);
			}
			
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
		}
		finally {
			session.close();
		}
	}
	
	public static List<MonHoc> GetList(int mssv) {
		Session session = HibernateUtility.getSession();
		
		Transaction transaction = null;
		List<MonHoc> dsMonHoc = null;
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT bd.monHoc FROM BangDiem bd WHERE bd.sinhVien.mssv = :mssv");
			query.setInteger("mssv", mssv);
			
			dsMonHoc = query.list();
			
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
		}
		finally {
			session.close();
		}
		
		return dsMonHoc;
	}
}
