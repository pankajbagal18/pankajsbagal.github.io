package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	PrintWriter out=response.getWriter();
	
	String regId=request.getParameter("regId");
	String password=request.getParameter("password");
	
	
	UserDao dao=new UserDao();
	User user=new User();
	try {
		user=dao.check(regId, password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(user!=null)
	{
		HttpSession session=request.getSession();
		session.setAttribute("user",user);
	//	out.print(user.getName());
		RequestDispatcher rd=request.getRequestDispatcher("profile.jsp");
	    rd.forward(request, response);
	}
	else
	{
		
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
	    rd.forward(request, response);
	}

	
	}

}
