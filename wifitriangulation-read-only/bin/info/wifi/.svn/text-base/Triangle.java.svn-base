package info.wifi;

public class Triangle 
{
	AccessPoint aa;
	AccessPoint bb;
	AccessPoint cc;
	
	double a;
	double b;
	double c;
	
	public Triangle(AccessPoint ap1, AccessPoint ap2, AccessPoint ap3)
	{
		aa = ap1;
		bb = ap2;
		cc = ap3;
		
		this.a = this.distanceAB();
		this.c = this.distanceAC();
		this.b = this.distanceBC();
	}
	
	/**
	 * Gets the a
	 * @return
	 */
	
	//
	public double getAngleARad()
	{
		double top = (Math.pow(this.a, 2) - Math.pow(this.b, 2) - Math.pow(this.c, 2));
		double bottom = 2*b*c;
		double acos = Math.acos(top/bottom);
		
		return acos;
	}
	
	public double getAngleBRad()
	{
		double top = (Math.pow(this.b, 2) - Math.pow(this.a, 2) - Math.pow(this.c, 2));
		double bottom = 2*a*c;
		double acos = Math.acos(top/bottom);
		
		return acos;
	}
	
	public double getAngleCRad()
	{
		double top = (Math.pow(this.c, 2) - Math.pow(this.a, 2) - Math.pow(this.b, 2));
		double bottom = 2*a*b;
		double acos = Math.acos(top/bottom);
		
		return acos;
	}
	
	public double getAngleADeg()
	{
		double top = (Math.pow(this.a, 2) - Math.pow(this.b, 2) - Math.pow(this.c, 2));
		double bottom = 2*b*c;
		double acos = Math.acos(top/bottom);
		
		return (180*acos)/Math.PI;
	}
	
	public double getAngleBDeg()
	{
		double top = (Math.pow(this.b, 2) - Math.pow(this.a, 2) - Math.pow(this.c, 2));
		double bottom = 2*a*c;
		double acos = Math.acos(top/bottom);
		
		return (180*acos)/Math.PI;
	}
	
	public double getAngleCDeg()
	{
		double top = (Math.pow(this.c, 2) - Math.pow(this.a, 2) - Math.pow(this.b, 2));
		double bottom = 2*a*b;
		double acos = Math.acos(top/bottom);
		
		return (180*acos)/Math.PI;
	}
	
	public double getPerimeterLength()
	{
		return this.a + this.c + this.b;
	}
	
	public double distanceAB()
	{
		int R = 6371; // km
		double dLat = Math.toRadians((bb.getLocation().getLattitude()-aa.getLocation().getLattitude()));
		double dLon = Math.toRadians((bb.getLocation().getLongitude()-aa.getLocation().getLongitude())); 
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(aa.getLocation().getLattitude())) * Math.cos(Math.toRadians(bb.getLocation().getLattitude())) * Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		
		return d;
	}
	
	public double distanceAC()
	{
		int R = 6371; // km
		double dLat = Math.toRadians((cc.getLocation().getLattitude()-aa.getLocation().getLattitude()));
		double dLon = Math.toRadians((cc.getLocation().getLongitude()-aa.getLocation().getLongitude())); 
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(aa.getLocation().getLattitude())) * Math.cos(Math.toRadians(cc.getLocation().getLattitude())) * Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		
		return d;
	}
	
	public double distanceBC()
	{
		int R = 6371; // km
		double dLat = Math.toRadians((bb.getLocation().getLattitude()-cc.getLocation().getLattitude()));
		double dLon = Math.toRadians((bb.getLocation().getLongitude()-cc.getLocation().getLongitude())); 
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(cc.getLocation().getLattitude())) * Math.cos(Math.toRadians(bb.getLocation().getLattitude())) * Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		
		return d;
	}
	
	public double kmToMi(double km)
	{
		 return km * 0.621371192;
	}
	
	public double signalToDistance(int signal)
	{
		return signal * 0.004;
	}
	
	public Location currentLocation()
	{
		double distanceA = new Double(aa.distanceFrom());
		double distanceB = new Double(bb.distanceFrom());
		double A = new Double(9999);
		double B = new Double(9999);
		
	}
	
	
}
