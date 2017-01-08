package jp.ac.hal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInputServlet
 */
@WebServlet("/UserInputServlet")
public class UserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInputServlet() {
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
		String userid=request.getParameter("userid");
		String pw=request.getParameter("pw");
		String pw2=request.getParameter("pw2");
		String lname=request.getParameter("lname");
		String fname=request.getParameter("fname");
		String hn=request.getParameter("hn");
		String mail=request.getParameter("mail");

		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		
		String sendUrl="";
		//エラー回避
		if(userid==null||userid.equals("")){
			isErr=true;
			request.setAttribute("idErr", "idが入力されていません");
		}else if(userid.length()<5||userid.length()>32){
			isErr=true;
			request.setAttribute("idErr", "idは5文字以上32文字以下で入力してください");
			userid="";
		}else{
			UserDAO check=new UserDAO();
			int flg=check.checkId(userid);
			if(flg==1){
				isErr=true;
				request.setAttribute("idErr", "そのユーザーIDは既に使用されています");
				userid="";
			}
		}
		if(pw==null||pw.equals("")){
			isErr=true;
			request.setAttribute("pwErr", "パスワードが入力されていません");
		}else if(pw.length()<7||pw.length()>32){
			isErr=true;
			request.setAttribute("pwErr", "パスワードは7文字以上32文字以下で入力してください");
		}else if(!pw.equals(pw2)){
			isErr=true;
			request.setAttribute("pwErr", "確認用パスワードと一致しません");
		}
		if(lname==null||lname.equals("")){
			isErr=true;
			request.setAttribute("lnameErr", "姓を入力してください");
		}else if(lname.length()>50){
			isErr=true;
			request.setAttribute("lnameErr", "姓は50文字以内で入力してください");
		}
		if(fname==null||fname.equals("")){
			isErr=true;
			request.setAttribute("fnameErr", "名を入力してください");
		}else if(fname.length()>50){
			isErr=true;
			request.setAttribute("fnameErr", "名は50文字以内で入力してください");
		}
		if(hn==null||hn.equals("")){
			isErr=true;
			request.setAttribute("hnErr", "ニックネームを入力してください");
		}else if(hn.length()>32){
			isErr=true;
			request.setAttribute("hnErr", "ニックネームは32文字以内で入力してください");
			hn="";
		}
		if(mail==null||mail.equals("")){
			isErr=true;
			request.setAttribute("mailErr", "メールアドレスを入力してください");
		}else if(!mail.matches(mailFormat)){
			isErr=true;
			request.setAttribute("mailErr", "メールアドレスの形式が不正です");
			mail="";
		}else if(mail.length()>100){
			isErr=true;
			request.setAttribute("mailErr", "メールアドレスは100文字以内に入力してください");
		}
		
		if(isErr==true){
			sendUrl="signup.jsp";
			request.setAttribute("userid", userid);
			request.setAttribute("lname", lname);
			request.setAttribute("fname", fname);
			request.setAttribute("hn", hn);
			request.setAttribute("mail", mail);
		}else{
			UserDAO dao=new UserDAO();
			int cnt=dao.insert(userid, pw, lname, fname, hn, mail);
			if(cnt<=0){
				sendUrl="signup.jsp";
			}else{
				sendUrl="success_signup.jsp";
			}
		}
		request.getRequestDispatcher(sendUrl).forward(request, response);
	}

}
