import java.util.ArrayList;

public class User
{
	public User(String username, String password)
	{
		user = username;
		pass = password;
	}
	
	public void addMed(Object med)
	{
		
	}
	
	public ArrayList<Object> getMed()
	{
		return null;
	}
	
	public void setUser(String username)
	{
		user = username;
	}
	
	public void setPass(String password)
	{
		pass = password;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	private String user;
	private String pass;
}
