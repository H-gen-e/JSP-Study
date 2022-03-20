import java.util.List;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO {
	
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
	
	
	public List<EmpVO> getEmpList() {
		Connection con = null;
		List<EmpVO> empList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // 실행할 쿼리를 담고있는 인스턴스
			ResultSet rs = stmt.executeQuery(); // select 쿼리문을 담기 위한 인스턴스
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phoneNumber"));
				emp.setHireDate(rs.getDate("hireDate"));
				emp.setJobId(rs.getString("jobId"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commissionPct"));
				emp.setManagerId(rs.getInt("managerId"));
				emp.setDepartmentId(rs.getInt("departmentId"));
				empList.add(emp);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return empList;
	}
	
	
	
	
	
	
	
	
	

}
