package org.kgitbank.emp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kgitbank.emp.model.EmpDAO;
import org.kgitbank.emp.model.EmpVO;

@WebServlet("/Emp.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmpDAO empDao;
	
	public EmpServlet() {
        super();
        empDao = new EmpDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // 얻고자 하는 데이터가 어떤 건지 알려줌.
		String url = "";
		
		if("list".equals(action)) {
			
			List<EmpVO> empList = empDao.getEmpList();
			request.setAttribute("empList", empList);
			url = "/emp/empList.jsp";
			
		} else if("view".equals(action)) {
			
			int empId = Integer.parseInt(request.getParameter("empId")); // 조회하고 싶은 사원이 누군지 알려줌.
			EmpVO emp = empDao.getEmpInfo(empId);
			request.setAttribute("emp", emp);
			url = "/emp/empView.jsp";
			
		} else if("insert".equals(action)) {
			
			request.setAttribute("jobList", empDao.getJobList());
			request.setAttribute("manList", empDao.getManagerList());
			request.setAttribute("deptList", empDao.getDeptList());
			request.setAttribute("action", action); // insert 페이지에서 수정/입력을 구분하기 위함.
			url = "/emp/empInsert.jsp";
			
		} else if("update".equals(action)) {
			
			int empId = Integer.parseInt(request.getParameter("empId"));
			request.setAttribute("jobList", empDao.getJobList());
			request.setAttribute("manList", empDao.getManagerList());
			request.setAttribute("deptList", empDao.getDeptList());
			EmpVO emp = empDao.getEmpInfo(empId);
			request.setAttribute("emp", emp);
			request.setAttribute("action", action);
			url = "/emp/empInsert.jsp";
			
		} else if("delete".equals(action)) {
			
			int empId = Integer.parseInt(request.getParameter("empId"));
			request.setAttribute("emp", empDao.getEmpInfo(empId));
			request.setAttribute("action", action);
			url = "/emp/empDelete.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response); // 중복되는 코드를 밖으로 빼내어 단순하게.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("insert".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			java.sql.Date hireDate = null;
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date jDate = tool.parse(request.getParameter("hireDate"));
				hireDate = new java.sql.Date(jDate.getTime()); // db에 들어갈 수 있는 형식으로 변경.
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String jobId = request.getParameter("jobId");
			double salary = Double.parseDouble(request.getParameter("salary"));
			double commissionPct = Double.parseDouble(request.getParameter("commissionPct"));
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			
			// 변수명을 동일하게 맞춰주면 코드작성이 아주 간편해짐.
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(empId);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setHireDate(hireDate);
			emp.setJobId(jobId);
			emp.setSalary(salary);
			emp.setCommissionPct(commissionPct);
			emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);
			empDao.insertEmp(emp); // EmpDAO 에 있는 메서드를 활용하여 입력받은 데이터 db에 작성.
			response.sendRedirect("/JDBC/Emp.do?action=list");
			
		} else if("update".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			java.sql.Date hireDate = null;
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date jDate = tool.parse(request.getParameter("hireDate"));
				hireDate = new java.sql.Date(jDate.getTime()); // db에 들어갈 수 있는 형식으로 변경.
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String jobId = request.getParameter("jobId");
			double salary = Double.parseDouble(request.getParameter("salary"));
			double commissionPct = Double.parseDouble(request.getParameter("commissionPct"));
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			
			// 변수명을 동일하게 맞춰주면 코드작성이 아주 간편해짐.
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(empId);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setHireDate(hireDate);
			emp.setJobId(jobId);
			emp.setSalary(salary);
			emp.setCommissionPct(commissionPct);
			emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);
			empDao.updateEmp(emp);
			response.sendRedirect("/JDBC/Emp.do?action=view&empId="+emp.getEmployeeId());
			
		} else if("delete".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			empDao.deleteEmp(empId);
			response.sendRedirect("/JDBC/Emp.do?action=list");
		}
	}

}
