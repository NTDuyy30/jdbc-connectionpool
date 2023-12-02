package it6020002.object;

public class StudentObject {
	private String masv;
	private String tensv;
	private String diachi;
	private boolean gioitinh;
	
	public StudentObject() {
		
	}

	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
	}

	public String getTensv() {
		return tensv;
	}

	public void setTensv(String tensv) {
		this.tensv = tensv;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	@Override
	public String toString() {
		return "StudentObject [masv=" + masv + ", tensv=" + tensv + "]";
	}
	
	
}
