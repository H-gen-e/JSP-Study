package org.kgitbank.emp.model;
import java.util.List;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
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
	
	// 직원 목록 
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
				emp.setPhoneNumber(rs.getString("phone_Number"));
				emp.setHireDate(rs.getDate("hire_Date"));
				emp.setJobId(rs.getString("job_Id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_Pct"));
				emp.setManagerId(rs.getInt("manager_Id"));
				emp.setDepartmentId(rs.getInt("department_Id"));
				empList.add(emp);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return empList;
	}
	
	// 직원 상세 정보
	public EmpVO getEmpInfo(int empId) {
		Connection con = null;
		EmpVO emp = new EmpVO();
		
		try {
			con = getConnection();
			String sql = "select * from employees where employee_id=?"; // prepared stmt 이용시, 넣어주고 싶은 값은 ?로 작업하여 간략히할 수 있음.
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId); // 1번째 물음표에 empId 를 넣어줌.
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { // 데이터가 하나만 있는 경우, if문.
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_Number"));
				emp.setHireDate(rs.getDate("hire_Date"));
				emp.setJobId(rs.getString("job_Id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_Pct"));
				emp.setManagerId(rs.getInt("manager_Id"));
				emp.setDepartmentId(rs.getInt("department_Id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return emp;
	}
	
	// 직무 목록
	public List<JobVO> getJobList(){
		Connection con = null;
		List<JobVO> jobList = new ArrayList<JobVO>();
		
		try {
			con = getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				JobVO job = new JobVO();
				job.setJobId(rs.getString("job_id"));
				job.setJobTitle(rs.getString("job_title"));
				jobList.add(job);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return jobList;
	}
	
	// 매니저 목록
	public List<EmpVO> getManagerList() {
		Connection con = null;
		List<EmpVO> manList = new ArrayList<EmpVO>();
		
		try {
			con = getConnection();
			String sql = "select employee_id, first_name||''||last_name as manager_name "
					+ "from employees "
					+ "where employee_id in "
					+ "(select distinct manager_id from employees)";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("manager_name"));
				manList.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return manList;
	}
	
	// 부서 목록
	public List<DeptVO> getDeptList() {
		Connection con = null;
		List<DeptVO> deptList = new ArrayList<DeptVO>();
		
		try {
			con = getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				deptList.add(dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
		return deptList;
	}
	
	// 사원 정보 입력
	public void insertEmp(EmpVO emp) {
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setDate(6, emp.getHireDate());
			stmt.setString(7, emp.getJobId());
			stmt.setDouble(8, emp.getSalary());
			stmt.setDouble(9, emp.getCommissionPct());
			stmt.setInt(10, emp.getManagerId());
			stmt.setInt(11, emp.getDepartmentId());
			int result = stmt.executeUpdate(); // insert 는 결과값이 숫자 하나.
		} catch (Exception e) {
			if(e.getMessage().contains("0001")) { // error - 0001 은 입력한 데이터가 중복되는 경우.
				e.printStackTrace();
				throw new RuntimeException("데이터가 중복됩니다. 콘솔을 확인하세요.");
			}
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
	}
	
	// 사원 정보 수정
	public void updateEmp(EmpVO emp) {
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "update employees set first_name=?, last_name=?, email=?,"
					+ "phone_number=?, hire_date=?, job_id=?, salary=?,"
					+ "commission_pct=?, manager_id=?, department_id=?"
					+ "where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, emp.getFirstName());
			stmt.setString(2, emp.getLastName());
			stmt.setString(3, emp.getEmail());
			stmt.setString(4, emp.getPhoneNumber());
			stmt.setDate(5, emp.getHireDate());
			stmt.setString(6, emp.getJobId());
			stmt.setDouble(7, emp.getSalary());
			stmt.setDouble(8, emp.getCommissionPct());
			stmt.setInt(9, emp.getManagerId());
			stmt.setInt(10, emp.getDepartmentId());
			stmt.setInt(11, emp.getEmployeeId());
			int result = stmt.executeUpdate();
			if(result <= 0) {
				throw new RuntimeException("수정이 되지 않았습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();}catch(SQLException e) {}}
		}
	}
	
	
	// 사원 정보 삭제
	public void deleteEmp(int empId) {
		Connection con = null;
		
		try {
			
			con = getConnection();
			String sql = "delete from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
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
