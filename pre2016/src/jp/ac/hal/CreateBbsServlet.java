package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateBbsServlet
 */
@WebServlet("/CreateBbsServlet")
public class CreateBbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBbsServlet() {
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
		boolean isErr=false;
		request.setCharacterEncoding("utf8");
		String sendUrl="success_bbs";
		HttpSession session=request.getSession(false);
		Project prj=(Project)session.getAttribute("prjS");
		int pid=prj.getId();
		String title=request.getParameter("title");
		String desc=request.getParameter("desc");
		
		if(title==null||title.equals("")){
			request.setAttribute("titleErr", "タイトルを入力してください");
			isErr=true;
		}else if(title.length()>50){
			request.setAttribute("titleErr", "タイトルは50文字以内で入力してください");
			title="";
			isErr=true;
		}
		if(desc==null||desc.equals("")){
			request.setAttribute("descErr", "説明を入力してください");
			isErr=true;
		}else if(desc.length()>200){
			request.setAttribute("descErr", "説明は200文字以内で入力してください");
			isErr=true;
		}
		if(isErr==true){
			sendUrl="createbbs.jsp";
		}else{
			BbsDAO dao=new BbsDAO();
			int cnt=dao.create(pid, title, desc);
			if(cnt<=0){
				sendUrl="createbbs.jsp";
			}else{
				sendUrl="success_bbs.jsp";
			}
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
