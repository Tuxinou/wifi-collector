///////////////////////////////////////////////////////////////////////////////
/// Triangulation.java
/// Author : Alper UNGOR
/// Last Updated: Wed Apr 30 15:35
///////////////////////////////////////////////////////////////////////////////
import java.applet.Applet;
import java.util.*;
import Edge;

public class Triangulation extends Applet {

  // All edges of the triangulation kept in an array
  Edge EdgeArray[];   

  // capacity of the triangulation is the maximum number of edges it can have
  int capacity;       

  // current number of edges in the triangulation
  int numOfEdges;

  ///////////////////////////////////////////////////////////////////////
  // Triangulation: is the Constructor of the Triangulation class.
  //                Given the capacity it allocates an array of edges.
  ///////////////////////////////////////////////////////////////////////
  public Triangulation(int s){
    EdgeArray = new Edge[s];
    capacity = s;
    numOfEdges = 0;
  }

  ///////////////////////////////////////////////////////////////////////
  // numOfEdges: is an access function. It returns the value of the
  //             data member numOfEdges.
  ///////////////////////////////////////////////////////////////////////
  public int numOfEdges() {
    return numOfEdges;
  }

  ///////////////////////////////////////////////////////////////////////
  // insert: is the first of the two insert functions for the triangulation
  //         It inserts an edge specified by its origin and destination 
  //         point indices into the triangulation.
  ///////////////////////////////////////////////////////////////////////
  public void insert(int orgPointIndex, int destPointIndex){
    EdgeArray[numOfEdges] = new Edge(orgPointIndex, destPointIndex);
    numOfEdges++;
  }

  ///////////////////////////////////////////////////////////////////////
  // onextdirection: returns the direction of the onext of the given edge
  //                 specified by the index i and origin org. 
  ///////////////////////////////////////////////////////////////////////
  public int onextdirection(int org, int i) {
    if (EdgeArray[i].dEdge[0].org == org)
      return 0;
    else if (EdgeArray[i].dEdge[1].org == org)
      return 1;
    else
      System.out.println("Error incorrect edge index");
    return -1;
  }

  ///////////////////////////////////////////////////////////////////////
  // onextdirection: returns the direction of the dnext of the given edge
  //                 specified by the index i and the destination dest. 
  ///////////////////////////////////////////////////////////////////////
  public int dnextdirection(int dest, int i) {
    if (EdgeArray[i].dEdge[0].org == dest)
      return 1;
    else if (EdgeArray[i].dEdge[1].org == dest)
      return 0;
    else
      System.out.println("Error incorrect edge index");
    return -1;
  }

  ///////////////////////////////////////////////////////////////////////
  // insert: is the second of the two insert functions for the triangulation
  //         It inserts an edge specified by its origin and destination 
  //         point indices and its neighbour edges into the triangulation.
  ///////////////////////////////////////////////////////////////////////
  public void insert(int orgPointIndex, int destPointIndex, 
		int onextEdgeIndex, int dnextEdgeIndex) {

    EdgeArray[numOfEdges] = new Edge(orgPointIndex, destPointIndex);

    // Figure out the direction of the neighbour edges
    int onextdir = onextdirection( orgPointIndex, onextEdgeIndex);
    int dnextdir = dnextdirection( destPointIndex, dnextEdgeIndex);


    // Modify the onext dnext of this edge to be neighbour edges
    EdgeArray[numOfEdges].dEdge[0].onext = new DEindex(onextEdgeIndex, onextdir);
    EdgeArray[numOfEdges].dEdge[0].dnext = new DEindex(dnextEdgeIndex, dnextdir);

    // Modify the onext and dnext of the neighbour edges to be this one
    // and the other neighbour edge.
    EdgeArray[onextEdgeIndex].dEdge[(onextdir+1)%2].onext = new DEindex(dnextEdgeIndex, dnextdir);
    EdgeArray[onextEdgeIndex].dEdge[(onextdir+1)%2].dnext = new DEindex(numOfEdges, 1);
    EdgeArray[dnextEdgeIndex].dEdge[(dnextdir+1)%2].onext = new DEindex(numOfEdges, 1);
    EdgeArray[dnextEdgeIndex].dEdge[(dnextdir+1)%2].dnext = new DEindex(onextEdgeIndex, onextdir);
   
    numOfEdges++;
  }

  ///////////////////////////////////////////////////////////////////////
  // start: returns the start of an edge. Origin of the first directed 
  //        edge is called the start of the edge.
  ///////////////////////////////////////////////////////////////////////
  public int start(int i) {
    return EdgeArray[i].dEdge[0].org;
  }

  ///////////////////////////////////////////////////////////////////////
  // end: returns the end of an edge. Origin of the second directed edge 
  //      is called the end of the edge
  ///////////////////////////////////////////////////////////////////////
  public int end(int i) {
    return EdgeArray[i].dEdge[1].org;
  }
  
  ///////////////////////////////////////////////////////////////////////
  // isDiagonal: checks if the ith edge of the triangulation is diagonal.
  ///////////////////////////////////////////////////////////////////////
  public boolean isDiagonal(int i) {
    return EdgeArray[i].isDiagonal();
  }

  ///////////////////////////////////////////////////////////////////////
  // mark: marks the ith edge of the triangulation.
  ///////////////////////////////////////////////////////////////////////
  public void mark(int i) {
    EdgeArray[i].mark();
  }

  ///////////////////////////////////////////////////////////////////////
  // unmark: unmarks the ith edge of the triangulation.
  ///////////////////////////////////////////////////////////////////////
  public void unmark(int i) {
    EdgeArray[i].unmark();
  }

  ///////////////////////////////////////////////////////////////////////
  // isMarked: checks if the ith edge of the triangulation is marked.
  ///////////////////////////////////////////////////////////////////////
  public boolean isMarked(int i) {
    return EdgeArray[i].isMarked();
  }

  ///////////////////////////////////////////////////////////////////////
  // origin: returns the origin of a directed Edge in the triangulation
  ///////////////////////////////////////////////////////////////////////
  public int origin(DEindex ix){
    return (EdgeArray[ix.lineNo].dEdge[ix.direction].org);  
  }

  ///////////////////////////////////////////////////////////////////////
  // rightPointIndex: returns the index of the right vertex of the ith edge.
  ///////////////////////////////////////////////////////////////////////
  public int rightPointIndex(int i) {
    return(origin(EdgeArray[i].dEdge[1].dnext));
  }

  ///////////////////////////////////////////////////////////////////////
  // leftPointIndex: returns the index of the left vertex of the ith edge.
  ///////////////////////////////////////////////////////////////////////
  public int leftPointIndex(int i) {
    return(origin(EdgeArray[i].dEdge[0].dnext));
  }

  ///////////////////////////////////////////////////////////////////////
  // edgeFlip: flips the ith edge in the triangulation 
  ///////////////////////////////////////////////////////////////////////
  public void edgeFlip(int i){

    int r = rightPointIndex(i);
    int s = leftPointIndex(i);

    DEindex selfonext = EdgeArray[i].dEdge[0].onext;
    DEindex selfdnext = EdgeArray[i].dEdge[0].dnext;
    DEindex symmonext = EdgeArray[i].dEdge[1].onext;
    DEindex symmdnext = EdgeArray[i].dEdge[1].dnext;

    EdgeArray[i].dEdge[0].org = r;
    EdgeArray[i].dEdge[1].org = s;
    EdgeArray[i].dEdge[0].onext = symmdnext;
    EdgeArray[i].dEdge[0].dnext = selfonext;
    EdgeArray[i].dEdge[1].onext = selfdnext;
    EdgeArray[i].dEdge[1].dnext = symmonext;

    EdgeArray[symmdnext.lineNo].dEdge[(symmdnext.direction+1)%2].onext = selfonext;
    EdgeArray[symmdnext.lineNo].dEdge[(symmdnext.direction+1)%2].dnext = new DEindex(i,1);
    EdgeArray[symmonext.lineNo].dEdge[(symmonext.direction+1)%2].onext = new DEindex(i,0);
    EdgeArray[symmonext.lineNo].dEdge[(symmonext.direction+1)%2].dnext = selfdnext;
    EdgeArray[selfdnext.lineNo].dEdge[(selfdnext.direction+1)%2].onext = symmonext;
    EdgeArray[selfdnext.lineNo].dEdge[(selfdnext.direction+1)%2].dnext = new DEindex(i,0);
    EdgeArray[selfonext.lineNo].dEdge[(selfonext.direction+1)%2].onext = new DEindex(i,1);
    EdgeArray[selfonext.lineNo].dEdge[(selfonext.direction+1)%2].dnext = symmdnext;

  }

  ///////////////////////////////////////////////////////////////////////
  // pushneighbours: finds the neighbour edges of the given edge i. Note
  //                 that neighbour edges are those that share the same face.
  //                 Then pushes the neighbour edges if necessary. Marked 
  //                 edges are not pushed into the stack because they are 
  //                 already in the stack. Additionally edges that belongs 
  //                 to convex hull are not pushed simply because they are 
  //                 not digaonals of any quadrangle. Hence nothing to be 
  //                 flipped later. Stack s is also a paramater of it.
  ///////////////////////////////////////////////////////////////////////
  void pushneighbours(int i, Stack s) {

    // Find the four neighbour edges of the current edge.
    DEindex selfonext = EdgeArray[i].dEdge[0].onext;
    DEindex selfdnext = EdgeArray[i].dEdge[0].dnext;
    DEindex symmonext = EdgeArray[i].dEdge[1].onext;
    DEindex symmdnext = EdgeArray[i].dEdge[1].dnext;

    // Push each one into the stack if necessary
    if (!EdgeArray[selfonext.lineNo].isMarked() && 
	EdgeArray[selfonext.lineNo].isDiagonal()) {
      s.push(new Integer(selfonext.lineNo));
    }
    if (!EdgeArray[selfdnext.lineNo].isMarked() && 
	EdgeArray[selfdnext.lineNo].isDiagonal()){
      s.push(new Integer(selfdnext.lineNo));
    }
    if (!EdgeArray[symmonext.lineNo].isMarked() && 
	EdgeArray[symmonext.lineNo].isDiagonal()){
      s.push(new Integer(symmonext.lineNo));
    }
    if (!EdgeArray[symmdnext.lineNo].isMarked() && 
	EdgeArray[symmdnext.lineNo].isDiagonal()){
      s.push(new Integer(symmdnext.lineNo));
    }
  }



  ///////////////////////////////////////////////////////////////////////
  // print: prints the current edge on the standard output.
  ///////////////////////////////////////////////////////////////////////
  public void print(){
    for (int i= 0; i < numOfEdges; i++)
      EdgeArray[i].print();
  }
    
}





