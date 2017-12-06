import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medication implements Serializable
{
	public Medication(String name)
	{
		this.name = name;
		reminders = new ArrayList<Time>();
	}
	
	public void setTime(Time aTime)
	{
		remTime = aTime;
	}
	
	public Time getTime()
	{
		return remTime;
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
	
	public void addReminder(int hour, DayOfWeek day)
	{
		Time temp = new Time(hour, day);
		
//		if (reminders.isEmpty())
//		{
			reminders.add(temp);
//		}
//		else
//		{
//			for (int i = 0; i < reminders.size(); i++)
//			{
//				if (temp.getDay().getValue() < reminders.get(i).getDay().getValue())
//				{
//					continue;
//				}
//				else if (temp.getDay().getValue() > reminders.get(i).getDay().getValue()) 
//				{
//					reminders.add(i, temp);
//				}
//				else
//				{
//					if (temp.getTime().isAfter(reminders.get(i).getTime()))
//					{
//						reminders.add(i, temp);
//					}
//				}
//			}
//		}
	}
	
	public ArrayList<Time> getReminders()
	{
		return reminders;
	}
	
	public StringProperty nameProperty()
	{
		return new SimpleStringProperty(name);
	}
	
	public StringProperty descProperty()
	{
		return new SimpleStringProperty(desc);
	}
	
	public StringProperty timeProperty()
	{
		return new SimpleStringProperty(remTime.toString());
	}
	
	private String name;
	private String desc;
	private Time remTime;
	private ArrayList<Time> reminders;
}
