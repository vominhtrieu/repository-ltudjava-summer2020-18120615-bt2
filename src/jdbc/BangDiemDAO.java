package jdbc;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.BangDiem;
import pojo.MonHoc;
import pojo.SinhVien;

public class BangDiemDAO {
	public static void Insert(String mssv, String maMon, String maLop, double diemGk, double diemCk, double diemKhac,
			double diemTong) {
		Session session = HibernateUtility.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SinhVien sv = new SinhVien(mssv);
			MonHoc mon = new MonHoc(maMon, maLop);
			BangDiem bangDiem = new BangDiem(sv, mon, diemGk, diemCk, diemKhac, diemTong);
			session.saveOrUpdate(bangDiem);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public static List<BangDiem> getList(String maLop, String maMon) {
		List<BangDiem> dsBangDiem = null;
		Session session = HibernateUtility.getSession();
		try {
			Query query = session
					.createQuery("SELECT bd FROM BangDiem bd WHERE bd.monHoc.maLop=:maLop AND bd.monHoc.maMon=:maMon");
			query.setString("maLop", maLop);
			query.setString("maMon", maMon);
			dsBangDiem = query.list();
			for (BangDiem bangDiem : dsBangDiem) {
				String mssv = bangDiem.getSinhVien().getMssv();
				String hoTen = bangDiem.getSinhVien().getHoTen();
				bangDiem.setSinhVien(new SinhVien(mssv, "", hoTen, "", ""));
			}
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
		return dsBangDiem;
	}

	public static List<BangDiem> getList(String mssv) {
		List<BangDiem> dsBangDiem = null;
		Session session = HibernateUtility.getSession();
		try {
			Query query = session.createQuery("SELECT bd FROM BangDiem bd WHERE bd.sinhVien.mssv=:mssv");
			query.setString("mssv", mssv);
			dsBangDiem = query.list();
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
		return dsBangDiem;
	}

	public static BangDiem get(String mssv, String maLop, String maMon) {
		BangDiem bangDiem = null;
		Session session = HibernateUtility.getSession();
		try {
			Query query = session.createQuery("SELECT bd FROM BangDiem bd WHERE bd.sinhVien.mssv=:mssv AND "
					+ "bd.monHoc.maLop=:maLop AND bd.monHoc.maMon=:maMon");
			query.setString("mssv", mssv);
			query.setString("maLop", maLop);
			query.setString("maMon", maMon);
			bangDiem = (BangDiem) query.list().get(0);
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
		return bangDiem;
	}

	public static void edit(String mssv, String maLop, String maMon, double diemGk, double diemCk, double diemKhac,
			double diemTong) {
		Session session = HibernateUtility.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SinhVien sv = new SinhVien(mssv);
			MonHoc mon = new MonHoc(maMon, maLop);
			BangDiem bangDiem = new BangDiem(sv, mon, diemGk, diemCk, diemKhac, diemTong);
			session.update(bangDiem);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}
}
