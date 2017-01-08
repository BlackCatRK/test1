package jp.ac.hal;

import java.io.IOException;
import java.sql.Timestamp;
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
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sendUrl="event.jsp";
		
		HttpSession session=request.getSession();
		
		User u=(User)session.getAttribute("userS");
		
		if(u==null){
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
		int uid=u.getId();
		
		Project p=(Project)session.getAttribute("prjS");
		int pid=p.getId();
		
		String eventid=request.getParameter("eid");
		int eid=Integer.parseInt(eventid);
		
		EventDAO dao=new EventDAO();
		Event event=dao.select(eid);
		
		//修正ここから
		E_writeDAO dao2=new E_writeDAO();
		int total=dao2.count(eid);
		int pageper=10; //1ページあたりの表示件数
		int page=total/pageper; //ページ数
		if(total%pageper!=0){
			page++; 
		}
		int pagenum=Integer.parseInt(request.getParameter("page"))-1; //ページ番号
		int offset=pageper*pagenum; //検索位置オフセット
		System.out.println("総ページ数は"+page);
		
		ArrayList<E_write> ew=new ArrayList<E_write>();
		ew=dao2.listW(pid,eid,offset,pageper);
		//ここまで
		
		/*
		 * 旧コード
		E_writeDAO dao2=new E_writeDAO();
		ArrayList<E_write> ew=new ArrayList<E_write>();
		ew=dao2.listW(pid,eid);
		*/
		
		Event_memberDAO dao3=new Event_memberDAO();
		ArrayList<Project_user> ml=new ArrayList<Project_user>();
		ml=dao3.listM(eid, pid);
		
		Event_memberDAO dao4=new Event_memberDAO();
		boolean flg=dao4.check(uid, eid);
		
		boolean flg2=false;
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		if(ts.after(event.getLimitdate())){
			flg2=true;
		}
		
		System.out.println(flg2);
		
		request.setAttribute("event", event);
		request.setAttribute("ewL", ew);
		request.setAttribute("mlist", ml);
		request.setAttribute("joinflg", flg);
		request.setAttribute("limitflg", flg2);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
