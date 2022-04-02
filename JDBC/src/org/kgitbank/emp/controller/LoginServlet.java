package org.kgitbank.emp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kgitbank.emp.model.MemberDAO;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberDAO memDao;
	
    public LoginServlet() {
        super();
        memDao = new MemberDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/JDBC/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		String mempw = memDao.getPassword(userId);
		// 아이디와 비밀번호를 구분하여 오류 메세지 띄우지 X -> 해킹의 근거가 된다.
		if(mempw!=null && mempw.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("mem", memDao.getMember(userId));
			
			response.sendRedirect("/JDBC/home.jsp");
		} else {
			request.setAttribute("message", "아이디 또는 비밀번호가 다릅니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
