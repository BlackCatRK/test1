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
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/BbsServlet_")
public class BbsServlet_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsServlet_() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sendUrl="BBS.jsp";
		
		HttpSession session=request.getSession();
		
		User u=(User)session.getAttribute("userS");
		
		if(u==null){
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
		
		Project p=(Project)session.getAttribute("prjS");
		int pid=p.getId();
		
		String bbsid=request.getParameter("bid");
		int bid=Integer.parseInt(bbsid);
		
		BbsDAO dao=new BbsDAO();
		Bbs bbs=dao.select(pid,bid);
		
		BbsWriteDAO_ dao2=new BbsWriteDAO_();
		ArrayList<BbsWrite> bbsw=new ArrayList<BbsWrite>();
		bbsw=dao2.listW(pid,bid);
		
		
		request.setAttribute("bbs", bbs);
		request.setAttribute("bbswL", bbsw);
		request.setAttribute("bid", bid);
		
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
