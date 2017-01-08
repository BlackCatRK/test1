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
@WebServlet("/BbsWriteServlet")
public class BbsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsWriteServlet() {
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
		System.out.println("書き込み開始");
		request.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		int bid=Integer.parseInt(request.getParameter("bid"));
		User userS=(User)session.getAttribute("userS");
		int uid=userS.getId();
		
		String sendUrl;
		
		String text=request.getParameter("text");
		
		System.out.println(text);
		
		BbsWriteDAO_ dao=new BbsWriteDAO_();
		
		int cnt=dao.write(bid, text, uid);
		if(cnt!=2){
			request.setAttribute("errMsg", "書き込みに失敗しました");
		}else{
			request.setAttribute("msg", "書き込みに成功しました");
		}
		
		request.setAttribute("bid",bid);
		sendUrl="BbsServlet?bid="+bid+"&page=1";
		
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
