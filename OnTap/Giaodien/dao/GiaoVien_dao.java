package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.GiaoVien_entity;

public class GiaoVien_dao {
	public ArrayList<GiaoVien_entity> getDSGiaoVien() {
		ArrayList<GiaoVien_entity> dsGV = new ArrayList<GiaoVien_entity>();
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from GiaoVien");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				GiaoVien_entity gv = new GiaoVien_entity(rs.getString(1), rs.getString(2));
				dsGV.add(gv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsGV;
		
	}
	
	public String findTenGV(String maGiaoVien) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		String tenGiaoVien = "";
		try {
			stmt = con.prepareStatement("select tenGiaoVien from GiaoVien where maGiaoVien=?");
			stmt.setString(1, maGiaoVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tenGiaoVien = rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return tenGiaoVien;
	}
	
	public String findMaGV(String tenGiaoVien) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		String maGiaoVien = "";
		try {
			stmt = con.prepareStatement("select maGiaoVien from GiaoVien where tenGiaoVien=?");
			stmt.setString(1, tenGiaoVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				maGiaoVien = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return maGiaoVien;
	}

}
