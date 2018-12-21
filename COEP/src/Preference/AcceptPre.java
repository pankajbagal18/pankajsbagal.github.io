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
 * Servlet implementation class AcceptPre
 */
@WebServlet("/AcceptPre")
public class AcceptPre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptPre() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id;
		if(request.getParameter("ckb")!=null)
		{
		String[] prefer=request.getParameterValues("ckb");
		long [] pref=new long[3];
			for(int i=0;i<prefer.length;i++)
			{
				pref[i]=Long.parseLong(prefer[i]);
			    System.out.println(pref[i]);
				
			}
		
		User user=new User();
		
		HttpSession session=request.getSession();
		user=(User)session.getAttribute("user");
		
		System.out.println(user.getId());
		id=user.getId();
		QueryLayer q=new QueryLayer();
		try {
			q.insertPreferences(id, pref);
			RequestDispatcher rd=request.getRequestDispatcher("table.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}

}
