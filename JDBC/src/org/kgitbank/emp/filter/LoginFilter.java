package org.kgitbank.emp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/Emp.do","/emp/*"})
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		HttpSession session = hreq.getSession();
		if(session.getAttribute("userId")==null) {
			hres.sendRedirect("/JDBC/login.jsp");
			return; // sendRedirect로 이미 응답을 끝냈는데, 아래의 doFilter가 실행되어버려 발생하는 오류를 방지하기 위함.
		}
		
		chain.doFilter(request, response); // 원래 가려던 길 : 전후로 나뉘어져, 전처리/후처리
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
