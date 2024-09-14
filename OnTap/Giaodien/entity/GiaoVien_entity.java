package entity;

import java.util.Objects;

public class GiaoVien_entity {
	private String maGiaoVien;
	private String tenGiaoVien;
	
	public GiaoVien_entity() {
		// TODO Auto-generated constructor stub
	}

	public GiaoVien_entity(String maGiaoVien) {
		super();
		this.maGiaoVien = maGiaoVien;
	}

	public GiaoVien_entity(String maGiaoVien, String tenGiaoVien) {
		super();
		this.maGiaoVien = maGiaoVien;
		this.tenGiaoVien = tenGiaoVien;
	}

	public String getMaGiaoVien() {
		return maGiaoVien;
	}

	public void setMaGiaoVien(String maGiaoVien) {
		this.maGiaoVien = maGiaoVien;
	}

	public String getTenGiaoVien() {
		return tenGiaoVien;
	}

	public void setTenGiaoVien(String tenGiaoVien) {
		this.tenGiaoVien = tenGiaoVien;
	}

	@Override
	public String toString() {
		return "GiaoVien_entity [maGiaoVien=" + maGiaoVien + ", tenGiaoVien=" + tenGiaoVien + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGiaoVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiaoVien_entity other = (GiaoVien_entity) obj;
		return Objects.equals(maGiaoVien, other.maGiaoVien);
	}
}
