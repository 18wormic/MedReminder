import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
	public User(String username, String password)
	{
		user = username;
		pass = password;
		meds = new ArrayList<Medication>();
		
	}
	
	public void addMed(Object med)
	{
		
	}
	
	public ArrayList<Medication> getMed()
	{
		return meds;
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
	
	private ArrayList<Medication> meds;
	private String user;
	private String pass;
}
