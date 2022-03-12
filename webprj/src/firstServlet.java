

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first.do") // 해당 서블릿 페이지가 가지는 주소
public class firstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public firstServlet() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.println("<HTML>");
		 out.println("<HEAD>");
		 out.println("<TITLE>첫 예제 페이지</TITLE>");
		 out.println("</HEAD>");
		 out.println("<BODY>");
		 out.println("<h3>안녕하세요! 첫 페이지입니다.</h3>");
		 out.println("</BODY>");
		 out.println("</HTML>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
