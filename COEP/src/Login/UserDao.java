package Login;

/*public class UserDao {
	
	int id,marks;
	String name	,address,cont_no,email_id,dob,par_cont_no,branch ,password;

}*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

//import registration.UserRegDao;;

public class UserDao {
	String query="select *from firstYear where Id=? and password=?";
	String url="jdbc:mysql://localhost:3306/COEP";
	String username="root";
	String pass="test";
	User user=new User();
	public User check(String regId,String password) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, username, pass);
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,regId);
		st.setString(2,password );
		ResultSet rs=st.executeQuery();
		
	
//		
//		while(rs.next())
//		System.out.println(rs.getString(1));
		
		
		
		if(rs.next())
		{
		
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setAddress(rs.getString(3));
			user.setCont_no(rs.getString(4));
			user.setMarks(rs.getInt(5));
			user.setEmail_id(rs.getString(6));
			user.setDob(rs.getString(7));
			user.setPar_cont_no(rs.getString(8));
			user.setBranch(rs.getString(9));
			user.setPassword(rs.getString(10));
			
			return user;
		}
		
		return null;
		
	}

}

