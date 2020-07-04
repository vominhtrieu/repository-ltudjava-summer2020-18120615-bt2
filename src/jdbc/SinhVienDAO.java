package jdbc;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.SinhVien;

public class SinhVienDAO {
	public static void Insert(String mssv, String maLop, String hoTen, String gioiTinh, String cmnd) {
		SinhVien sv = new SinhVien(mssv, maLop, hoTen, gioiTinh, cmnd);
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(sv);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public static List<SinhVien> GetList(String maLop) {
		List<SinhVien> dsSinhVien = null;
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;

		try {
			Query query = session.createQuery("SELECT sv FROM SinhVien sv WHERE sv.maLop = :maLop");
			query.setString("maLop", maLop);
			dsSinhVien = query.list();

		} catch (HibernateException e) {

		} finally {
			session.close();
		}

		return dsSinhVien;
	}

	public static List<SinhVien> GetList(String maLop, String maMon) {
		List<SinhVien> dsSinhVien = null;
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;

		try {
			Query query = session.createQuery("SELECT bd.sinhVien FROM BangDiem bd WHERE "
					+ "bd.monHoc.maLop = :maLop AND bd.monHoc.maMon = :maMon");
			query.setString("maLop", maLop);
			query.setString("maMon", maMon);
			dsSinhVien = query.list();
		} catch (HibernateException e) {

		} finally {
			session.close();
		}

		return dsSinhVien;
	}
}
