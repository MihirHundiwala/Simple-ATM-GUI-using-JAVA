import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
	
	public static Connection getconnection()
	{
		try
		{
			//String driver="com.mysql.jdbc.Driver";
			// LOCAL SERVER USING XAMPP 
			String url="jdbc:mysql://localhost:3306/bank";
			String username="root";
			String password="";
			//Class.forName(driver);
			
			// OBTAIN CONNECTION
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected.System Online.");
			
			return conn;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
}
