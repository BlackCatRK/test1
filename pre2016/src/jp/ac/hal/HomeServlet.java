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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		User u=(User)session.getAttribute("userS");
		
		if(u==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		ArrayList<Project> prj = new ArrayList<Project>();
		ArrayList<Project> prjn = new ArrayList<Project>();
		ArrayList<HomeBBS> bbsh = new ArrayList<HomeBBS>();
		ArrayList<HomeEvent> eventh = new ArrayList<HomeEvent>();
		
		int id=u.getId();
		System.out.println("userid is"+id);
				
		ProjectDAO dao2=new ProjectDAO();
		prj=dao2.listHome();
		request.setAttribute("prjS0", prj);
		prjn=dao2.listHomeNow(id);
		request.setAttribute("prjnS0", prjn);
		
		BbsDAO dao3=new BbsDAO();
		bbsh=dao3.listHome(id);
		request.setAttribute("bbsh", bbsh);
		
		EventDAO dao4=new EventDAO();
		eventh=dao4.listHome(id);
		request.setAttribute("eventh", eventh);
		
		request.getRequestDispatcher("home.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
