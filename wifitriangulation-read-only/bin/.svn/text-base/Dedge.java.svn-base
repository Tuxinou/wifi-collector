///////////////////////////////////////////////////////////////////////////////
/// Dedge.java
/// Author : Alper UNGOR
/// Last Updated: Wed Apr 30 15:35
///////////////////////////////////////////////////////////////////////////////

import java.applet.Applet;

public class Dedge extends Applet {

  // index to the origin point of the directed edge
  public int org;

  // Index of the two left neighbour directed edges of this edge 
  public DEindex onext, dnext;

  ///////////////////////////////////////////////////////////////////////
  // Dedge: constructor for Dedge class. It requires a point index o,
  //        and two left neighbour directed edges.
  ///////////////////////////////////////////////////////////////////////
  public  Dedge(int o, DEindex on, DEindex dn){
    org = o;
    onext = on;
    dnext = dn;
  }
	
  ///////////////////////////////////////////////////////////////////////
  // Dedge: another constructor for Dedge class. It does not require
  //        the neighbour edges. It creates two dummy neighbour edges
  ///////////////////////////////////////////////////////////////////////
  public  Dedge(int o){
    org = o;
    onext = new DEindex(0,0);
    dnext = new DEindex(0,0);
  }

  ///////////////////////////////////////////////////////////////////////
  // print: prints this directed edge on the standard output
  ///////////////////////////////////////////////////////////////////////
  public void print(){
    System.out.print(org);
    System.out.print("    ");
    onext.print();
    System.out.print("    ");
    dnext.print();
    System.out.println();
    
  }

}

