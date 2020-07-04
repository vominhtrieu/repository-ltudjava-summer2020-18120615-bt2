package jdbc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.BangDiem;
import pojo.MonHoc;
import pojo.SinhVien;

public class BangDiemDAO {
	public void Insert(String mssv, String maMon, String maLop, double diemGk, double diemCk, double diemKhac,
			double diemTong) {
		Session session = HibernateUtility.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SinhVien sv = new SinhVien(mssv);
			MonHoc mon = new MonHoc(maMon, maLop);
			BangDiem bangDiem = new BangDiem(sv, mon, diemGk, diemCk, diemKhac, diemTong);
			session.save(bangDiem);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}
}
