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
 * Servlet implementation class ListProjectServlet
 */
@WebServlet("/ListProjectServlet")
public class ListProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses=request.getSession();
		User u1=(User)ses.getAttribute("userS");
		
		if(u1==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		boolean flg=false;
		ArrayList<Project> list = new ArrayList<Project>();
		ProjectDAO dao2=new ProjectDAO();
		
		int total=dao2.count();
		int pageper=10; //1ページあたりの表示件数
		int page=total/pageper; //ページ数
		if(total%pageper!=0){
			page++; 
		}
		int pagenum=Integer.parseInt(request.getParameter("page"))-1; //ページ番号
		int offset=pageper*pagenum; //検索位置オフセット
		System.out.println("総ページ数は"+page);
		int limit=10;
		
		list=dao2.listPrj2(offset, limit);
		request.setAttribute("prjL", list);
		request.setAttribute("page", page);
		request.setAttribute("flg", flg);
		
		request.getRequestDispatcher("projects.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
