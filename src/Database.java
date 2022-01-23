import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	static Connection conn = GetConnection.getconnection();
	
//===================================================================================================================
	
	public static void createaccount(String account_name, int account_id,String account_pin,double balance,String history)
	{
		createtable();															//If table doesn't exist create one
		insertaccount(account_name,account_id,account_pin,balance,history); 	//Insert  info
	}
	
//===============================================================================================================	
	
	public static void insertaccount(String account_name, int account_id,String account_pin, double balance,String history) 
	{
		try 
		{
			Statement insert=conn.createStatement();
			insert.executeUpdate("INSERT INTO accounts(account_name, account_id, account_pin, account_balance,history) "
					+ "VALUES('"+account_name+"','"+account_id+"','"+account_pin+"','"+balance+"','"+history+"')");
			System.out.println("Insert Complete");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}
	
//====================================================================================================================
	
	public static void createtable()
	{
		try 
		{
			Statement create=conn.createStatement();
			create.executeUpdate("CREATE TABLE IF NOT EXISTS accounts"
					+ "(account_name varchar(50),account_id int,"
					+ "account_pin varchar(11),account_balance double,history LONGTEXT, UNIQUE(account_id))");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}	
	}
	
//====================================================================================================================
	
	public static String getnamethroughaccountID(int id)
	{
		String n="ErrorInGettingName";
		try 
		{
			Statement getname=conn.createStatement();
			ResultSet name=getname.executeQuery("SELECT account_name from accounts WHERE account_id='"+id+"'");
			if(name.next())
			{
				n=name.getString(1);
			}
			System.out.println("=>"+n);
			return n;
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return n;
	}
}
