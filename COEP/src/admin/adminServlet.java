package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.User;
import Login.UserDao;

/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/adminServlet")
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String regId=request.getParameter("name");
		String password=request.getParameter("password");
		
		
		adminDao dao=new adminDao();
		//User user=new User();
		boolean b=false;
		try {
			b=dao.check(regId, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b==true)
		{
			//HttpSession session=request.getSession();
			//session.setAttribute("user",user);
		//	out.print(user.getName());
			RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
		    rd.forward(request, response);
		}
		else
		{
			
			RequestDispatcher rd=request.getRequestDispatcher("adminLogin.jsp");
		    rd.forward(request, response);
		}

		
	}

}
