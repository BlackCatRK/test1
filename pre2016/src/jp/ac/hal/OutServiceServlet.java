package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OutService
 */
@WebServlet("/OutServiceServlet")
public class OutServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutServiceServlet() {
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
		String sendUrl="";
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pw=request.getParameter("pw");
		String pw2=request.getParameter("pw2");
		
		if(pw==null||pw.equals("")){
			isErr=true;
			request.setAttribute("pwErr", "パスワードが入力されていません");
		}else if(!pw.equals(pw2)){
			isErr=true;
			request.setAttribute("pwErr", "確認用パスワードと一致しません");
		}
		if(isErr==true){
			sendUrl="outSystem.jsp";
			request.setAttribute("errMsg", "パスワード入力エラー、ドスェ");
		}else{
			UserDAO dao=new UserDAO();
			int cnt=dao.delete(userid, pw);
			if(cnt<=0){
				sendUrl="outSystem.jsp";
			}else{
				HttpSession sessionU = request.getSession(true);
				sendUrl="login.jsp";
				sessionU.invalidate();
			} 
		}
		request.getRequestDispatcher(sendUrl).forward(request, response);
	}

}
