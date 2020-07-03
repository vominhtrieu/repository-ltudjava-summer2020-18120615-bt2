package pojo;

import java.io.*;

public class BangDiem implements Serializable {
	private static final long serialVersionUID = 2L;
	private SinhVien sinhVien;
	private MonHoc monHoc;
	private double diemGk;
	private double diemCk;
	private double diemKhac;
	private double diemTong;
	
	public BangDiem() {
	}
	
	public BangDiem(SinhVien sinhVien, MonHoc monHoc,
			double diemGk, double diemCk, double diemKhac, double diemTong) {
		this.setSinhVien(sinhVien);
		this.setMonHoc(monHoc);
		this.setDiemGk(diemGk);
		this.setDiemCk(diemCk);
		this.setDiemKhac(diemKhac);
		this.setDiemTong(diemTong);
	}
	
	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public double getDiemGk() {
		return diemGk;
	}

	public void setDiemGk(double diemGk) {
		this.diemGk = diemGk;
	}

	public double getDiemCk() {
		return diemCk;
	}

	public void setDiemCk(double diemCk) {
		this.diemCk = diemCk;
	}

	public double getDiemKhac() {
		return diemKhac;
	}

	public void setDiemKhac(double diemKhac) {
		this.diemKhac = diemKhac;
	}

	public double getDiemTong() {
		return diemTong;
	}

	public void setDiemTong(double diemTong) {
		this.diemTong = diemTong;
	}
	
	
}
