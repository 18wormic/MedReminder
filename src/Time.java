import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Time implements Serializable
{
	public Time(int hour, DayOfWeek day)
	{
		timeOfReminder = LocalTime.of(hour, 0);
		dayOfReminder = day;
	}
	
	public DayOfWeek getDay()
	{
		return dayOfReminder;
	}
	
	public LocalTime getTime()
	{
		return timeOfReminder;
	}
	
	@Override
	public String toString()
	{
		return timeOfReminder.toString() + ", " + dayOfReminder.toString();
	}
	
	private DayOfWeek dayOfReminder;
	private LocalTime timeOfReminder;
}
