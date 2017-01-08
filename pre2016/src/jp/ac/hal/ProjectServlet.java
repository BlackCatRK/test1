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
@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String sendUrl="";
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("puS");
		User u1=(User)session.getAttribute("userS");
		
		if(u1==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		int uid=u1.getId();
		
		request.setCharacterEncoding("utf8");
		String id0=request.getParameter("id");
		int id=Integer.parseInt(id0);
		System.out.println(id);
		ProjectDAO dao=new ProjectDAO();
		Project prj=dao.select2(id);
		Prj_userDAO dao2=new Prj_userDAO();
		Prj_user pu=dao2.select(id,uid);
		if(prj!=null){
			BbsDAO dao3= new BbsDAO();
			ArrayList<Bbs> bbsL=new ArrayList<Bbs>();
			bbsL=dao3.listH(id);
			EventDAO dao4= new EventDAO();
			ArrayList<Event> eventL=new ArrayList<Event>();
			eventL=dao4.listH(id);
			
			ArrayList<Prj_user> memL=new ArrayList<Prj_user>();
			memL=dao2.listMember(id);
			
			sendUrl="prj_home.jsp";
			session.setAttribute("prjS", prj);
			request.setAttribute("bbsL", bbsL);
			request.setAttribute("eventL", eventL);
			session.setAttribute("memL", memL);
			if(pu!=null){
				session.setAttribute("puS", pu);
				session.setAttribute("joinOrLeave", "LeaveServlet");
				session.setAttribute("JorL","退会");
			}else{
				request.setAttribute("msg", "不参加");
				session.setAttribute("joinOrLeave", "join.jsp");
				session.setAttribute("JorL","参加");
			}
		}else{
			sendUrl="jspIdTest.jsp";
			request.setAttribute("errMsg", "該当IDのプロジェクトが見つかりません");
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sendUrl="";
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("puS");
		User u1=(User)session.getAttribute("userS");
		int uid=u1.getId();
		
		request.setCharacterEncoding("utf8");
		int id=(int)request.getAttribute("id");
		System.out.println(id);
		ProjectDAO dao=new ProjectDAO();
		Project prj=dao.select2(id);
		Prj_userDAO dao2=new Prj_userDAO();
		Prj_user pu=dao2.select(id,uid);
		if(prj!=null){
			BbsDAO dao3= new BbsDAO();
			ArrayList<Bbs> bbsL=new ArrayList<Bbs>();
			bbsL=dao3.listH(id);
			EventDAO dao4= new EventDAO();
			ArrayList<Event> eventL=new ArrayList<Event>();
			eventL=dao4.listH(id);
			sendUrl="prj_home.jsp";
			session.setAttribute("prjS", prj);
			request.setAttribute("bbsL", bbsL);
			request.setAttribute("eventL", eventL);
			if(pu!=null){
				session.setAttribute("puS", pu);
				session.setAttribute("joinOrLeave", "LeaveServlet");
				session.setAttribute("JorL","退会");
			}else{
				request.setAttribute("msg", "不参加");
				session.setAttribute("joinOrLeave", "join.jsp");
				session.setAttribute("JorL","参加");
			}
		}else{
			sendUrl="jspIdTest.jsp";
			request.setAttribute("errMsg", "該当IDのプロジェクトが見つかりません");
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
