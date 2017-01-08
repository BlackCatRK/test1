package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProjectCreateServlet
 */
@WebServlet("/ProjectCreateServlet")
public class ProjectCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectCreateServlet() {
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
		
		System.out.println("プロジェクト生成用データ取得開始");
		boolean isErr=false;
		HttpSession session=request.getSession(false);
		
		request.setCharacterEncoding("utf8");
		String name=request.getParameter("name");
		String desc=request.getParameter("desc");
		String eventflg0=request.getParameter("eventflg");
		String bbsflg0=request.getParameter("bbsflg");
		String joinflg0=request.getParameter("joinflg");
		
		User u=(User)session.getAttribute("userS");
		int userid=u.getId();
				
		String sendUrl="";
		
		boolean eventflg=true;
		boolean bbsflg=true;
		boolean joinflg=false;
		
		if(name==null||name.equals("")){
			isErr=true;
			request.setAttribute("nameErr", "プロジェクトの名前を入力してください");
		}else if(name.length()>32){
			isErr=true;
			request.setAttribute("nameErr", "プロジェクトの名前は32文字以内で入力してください");
		}
		if(desc==null||desc.equals("")){
			isErr=true;
			request.setAttribute("descErr", "プロジェクトの説明を入力してください");
		}else if(desc.length()>1000){
			isErr=true;
			request.setAttribute("nameErr", "プロジェクトの説明は1000文字以内で入力してください");
		}
		if(eventflg0.equals("0")){
			eventflg=false;
		}
		if(bbsflg0.equals("0")){
			bbsflg=false;
		}
		if(joinflg0.equals("1")){
			bbsflg=true;
		}
		
		
		System.out.println("プロジェクト管理者のプロファイルを取得");
		
		String nameflg0=request.getParameter("nameflg");
		String adrsflg0=request.getParameter("adrsflg");
		String hn=request.getParameter("hn");
		String mail=request.getParameter("mail");
		int type=1;
		boolean nameflg=true;
		boolean adrsflg=true;
		
		
		
		if(nameflg0.equals("0")){
			nameflg=false;
		}
		if(adrsflg0.equals("0")){
			adrsflg=false;
		}
		if(hn==null||hn.equals("")){
			isErr=true;
			request.setAttribute("hnErr", "ハンドルネームを入力してください（デフォルトは初回登録時のHN。変更する場合は入力）");
		}
		if(mail==null||mail.equals("")){
			isErr=true;
			request.setAttribute("mailErr", "メールアドレスを入力してください（デフォルトは初回登録時のアドレス。変更する場合は入力）");
		}
			
		if(isErr==true){
			sendUrl="createprj.jsp";
		}
		else{
			
			int pid=0;
			ProjectDAO dao=new ProjectDAO();
			pid=dao.insert(name, eventflg, bbsflg, joinflg, desc);
			
			if(pid==0){
				request.setAttribute("name", name);
				request.setAttribute("desc", desc);
				sendUrl="createprj.jsp";
			}else{
				int cnt=0;
				Prj_userDAO dao2=new Prj_userDAO();
				cnt=dao2.join(pid, userid, nameflg, adrsflg, hn, mail, type);
				if(cnt<=0){
					sendUrl="createprj.jsp";
				}else{
					sendUrl="success_join.jsp";
				}
			}
		}
		
		request.getRequestDispatcher(sendUrl).forward(request, response);
	}

}
