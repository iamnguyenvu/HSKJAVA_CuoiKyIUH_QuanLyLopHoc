package entity;

import java.util.Objects;

public class LopHoc_entity {
	private String maLop;
	private String tenLop;
	private String maGiaoVien;
	private int siSo;
	
	public LopHoc_entity() {
		// TODO Auto-generated constructor stub
	}

	public LopHoc_entity(String maLop) {
		super();
		this.maLop = maLop;
	}

	public LopHoc_entity(String maLop, String tenLop, String maGiaoVien, int siSo) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.maGiaoVien = maGiaoVien;
		this.siSo = siSo;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getMaGiaoVien() {
		return maGiaoVien;
	}

	public void setMaGiaoVien(String maGiaoVien) {
		this.maGiaoVien = maGiaoVien;
	}

	public int getSiSo() {
		return siSo;
	}

	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}

	@Override
	public String toString() {
		return "LopHoc_entity [maLop=" + maLop + ", tenLop=" + tenLop + ", maGiaoVien=" + maGiaoVien + ", siSo=" + siSo
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLop);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopHoc_entity other = (LopHoc_entity) obj;
		return Objects.equals(maLop, other.maLop);
	}

}
