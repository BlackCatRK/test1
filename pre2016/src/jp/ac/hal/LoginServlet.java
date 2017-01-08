package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		request.setCharacterEncoding("utf8");
		String userid=request.getParameter("userid");
		String pw=request.getParameter("pw");
		String sendUrl="";
		UserDAO dao=new UserDAO();
		User user=dao.select(userid, pw);
		System.out.println(user);
		
		
		if(user!=null){
			session.setAttribute("userS", user);
			sendUrl="HomeServlet";
		}else{
			sendUrl="login.jsp";
			request.setAttribute("errMsg", "IDまたはパスワードが違います");
			session.invalidate();
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
