import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class User implements Serializable
{
	public User(String username, String password)
	{
		user = username;
		pass = password;
		meds = new ArrayList<Medication>();

	}

	public void addMed(Medication med)
	{
		meds.add(med);
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

	public Time getNearestTime()
	{
		if (meds.size() != 0)
		{
			return meds.get(0).getTime();
		}
		return null;
	}

	public void removeNearest()
	{
		if (meds.size() != 0)
		{
			meds.get(0).removeNearest();
			sortMeds();
		}
	}

	private void sortMeds()
    {
        Time placeholderTime = new Time(1000, DayOfWeek.SUNDAY);
        int index = 0;
        Medication placeholderMed;
        
        
        if (meds.size() != 0)
        {
            for (int count = 0; count < meds.size(); count++)
            {
                if (placeholderTime.isAfter(meds.get(count).returnNearestReminder()))
                {
                    index = count;
                    placeholderMed = meds.get(count);
                }
            }
            
            placeholderMed = meds.get(0);
            meds.set(0, meds.get(index));
            meds.add(placeholderMed);
        }
}

	private ArrayList<Medication> meds;
	private String user;
	private String pass;
}
