package jp.ac.hal;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		
		User u1=(User)session.getAttribute("userS");
		if(u1==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		Project prj=(Project)session.getAttribute("prjS");
		int pid=prj.getId();
		System.out.println(pid);
		ArrayList<Prj_user> list=new ArrayList<Prj_user>();
		Prj_userDAO dao=new Prj_userDAO();
		list=dao.listMember(pid);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("memberlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
