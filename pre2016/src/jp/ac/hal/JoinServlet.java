package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean isErr=false;
		HttpSession session=request.getSession(false);
		
		request.setCharacterEncoding("utf8");
		String nameflg0=request.getParameter("nameflg");
		String adrsflg0=request.getParameter("adrsflg");
		String hn=request.getParameter("hn");
		String mail=request.getParameter("mail");
		int type=0;
		
		System.out.println(hn);
		
		User u=(User)session.getAttribute("userS");
		int userid=u.getId();
		Project p=(Project)session.getAttribute("prjS");
		int prjid=p.getId();
				
		String sendUrl="";
		request.setCharacterEncoding("utf-8");
		
		
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
			sendUrl="join.jsp";
		}
		else{
			Prj_userDAO dao=new Prj_userDAO();
			int id=prjid;
			int uid=userid;
			Prj_user pu=dao.select_rj(id,uid);
			
			if(pu!=null){
				System.out.println("再参加");
				Prj_userDAO dao2=new Prj_userDAO();
				int cnt=dao2.rejoin(prjid, userid, nameflg, adrsflg, hn, mail, type);
				if(cnt<=0){
					sendUrl="join.jsp";
				}else{
					sendUrl="success_join.jsp";
				}
			}else{
				System.out.println("新規参加");
				Prj_userDAO dao2=new Prj_userDAO();
				int cnt=dao2.join(prjid, userid, nameflg, adrsflg, hn, mail, type);
				if(cnt<=0){
					sendUrl="join.jsp";
				}else{
					sendUrl="success_join.jsp";
				}
			}
		}
		
		request.getRequestDispatcher(sendUrl).forward(request, response);
		
		
	}

}
