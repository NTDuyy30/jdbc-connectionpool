package it6020002.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it6020002.ConnectionPool;
import it6020002.ConnectionPoolImpl;
import it6020002.object.StudentObject;

public class Student {
	private ConnectionPool cp;
	private Connection con;
	
	public Student() {
		this.cp = new ConnectionPoolImpl();
		try {
			this.con = this.cp.getConnection("Student");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StudentObject> getStudentObjects(StudentObject similar, byte total) {
		ArrayList<StudentObject> items = new ArrayList<>();
		
		String sql = "SELECT * FROM sinhvien ";
		sql += "";
		sql += "LIMIT ?";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setByte(1, total);
			ResultSet rs = pre.executeQuery();
			
			if (rs != null) {
				StudentObject item;
				while (rs.next()) {
					item = new StudentObject();
					item.setMasv(rs.getString("masv"));
					item.setTensv(rs.getString("tensv"));
					items.add(item);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
		return items;
	}
	
	public boolean addStudentObject(StudentObject item) {
		StringBuilder sql = new StringBuilder(); 
		sql.append("INSERT INTO sinhvien(");
		sql.append("masv, tensv, diachi, gioitinh) ");
		sql.append("VALUES(?, ?, ?, ?)");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getMasv());
			pre.setString(2, item.getTensv());
			pre.setString(3, item.getDiachi());
			pre.setBoolean(4, item.isGioitinh());
			
			int result = pre.executeUpdate();
			
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}		
	}
	
	public boolean updateStudentObject() {
		StringBuilder sql = new StringBuilder(); 
		sql.append("UPDATE sinhvien ");
		sql.append("SET tensv = 'Ha', diachi = 'Thai Nguyen' ");
		sql.append("WHERE masv = '2'");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			int result = pre.executeUpdate();
			
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}		
	}
	
	public boolean deleteStudentObject() {
		StringBuilder sql = new StringBuilder(); 
		sql.append("DELETE FROM sinhvien ");
		sql.append("WHERE masv='3'");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			int result = pre.executeUpdate();
			
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}		
	}
	
	public static void main(String[] args) {
		Student st = new Student();
		ArrayList<StudentObject> list = st.getStudentObjects(null, (byte)10);
		list.forEach(item ->  {
			System.out.println(item);
		});
//		System.out.println(st.getStudentObjects(null, (byte)2));
		
		// Tao chuyen muc moi
//		StudentObject nstudent = new StudentObject();
//		nstudent.setMasv("3");
//		nstudent.setTensv("Hieu");
//		nstudent.setDiachi("Viet Nam");
//		nstudent.setGioitinh(true);
//		
//		if (!st.addStudentObject(nstudent)) {
//			System.out.println("\n---------KHONG THANH CONG-----------\n");
//		} else {
//			System.out.println("\n---------RAT THANH CONG-----------\n");
//		}
		
//		Update student
//		if (!st.updateStudentObject()) {
//			System.out.println("\n---------KHONG THANH CONG-----------\n");
//		} else {
//			System.out.println("\n---------RAT THANH CONG-----------\n");
//		}
		
		
//		Delete student
//		if (!st.deleteStudentObject()) {
//			System.out.println("\n---------KHONG THANH CONG-----------\n");
//		} else {
//			System.out.println("\n---------RAT THANH CONG-----------\n");
//		}
	}
}
