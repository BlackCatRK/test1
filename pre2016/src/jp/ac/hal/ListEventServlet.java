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
 * Servlet implementation class ListEventServlet
 */
@WebServlet("/ListEventServlet")
public class ListEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		Prj_user pu=(Prj_user)session.getAttribute("puS");
		int pid=pu.getProject_id();
		
		User u=(User)session.getAttribute("userS");
		int uid=u.getId();
		
		EventDAO dao= new EventDAO();
		ArrayList<Event> eventL=new ArrayList<Event>();
		eventL=dao.listH(pid);
		
		request.setAttribute("eventL", eventL);
		
		ProjectDAO dao2=new ProjectDAO();
		ArrayList<Project> prjn = new ArrayList<Project>();
		prjn=dao2.listHomeNow(uid);
		request.setAttribute("prjnS0", prjn);
		
		request.getRequestDispatcher("list_event.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
