package jp.ac.hal;

import java.io.IOException;
//import java.util.Calendar;


import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NorPServlet
 */
@WebServlet("/NorPFServlet")
public class NorPFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NorPFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Calendar calendar = Calendar.getInstance();
		
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("userS");
		if(u==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		Project p=(Project)session.getAttribute("prjS");
		int pid=p.getId();		
		
		int year=Integer.parseInt(request.getParameter("year"));
		int month=Integer.parseInt(request.getParameter("month"))-1;
		int day=0;
		if(month==-1){
	    	month=11;
	    	year=year-1;
	    }else if(month==12){
	    	month=0;
	    	year=year+1;
	    }
		
		CalenderF C=new CalenderF();
		ArrayList<CalendarXF> clndr=new ArrayList<CalendarXF>();
		clndr=C.main(year, month, day, pid);
		
		request.setAttribute("days", clndr);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		
		request.getRequestDispatcher("CalenderFull.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
