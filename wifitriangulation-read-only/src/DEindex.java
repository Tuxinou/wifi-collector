///////////////////////////////////////////////////////////////////////////////
/// DEindex.java
/// Author : Alper UNGOR
/// Last Updated: Wed Apr 30 15:35
///////////////////////////////////////////////////////////////////////////////

import java.applet.Applet;

public class DEindex extends Applet {

  public int lineNo;    // index of the line
  public int direction; // 0 or 1

  ///////////////////////////////////////////////////////////////////////
  // DEindex: is the constructor. It requires line number and direction
  ///////////////////////////////////////////////////////////////////////
  public  DEindex(int line, int dir){
    lineNo = line;
    direction = dir;
  }
	
  ///////////////////////////////////////////////////////////////////////
  // DEindex: Using this constructor it is possible to construct dummy
  //          directed edges. It is necessary to define the outside 
  //          neighbours of the edges that belong to convex hull.
  ///////////////////////////////////////////////////////////////////////
  public  DEindex(){
    lineNo = 0;
    direction = 0;
  }

  ///////////////////////////////////////////////////////////////////////
  // print: outputs the attributes of this directed edge index 
  ///////////////////////////////////////////////////////////////////////
  public void print() {
    System.out.print(lineNo);
    System.out.print(" ");
    System.out.print(direction);
  }
}

