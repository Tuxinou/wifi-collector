package info.wifi;
import java.util.ArrayList;

public class Triangle2 
{
private Location l1;
private Location l2;
private Location l3;
private AccessPoint a1;
private AccessPoint a2;
private AccessPoint a3;
private final double tolerance = .1;

public Triangle2(AccessPoint a, AccessPoint b, AccessPoint c)
{
	l1 = a.getLocation();
	l2 = b.getLocation();
	l3 = c.getLocation();
	a = a1;
	b = a2;
	c = a3;
}
//distance formula between two points
public double howClose(Location a, Location b)
{
	double xDiff = a.getLongitude()-b.getLongitude();
	double yDiff = a.getLattitude()-b.getLattitude();
	double result = Math.pow(xDiff, 2)+Math.pow(yDiff, 2);
	return Math.sqrt(result);
	
}

public ArrayList<Location> createList(AccessPoint ap)
{
	ArrayList<Location> loc = new ArrayList();
	double x = ap.getLocation().getLongitude();
	double y = ap.getLocation().getLattitude();
	for (int i = 0; i < 360; i+= .5)
	{
		double angle = Math.toRadians(i);
		double xcord = Math.sin(angle)*ap.getSignal();
		double ycord = Math.cos(angle)*ap.getSignal();
		loc.add(new Location(xcord + x,ycord + y));
	}
	return loc;
}

public ArrayList<Location> twoCircles(AccessPoint x, AccessPoint y)
{
ArrayList<Location> result = new ArrayList();
ArrayList<Location> locargs1 = createList(x);
ArrayList<Location> locargs2 = createList(y);
for (int deg1 = 0; deg1 < 360; deg1++)
{
	for (int deg2 = 0; deg2 < 360; deg2++)
	{
		if (howClose(locargs1.get(deg1),locargs2.get(deg2))<=tolerance)
		{
			result.add(locargs1.get(deg1));
			
		}
	}
}
return result;
}

public Location thirdCircle(ArrayList<Location> locs)
{
Location closest = new Location(-9999,-9999);
double closestdistance=1000000;
for (int i = 0; i < locs.size(); i++)
{
	if(howClose(l3,locs.get(i))<closestdistance)
	{
		closest = locs.get(i);
		closestdistance = (howClose(l3,locs.get(i)));
	}
}

return closest;
}

public static Location findLocation(AccessPoint x,AccessPoint y, AccessPoint z)
{
Triangle2 tri = new Triangle2(x,y,z);
ArrayList<Location> possibleLocs = tri.twoCircles(x,y);
Location result = tri.thirdCircle(possibleLocs);
return result;
}
}
