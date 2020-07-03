package jdbc;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.BangDiem;
import pojo.MonHoc;
import pojo.SinhVien;

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

			for (SinhVien sv : dsSinhVien) {
				BangDiem bangDiem = new BangDiem(sv, monHoc, 0, 0, 0, 0);
				session.save(bangDiem);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public static List<MonHoc> getListByID(String mssv) {
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;
		List<MonHoc> dsMonHoc = null;

		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT bd.monHoc FROM BangDiem bd WHERE bd.sinhVien.mssv = :mssv");
			query.setString("mssv", mssv);

			dsMonHoc = query.list();

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}

		return dsMonHoc;
	}

	public static List<MonHoc> getListBySubjectID(String maMon) {
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;
		List<MonHoc> dsMonHoc = null;

		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT mon FROM MonHoc mon WHERE mon.maMon = :maMon");
			query.setString("maMon", maMon);

			dsMonHoc = query.list();

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}

		return dsMonHoc;
	}

	public static void ChangeClass(String mssv, String maMonHoc, String maLopCu, String maLopMoi) {
		Session session = HibernateUtility.getSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Query query1 = session.createQuery("SELECT bd FROM BangDiem bd WHERE bd.sinhVien.mssv = :mssv"
					+ " AND bd.monHoc.maMon = :maMon AND bd.monHoc.maLop = :maLop");
			query1.setString("mssv", mssv);
			query1.setString("maMon", maMonHoc);
			query1.setString("maLop", maLopCu);

			BangDiem bangDiem = (BangDiem) query1.list().get(0);
			session.delete(bangDiem);
			System.out.println(bangDiem.getMonHoc().getMaLop());

			Query query2 = session
					.createQuery("SELECT mon FROM MonHoc mon WHERE mon.maMon = :maMon AND mon.maLop = :maLop");
			query2.setString("maMon", maMonHoc);
			query2.setString("maLop", maLopMoi);
			MonHoc monHoc = (MonHoc) query2.list().get(0);

			bangDiem.setMonHoc(monHoc);
			session.save(bangDiem);

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}
}
