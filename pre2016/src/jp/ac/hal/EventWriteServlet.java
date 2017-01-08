package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WriteEventServlet
 */
@WebServlet("/EventWriteServlet")
public class EventWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventWriteServlet() {
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
		request.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		int eid=Integer.parseInt(request.getParameter("eid"));
		User userS=(User)session.getAttribute("userS");
		int uid=userS.getId();
		
		String sendUrl;
		
		String join=request.getParameter("join");
		String text=request.getParameter("text");
		
		
		System.out.println(join);
		System.out.println(text);
		
		if(join!=null){
			System.out.println("参加");
			Event_memberDAO dao=new Event_memberDAO();
			int cnt=dao.join(eid, uid);
			if(cnt!=1){
				System.out.println("参加失敗");
			}else{
				System.out.println("参加成功");
			}
		}
		E_writeDAO dao=new E_writeDAO();
		int cnt=dao.write(eid, text, uid);
		if(cnt!=2){
			request.setAttribute("errMsg", "書き込みに失敗しました");
		}else{
			request.setAttribute("msg", "書き込みに成功しました");
		}
		
		if(join!=null){
			request.setAttribute("eid",eid);
			sendUrl="success_join_event.jsp";
		}else{
			sendUrl="EventServlet?eid="+eid+"&page=1";
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
