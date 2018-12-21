package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import General.QueryLayer;
import Social.SendEmail;

/**
 * Servlet implementation class actualServlet
 */
@WebServlet("/actualServlet")
public class actualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminSort as=new AdminSort();
		QueryLayer q=new QueryLayer();
		boolean bool=false;
		if(request.getParameter("update")!=null) {
			try {
				as.updateTables();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("allocate1")!=null) {
			System.out.println("Allocate1");
			try {
				q.allocateRoom();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if(request.getParameter("allocate2")!=null) {
			
			System.out.println("Allocate2");
			
			try {
				q.allocateSecondTime();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(request.getParameter("allocate3")!=null) {
	
			System.out.println("Allocate3");
			try {
				q.allocateThirdTime();
				bool=true;
				HttpSession session=request.getSession();
				session.setAttribute("flag", 1);
				 SendEmail e = new SendEmail();
				 QueryLayer q4 = new QueryLayer();
				 String[] add = q4.getSelectedEmails();
				 boolean state = e.sendEmail("Your seat has been confirmed.\nFor Further process please contact college",add);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
	}

}
