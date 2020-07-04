package pojo;

import java.io.Serializable;

public class MonHoc implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maMon;
	private String maLop;
	private String tenMon;
	private String phongHoc;

	public MonHoc() {
	}

	public MonHoc(String maMon, String maLop) {
		this.setMaMon(maMon);
		this.setMaLop(maLop);
	}

	public MonHoc(String maMon, String maLop, String tenMon, String phongHoc) {
		this.setMaMon(maMon);
		this.setMaLop(maLop);
		this.setTenMon(tenMon);
		this.setPhongHoc(phongHoc);
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public String getPhongHoc() {
		return phongHoc;
	}

	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
}
