import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

public class Authenticate {
	
	public boolean authenticate(int account_id,String pin)
	{	
		Connection conn=GetConnection.getconnection();
		ResultSet b;
		try 
		{
			Statement authenticate=conn.createStatement();
			b=authenticate.executeQuery("SELECT * from accounts WHERE account_id='"+account_id+"' AND account_pin='"+pin+"'");
			System.out.print(b);
			if(b.next())
				return true;
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		return false;
	}
}
