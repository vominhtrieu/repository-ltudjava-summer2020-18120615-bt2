package pojo;

public class ThoiKhoaBieu {
	private int stt;
	private String maMon;
	private String tenMon;
	private String phongHoc;
	private String lop;
	
	public ThoiKhoaBieu(int stt, String maMon, String tenMon, String phongHoc, String lop) {
		this.setStt(stt);
		this.setMaMon(maMon);
		this.setTenMon(tenMon);
		this.setPhongHoc(phongHoc);
		this.setLop(lop);
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
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

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}
	
	
}
