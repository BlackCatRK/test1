package jp.ac.hal;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateEventServlet
 */
@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
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
		String name=request.getParameter("name");
		String desc=request.getParameter("desc");
		String yyyy1=request.getParameter("yyyy1");
		String mm1=request.getParameter("MM1");
		String dd1=request.getParameter("dd1");
		String hh1=request.getParameter("hh1");
		String m1=request.getParameter("mm1");
		String yyyy2=request.getParameter("yyyy2");
		String mm2=request.getParameter("MM2");
		String dd2=request.getParameter("dd2");
		String hh2=request.getParameter("hh2");
		String m2=request.getParameter("mm2");
		
		String startdate=yyyy1+"-"+mm1+"-"+dd1+" "+hh1+":"+m1+":"+"00";
		String limitdate=yyyy2+"-"+mm2+"-"+dd2+" "+hh2+":"+m2+":"+"00";
		
		if(name==null||name.equals("")){
			request.setAttribute("titleErr", "タイトルを入力してください");
			isErr=true;
		}else if(name.length()>50){
			request.setAttribute("titleErr", "タイトルは50文字以内で入力してください");
			name="";
			isErr=true;
		}
		if(desc==null||desc.equals("")){
			request.setAttribute("descErr", "説明を入力してください");
			isErr=true;
		}else if(desc.length()>1000){
			request.setAttribute("descErr", "説明は1000文字以内で入力してください");
			desc="";
			isErr=true;
		}
		
		if(isErr==true){
			request.setAttribute("title", name);
			request.setAttribute("desc", desc);
			sendUrl="createevent.jsp";
		}else{
			EventDAO dao=new EventDAO();
			int cnt=dao.create(pid, name, desc, startdate, limitdate);
			if(cnt<=0){
				request.setAttribute("title", name);
				request.setAttribute("desc", desc);
				sendUrl="createevent.jsp";
			}else{
				sendUrl="success_event.jsp";
			}
		}
		request.getRequestDispatcher(sendUrl).forward(request,response);
	}

}
