

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SRDF.do")
public class SRDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SRDFServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// 사용자에게 보이지 않고, 단지 동일한 주소 내의 동작을 구분하기 위한 파라미터.
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("sr".equals(action)) {
			response.sendRedirect("/sddfresult.jsp");
		} else if("df".equals(action)) {
			RequestDispatcher disp = request.getRequestDispatcher("/sddfresult.jsp");
			disp.forward(request, response);
		}
	}

}
