

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃 : 세션을 날리는 것.
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("hgene0929") && pw.equals("1234")) {
			// 로그인 기록을 세션에 남기기 위한 코드.
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			response.sendRedirect("/mypage.jsp");
		} else {
			request.setAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
