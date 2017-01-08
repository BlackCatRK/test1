package jp.ac.hal;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet2() {
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
		HttpSession sessionU = request.getSession(true);
		HttpSession sessionP = request.getSession(true);
		
		request.setCharacterEncoding("utf8");
		String userid=request.getParameter("userid");
		String pw=request.getParameter("pw");
		String sendUrl="";
		UserDAO dao=new UserDAO();
		User user=dao.select(userid, pw);
		System.out.println(user);
		ArrayList<Project> prj = new ArrayList<Project>();
		ArrayList<Project> prjn = new ArrayList<Project>();
		
		if(user!=null){
			sendUrl="home.jsp";
			sessionU.setAttribute("userS", user);
			ProjectDAO dao2=new ProjectDAO();
			prj=dao2.listHome();
			sessionP.setAttribute("prjS0", prj);
			int id=user.getId();
			prjn=dao2.listHomeNow(id);
			sessionP.setAttribute("prjnS0", prjn);
		}else{
			sendUrl="login.jsp";
			request.setAttribute("errMsg", "IDまたはパスワードが違います");
			sessionU.invalidate();
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
