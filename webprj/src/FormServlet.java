

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Form.do")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FormServlet() {
        super();
    }
    
    // 요청시 파라미터를 받아오기 때문에 POST 방식 사용.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글로 구성된 파라미터를 요청시에 받기 때문에 인코딩 필수.
		
		// 아직 구축된 db가 없기 때문에 db 역할을 대신하기 위해 만든 HashMap.
		HashMap<String, String> hobbyMap = new HashMap<>();
		hobbyMap.put("1", "볼링"); hobbyMap.put("2", "하키"); hobbyMap.put("3", "조정"); hobbyMap.put("4", "승마");
		HashMap<String, String> areaMap = new HashMap<>();
		areaMap.put("1", "서울"); areaMap.put("2", "경기"); areaMap.put("3", "인천");
		
		// 요청받은 파라미터 가져오기.(controller 로서 Servlet 의 역할)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		java.util.Date birth = null;
		try {
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-mm-dd");
			birth = tool.parse(request.getParameter("birth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hobbys[] = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int grade = Integer.parseInt(request.getParameter("grade"));
		String area = request.getParameter("area");
		String intro = request.getParameter("intro");
		
		// 받은 파라미터들 response를 통해 화면에 내보내기.
		response.setContentType("text/HTML;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>form 태그 예제</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<h3>입력하신 데이터</h3>");
		out.printf("아이디 : %s<br>", id);
		out.printf("비밀번호 : %s<br>", pw);
		out.printf("생년월일 : %s<br>", birth);
		out.print("취미 : ");
		if(hobbys == null || hobbys.length == 0) {
			out.println("해당없음.<br>");
		} else {
			for(String hobbyNum: hobbys) {
				out.printf("%s ", hobbyMap.get(hobbyNum));
			}
			out.println("<br>");
		}
		out.printf("성별 : %s<br>", gender.equals("1") ? "남" : "여");
		out.printf("이메일 : %s<br>", email);
		out.printf("전화번호 : %s<br>", tel);
		out.printf("지역 : %s<br>", areaMap.get(area));
		out.printf("자기소개 : %s<br>", intro);
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
