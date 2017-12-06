import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
	
	public boolean checkRem()
	{
		LocalDateTime now = LocalDateTime.now();
		
		return (now.getDayOfWeek().equals(dayOfReminder) && now.getHour() == timeOfReminder.getHour());
		
	}
	
	private DayOfWeek dayOfReminder;
	private LocalTime timeOfReminder;
}
