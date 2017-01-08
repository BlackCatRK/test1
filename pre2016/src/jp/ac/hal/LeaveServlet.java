package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LeaveServlet
 */
@WebServlet("/LeaveServlet")
public class LeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cnt=0;
		HttpSession session=request.getSession();
		Prj_user pu=(Prj_user)session.getAttribute("puS");
		int uid=pu.getUser_id();
		int pid=pu.getProject_id();
		
		Prj_userDAO dao=new Prj_userDAO();
		cnt=dao.leave(uid, pid);
		
		if(cnt==0){
			session.removeAttribute("puS");
			request.setAttribute("msg", "プロジェクトを抜けました");
		}else{
			request.setAttribute("msg", "エラーです");
		}
		request.setAttribute("id", pid);
		
		request.getRequestDispatcher("leave_success.html").forward(request,response);
		
	}

}
