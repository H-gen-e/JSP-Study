package org.kgitbank.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kgitbank.emp.model.MemberDAO;
import org.kgitbank.emp.model.MemberVO;

import oracle.net.ns.SessionAtts;

@WebServlet("/Mem.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberDAO memDao;
	
    public MemberServlet() {
        super();
        memDao = new MemberDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String url = "";
		
		if("update".equals(action)) {
			
			String userId = request.getParameter("userId");
			MemberVO mem = memDao.getMember(userId);
			request.setAttribute("mem", mem);
			request.setAttribute("action", action);
			url = "/memInsert.jsp";
			
			
		} else if("delete".equals(action)) {
			
			url = "/memDelete.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String  action = request.getParameter("action");
		String url = "";
		
		if("insert".equals(action)) {
			String userId = request.getParameter("userid");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			MemberVO mem = new MemberVO();
			mem.setUserId(userId);
			mem.setName(name);
			mem.setPassword(password);
			mem.setEmail(email);
			mem.setAddress(address);
			
			memDao.insertMember(mem);
			url = "/JDBC/login.jsp";
			
		} else if("update".equals(action)) {
			
		} else if("delete".equals(action)) {
			
			String password = request.getParameter("password");
			String userId = (String)request.getSession().getAttribute("userId");
			String mempw = memDao.getPassword(userId);
			
			if(mempw.equals(password)){
				memDao.deleteMem(userId);
				request.getSession().invalidate();
				url = "/JDBC/login.jsp";
			} else {
				request.setAttribute("message", "비밀번호가 다릅니다.");
				url = "/memDelete.jsp";
			}
			
			
		}
		
		
		
		response.sendRedirect(url);
		
	}

}
