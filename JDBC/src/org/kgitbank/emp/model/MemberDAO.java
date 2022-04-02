package org.kgitbank.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("드라이버 로드 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Connection getConnection() {
		DataSource ds = null;
		Connection con = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {}
		}
	}
	
	// 회원가입
	public void insertMember(MemberVO mem) {
		
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem.getUserId());
			stmt.setString(2, mem.getName());
			stmt.setString(3, mem.getPassword());
			stmt.setString(4, mem.getEmail());
			stmt.setString(5, mem.getAddress());
			int result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		
	}
	
	// 아이디로 비밀번호 조회
	public String getPassword(String userId){
		Connection con = null;
		String mempw = null;
		
		try {
			con = getConnection();
			String sql = "select password from member where userid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				mempw = rs.getString("password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return mempw;
	}
	
	// 아이디로 전체정보 조회
	public MemberVO getMember(String userId){
		Connection con = null;
		MemberVO mem = new MemberVO();
		
		try {
			con = getConnection();
			String sql = "select * from member where userid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return mem;
	}
	
	// 회원 정보 삭제
	public void deleteMem(String userId) {
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "delete from mamber where userid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			int result = stmt.executeUpdate();
			if(result <= 0) {
				throw new RuntimeException("삭제가 되지 않았습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
