

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Input.do")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InputServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 객체에 존재하는 파라미터 가져오기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// response 를 통해 사용자에게 응답할 내용 작성하기
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		 out.println("<HEAD>");
		 out.println("<TITLE>get/post 방식 예제</TITLE>");
		 out.println("</HEAD>");
		 out.println("<BODY>");
		 out.println("<h2>입력하신 데이터(get)</h2>");
		 out.printf("이름 : %s<br>", name);
		 out.printf("나이 : %d<br>", age);
		 out.println("</BODY>");
		 out.println("</HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 객체에 존재하는 파라미터 가져오기
		request.setCharacterEncoding("UTF-8"); // 인코딩 설정은 무조건 파라미터를 하나라도 불러오기 전에 해주어야 한다
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
				
		// response 를 통해 사용자에게 응답할 내용 작성하기
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>get/post 방식 예제</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<h2>입력하신 데이터(post)</h2>");
		out.printf("이름 : %s<br>", name);
		out.printf("나이 : %d<br>", age);
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
