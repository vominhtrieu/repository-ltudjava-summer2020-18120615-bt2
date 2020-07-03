package pojo;

public class SinhVien {
	private String mssv;
	private String maLop;
	private String hoTen;
	private String gioiTinh;
	private String cmnd;

	public SinhVien() {
	}

	public SinhVien(String mssv, String maLop, String hoTen, String gioiTinh, String cmnd) {
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.maLop = maLop;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String lop) {
		this.maLop = lop;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
}
