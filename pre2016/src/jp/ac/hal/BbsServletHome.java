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
@WebServlet("/BbsServletHome")
public class BbsServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsServletHome() {
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
		
		int uid=u.getId();
		
		int pid=Integer.parseInt(request.getParameter("pid"));
		ProjectDAO dao5=new ProjectDAO();
		Project prj=dao5.select2(pid);
		System.out.println("プロジェクト接続");
		
		Prj_userDAO dao6=new Prj_userDAO();
		Prj_user pu=dao6.select(pid,uid);
		System.out.println("プロジェクトユーザー承認");
		
		String bbsid=request.getParameter("bid");
		int bid=Integer.parseInt(bbsid);
		
		BbsDAO dao=new BbsDAO();
		Bbs bbs=dao.select(pid,bid);
		
		BbsWriteDAO dao2=new BbsWriteDAO();
		int total=dao2.count(bid);
		int pageper=10; //1ページあたりの表示件数
		int page=total/pageper; //ページ数
		if(total%pageper!=0){
			page++; 
		}
		int pagenum=0; //ページ番号
		int offset=pageper*pagenum; //検索位置オフセット
		System.out.println("総ページ数は"+page);
		
		ArrayList<BbsWrite> bbsw=new ArrayList<BbsWrite>();
		bbsw=dao2.listW(pid,bid,offset,pageper);
		
		request.setAttribute("page", page);
		request.setAttribute("bbs", bbs);
		request.setAttribute("bbswL", bbsw);
		request.setAttribute("bid", bid);
		session.setAttribute("prjS", prj);
		session.setAttribute("puS", pu);
		
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
