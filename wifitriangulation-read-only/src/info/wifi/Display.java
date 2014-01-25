package info.wifi;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display extends JFrame 
{
	private static final long serialVersionUID = -6546935620571766216L;
	
	private Map currentMap = null;
	
	public Display(Map m)
	{
		this.currentMap = m;

		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Wi-Fi Triangulation");
		this.setVisible(true);
		
		currentMap = new Map(null);
		
		this.add(currentMap);
		
		JLabel a = new JLabel("");
		this.add(a);
		a.setBounds(0,0,0,0);
		a.setVisible(true);
	}

	public void newMap(Map m)
	{
		this.currentMap = m;
	}

}
