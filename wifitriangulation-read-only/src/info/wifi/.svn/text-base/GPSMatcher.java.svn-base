package info.wifi;

import java.util.ArrayList;
import java.util.Collections;


public class GPSMatcher 
{
	private ArrayList<AccessPoint> currentAP;
    private ArrayList<AccessPoint> masterAP;
    
	public GPSMatcher(ArrayList<AccessPoint> available, ArrayList<AccessPoint> masterList)
	{
		currentAP = available;
		masterAP = masterList;
	}

//takes an access point from available and tries to match it to master list
//it then fills in the location data from the master list into the current list
public void fillInLocations()
{
	for (AccessPoint a: currentAP)
	{
		for (AccessPoint b: masterAP)
		{
			if (a.getBssid().equals(b.getBssid()))
			{
				a.setLocation(b.getLocation());
			}
		}
	}
}

public void removeNullLocations()
{
	fillInLocations();
	for (int i = currentAP.size()-1; i >=0; i--)
	{
		if (currentAP.get(i).getBssid()==null)
		{
			currentAP.remove(i);
		}
	}
}

public Location determineLocation()
{
	sortBySignal();
	Triangle t = new Triangle (currentAP.get(0), currentAP.get(1),currentAP.get(2));
	return t.currentLocation();
}

public void sortBySignal()
{
	removeNullLocations();
	Collections.sort(currentAP);
}
	
}
