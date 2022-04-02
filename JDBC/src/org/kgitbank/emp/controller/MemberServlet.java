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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
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
		response.sendRedirect("/JDBC/login.jsp");
		
	}

}
