import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Time implements Serializable
{
	public Time(int hour, int minute, DayOfWeek day)
	{
		timeOfReminder = LocalTime.of(hour, minute);
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
	
	private DayOfWeek dayOfReminder;
	private LocalTime timeOfReminder;
}
