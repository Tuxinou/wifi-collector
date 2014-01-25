package info.wifi;

public class AccessPoint implements Comparable
{
	private Location location;
	private String bssid;
	private int signal;
	
	public AccessPoint(String bss, int sig, Location loc)
	{
		location = loc;
		bssid = bss;
		signal = sig;
	}
	
	public String getBssid()
    {
    	return bssid;
    }
    
    public int getSignal()
    {
    	return signal;
    }
    
    public Location getLocation()
    {
    	return location;
    }
    
    public void setLocation(Location loc)
    {
    	location = loc;
    }

    //returns 1 if the signal is stronger
	public int compareTo(Object a) 
	{
		if(getSignal()  >= ((AccessPoint) a).getSignal())
		{
			return 0;
		}
		
	return 1;
	}
	
	//returns distance from access point in km
	public double distanceFrom()
	{
		return .004 * signal;
	}


	}
    
    



