import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Time
{
	private final int time;
	private final boolean am;
	
	public Time(int aTime, boolean aAm)
	{
		time = aTime;
		am = aAm;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public boolean getAm()
	{
		return am;
	}
	
	public StringProperty timeProperty()
	{
		return new SimpleStringProperty(time + ":00");
	}
	
	public StringProperty amProperty()
	{
		if (am)
		{
			return new SimpleStringProperty("AM");
		}
		else
		{
			return new SimpleStringProperty("PM");
		}
	}
}
