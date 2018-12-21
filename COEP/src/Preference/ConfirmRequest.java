package Preference;

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
import Login.User;

/**
 * Servlet implementation class ConfirmRequest
 */
@WebServlet("/ConfirmRequest")
public class ConfirmRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmRequest() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("ckb")!=null)
		{
		String[] prefer=request.getParameterValues("ckb");
		long  pref;
		
				pref=Long.parseLong(prefer[0]);
				
				System.out.println(pref);
				HttpSession session=request.getSession();
				User user=(User)session.getAttribute("user");
				QueryLayer q=new QueryLayer();
				try {
					q.acceptRequest(user.getId(),pref);
					RequestDispatcher rd=request.getRequestDispatcher(("profile.jsp"));
					rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
  }

}
