package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Login.User;

public class adminDao {
	
	String query="select *from admin where name=? and password=?";


	public boolean check(String regId,String password) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hackathon1", "root","test");
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1, "admin");
		st.setString(2, "123456");
		ResultSet rs=st.executeQuery();
		
	
//		
//		while(rs.next())
//		System.out.println(rs.getString(1));
		
		
		
		if(rs.next())
		{
		
			
			
			return true;
		}
		
		return false;
		
	}

}
