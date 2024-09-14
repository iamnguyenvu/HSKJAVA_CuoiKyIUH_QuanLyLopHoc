package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.LopHoc_entity;

public class LopHoc_dao {
	public boolean create(LopHoc_entity lh) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LopHoc values(?, ?, ?, ?)");
			stmt.setString(1, lh.getMaLop());
			stmt.setString(2, lh.getTenLop());
			stmt.setString(3, lh.getMaGiaoVien());
			stmt.setInt(4, lh.getSiSo());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			 try {
	             stmt.close();
	         } catch (SQLException e) {
	              e.printStackTrace();
	         }
		}
		return n>0;
	}
	
	public boolean delete(String maLop) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from LopHoc where maLop=?");
			stmt.setString(1, maLop);
			if(stmt.executeUpdate() > 0) return true;
		} catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return false;
	}
	
	public boolean update(LopHoc_entity lh) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHoc set tenLop=?, maGiaoVien=?, siSo=? where maLop=?");
			stmt.setString(1, lh.getTenLop());
			stmt.setString(2, lh.getMaGiaoVien());
			stmt.setInt(3, lh.getSiSo());
			stmt.setString(4, lh.getMaLop());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			 try {
	             stmt.close();
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
		}
		return n > 0;
		
	}
	
	public ArrayList<LopHoc_entity> getDSLopHoc() {
		ArrayList<LopHoc_entity> dsLH = new ArrayList<LopHoc_entity>();
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from LopHoc");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				LopHoc_entity lh = new LopHoc_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dsLH.add(lh);
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
		return dsLH;
	}
	
	public LopHoc_entity getLopHocByMaLop(String maLop) {
		LopHoc_entity lh = new LopHoc_entity();
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from LopHoc where maLop=?");
			stmt.setString(1, maLop);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lh = new LopHoc_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
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
		return lh;
	}
	
	public ArrayList<LopHoc_entity> getDSLopHocByMaGV(String maGiaoVien) {
		ArrayList<LopHoc_entity> dsLH = new ArrayList<LopHoc_entity>();
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from LopHoc where maGiaoVien=?");
			stmt.setString(1, maGiaoVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				LopHoc_entity lh = new LopHoc_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dsLH.add(lh);
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
		return dsLH;
	}
	
	public String findTenGV(String maLop) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		String tenGiaoVien = "";
		try {
			stmt = con.prepareStatement("select tenGiaoVien from GiaoVien gv join LopHoc lh on gv.maGiaoVien = lh.maGiaoVien where maLop=?");
			stmt.setString(1, maLop);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				tenGiaoVien = rs.getString(1);
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
	
	public ArrayList<LopHoc_entity> locTheoTenGV(String tenGiaoVien) {
		Connection con = connectDB.accessDataBase();
		PreparedStatement stmt = null;
		ArrayList<LopHoc_entity> dsLH = new ArrayList<LopHoc_entity>();
		String maGiaoVien = "";
		try {
			stmt = con.prepareStatement("select maGiaoVien from GiaoVien where tenGiaoVien=?");
			stmt.setString(1, tenGiaoVien);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				maGiaoVien = rs.getString(1);
			}
			stmt = con.prepareStatement("select * from LopHoc where maGiaoVien=?");
			stmt.setString(1, maGiaoVien);
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				LopHoc_entity lh = new LopHoc_entity(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4));
				dsLH.add(lh);
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
		return dsLH;
	}
	

}
