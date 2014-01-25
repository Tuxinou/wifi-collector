package info.wifi;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Map extends JPanel implements MouseMotionListener
{
	private static final long serialVersionUID = 3897556856087230354L;
	
	String locationString = null;

	private Image img = null;
	
	private int imageHeight;
	private int imageWidth;
	
	private int lastPointerX = -1;
	private int lastPointerY = -1;
	
	private int translationX;
	private int translationY;
	
	public Map(Location loc)
	{
		img = new ImageIcon("newmap.png").getImage();
		
		imageHeight = img.getHeight(this);
		imageWidth = img.getWidth(this);
		
		System.out.println(imageHeight + " : " + imageWidth);
		
		this.addMouseMotionListener(this);
		
		this.setVisible(true);
		this.setBounds(0, 50, 800, 550);
		this.setOpaque(true);
		this.setBackground(Color.black);
		
		repaint();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(img,-translationX,-translationY,null);
	}
	
	public void mousePressed(int x, int y)
	{
		
		int a = lastPointerX - x;
		int b = lastPointerY - y;
		
		if(a > 40 || a < -40 || b > 40 || b < -40)
		{
			lastPointerX = x;
			lastPointerY = y;
			
			a = lastPointerX - x;
			b = lastPointerY - y;
		}
		
		translationX += a;
		translationY += b;
		
		if(translationX < 0)
		{
			translationX = 0;
		}
		else if(translationX + getWidth() > imageWidth)
		{
			translationX = imageWidth - getWidth();
		}
		
		if(translationY < 0)
		{
			translationY = 0;
		}
		else if(translationY + getHeight() > imageHeight)
		{
			translationY = imageHeight- getHeight();
		}

		lastPointerX = x;
		lastPointerY = y;
		
		System.out.println("X: "+translationX+" Y: "+translationY);
		
		repaint();
	}
	
	public int getX()
	{
		return -translationX;
	}
	
	public int getY()
	{
		return -translationY;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	public void mouseDragged(MouseEvent arg0) 
	{
		mousePressed(arg0.getXOnScreen(), arg0.getYOnScreen());
	}

	public void mouseMoved(MouseEvent arg0) {}
}
