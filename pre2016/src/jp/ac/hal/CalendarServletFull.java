package jp.ac.hal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServletFull")
public class CalendarServletFull extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServletFull() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("userS");
		if(u==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		Project p=(Project)session.getAttribute("prjS");
		int pid=p.getId();
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
		ArrayList<CalendarXF> clndr=new ArrayList<CalendarXF>();
		CalenderF C=new CalenderF();
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
