import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;

public class History {
	
	public static String show(int id)
	{
		String history=null;
		try 
		{	
			// FIND PREVIOUS TRANSACTION HISTORY AND DISPLAY
			Statement retrieve =Database.conn.createStatement();
			ResultSet lines =retrieve.executeQuery("SELECT history FROM accounts WHERE account_id='"+id+"'");
			if(lines.next())
			{
				history =lines.getString(1);
			}
			return history;
		} 
		catch (Exception e) 
		{
			return "error";
		}
	}
	
	public static void newtransaction(int id,String operation,double amount)
	{	
		// APPEND NEW TRANSACTION IN HISTORY
		String trans="\n"+LocalDateTime.now()+" :\n  "+operation+" Amount: "+amount+"\n";
		try 
		{
			Statement new_trans =Database.conn.createStatement();
			String previous_history=show(id);
			String new_history=previous_history+trans;
			new_trans.executeUpdate("UPDATE accounts SET history='"+new_history+"' WHERE account_id='"+id+"'");
			System.out.println(new_history);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
}
