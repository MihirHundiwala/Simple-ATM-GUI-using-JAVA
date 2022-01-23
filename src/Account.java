import java.sql.ResultSet;
import java.sql.Statement;

public class Account {
		
	protected static double balance_enquiry(int id) 
		{
			double bal=0;
			try 
			{
				Statement getname=Database.conn.createStatement();
				ResultSet name=getname.executeQuery("SELECT account_balance from accounts WHERE account_id='"+id+"'");
				if(name.next())
				{
					bal=Double.valueOf(name.getString(1));
				}
				System.out.println("=>"+bal);
				return bal;
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
			return bal;
		}
		
	
		protected static boolean withdraw(int id,double amount) 
		{
			double bal=0;
			try 
			{
				Statement getname=Database.conn.createStatement();
				ResultSet name=getname.executeQuery("SELECT account_balance from accounts WHERE account_id='"+id+"'");
				if(name.next())
				{
					bal=Double.valueOf(name.getString(1));
					// SUBTRACT FOR WITHDRAWAL
					bal=bal-amount;
					if(bal>=0)
					{
						Statement withdraw=Database.conn.createStatement();
						withdraw.executeUpdate("UPDATE accounts SET account_balance='"+bal+"' WHERE account_id='"+id+"'");
						History.newtransaction(id,"Withdrawal",amount);
						return true;
					}
					else
						return false;
				}
				System.out.println("=>"+bal);
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
			return false;
		}
		
		
		protected static void deposit(int id,double amount) 
		{
			double bal=0;
			try 
			{
				Statement getname=Database.conn.createStatement();
				ResultSet name=getname.executeQuery("SELECT account_balance from accounts WHERE account_id='"+id+"'");
				if(name.next())
				{
					bal=Double.valueOf(name.getString(1));
					//ADD FOR DEPOSIT
					bal=bal+amount;
					Statement withdraw=Database.conn.createStatement();
					withdraw.executeUpdate("UPDATE accounts SET account_balance='"+bal+"' WHERE account_id='"+id+"'");
					History.newtransaction(id,"Deposit",amount);
				}
				System.out.println("=>"+bal);
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
}
