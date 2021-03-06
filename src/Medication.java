import java.time.DayOfWeek;
import java.util.ArrayList;

public class Medication
{
	public Medication(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String desc)
	{
		this.desc = desc;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addReminder(int hour, int minute, DayOfWeek day)
	{
		Time temp = new Time(hour, minute, day);
		
		if (reminders.isEmpty())
		{
			reminders.add(temp);
		}
		else
		{
			for (int i = 0; i < reminders.size(); i++)
			{
				if (temp.getDay().getValue() < reminders.get(i).getDay().getValue())
				{
					continue;
				}
				else if (temp.getDay().getValue() > reminders.get(i).getDay().getValue()) 
				{
					reminders.add(i, temp);
				}
				else
				{
					if (temp.getTime().isAfter(reminders.get(i).getTime()))
					{
						reminders.add(i, temp);
					}
				}
			}
		}
	}
	
	public ArrayList<Time> getReminders()
	{
		return reminders;
	}
	
	private String name;
	private String desc;
	private ArrayList<Time> reminders;
}
