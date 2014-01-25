//////////////////////////////////////////////////////////////////////////////////////
/// Delaunay.java
/// Alper UNGOR
/// Wed Apr 30 15:35
//////////////////////////////////////////////////////////////////////////////////////
//  This program is written for the animation of two geometric algorithms.
//  First one is plane sweep algorithm. Given a set of points, this algorithm
//  computes a triangulation and the convex hull of these points. Second
//  algorithm is Edge Flip algorithm, it flips the edges of the arbitrary
//  triangulation constructed by the first algorithm. And this way it finds
//  the Delaunay Triangulation of the given set of points. A special data
//  structure(Quad Edge) is used to make edge flip easy and efficient. This 
//  file generally includes animation related code. Quad Edge Data structure
//  is available in DEindex, Dedge, Edge and Triangulation files. Cell and
//  Link files includes a doubly linked circular linked list which is used
//  in the implementation of the plane sweep algorithm. Convex Hull is kept
//  in this data structure.
//  See my project report for further details of algorithm and implementation.
//  Also this my first java program. I think it was quite an amateour job.
//  I hope to improve this program and extend it to show Voronoi regions,
//  circles that cover the triangles, etc.
//////////////////////////////////////////////////////////////////////////////////////

import java.applet.Applet;
import java.util.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.*;
import Cell;
import Link;
import Edge;
import Triangulation;

public class Delaunay extends Applet implements Runnable{

  // Following are animation related items such as labels for user
  // interface, times to measure the efficiency.
  Thread runner;
  Label sizeL, delaunayTimeL, sweepTimeL, systemTimeL, edgeNumL,
    freeMemL, usedMemL;
  Scrollbar speedScroll, numPointsScroll;
  Button startButton;
  Runtime execTime;
  long initialTime;
  long prevTime;
  long sweepTime;
  long delaunayTime;

  // number of points in the data set
  int numPoints = 6;
  int nextNumPoints = 6;

  // screen size 
  int WIDTH = 800;
  int HEIGHT = 500;

  // TIME controls the animation speed (higher it is, slower the animation)
  int TIME = 5;

  boolean started = true;

  boolean DelaunayWorking = false;

  // Stack is used to keep non Delaunay edges during Edge Flip algorithm
  Stack nonDelaunayEdges;

  // Points are kept in an array
  Point PointArray[];

  // index for the current Point in the Point Array
  int currPoint = 0;

  // Triangulation is kept in a quad edge data structure.
  Triangulation T;

  // Keep the convex polygon in a circular doubly linked list
  Link pointlist = new Link();

  /////////////////////////////////////////////////////////////////////////////////
  // lessThan : compares two points, returns true if first point is
  //            geometrically less than the second, false otherwise.
  /////////////////////////////////////////////////////////////////////////////////
  public boolean lessThan(Point a, Point b) {
    return ((a.x < b.x) || ((a.x == b.x) && (a.y < b.y)));
  }

  /////////////////////////////////////////////////////////////////////////////////
  // insertionsort: sorts some elements of an array defined by the range
  //                low and high. as its name implies it uses insertion sort
  //                algorithm.
  /////////////////////////////////////////////////////////////////////////////////
  public void insertionsort(Point A[], int low, int high) {
    
    for (int i=low+1; i<=high; i++) {
      Point temp = new Point(A[i].x, A[i].y);  
      int j;
      for (j = i; j > low  &&  lessThan(temp, A[j-1]); j--) {
	A[j].x = A[j-1].x;
        A[j].y = A[j-1].y;
      }
      A[j].x = temp.x;
      A[j].y = temp.y;
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // quicsort: sorts the elements of an array. It recursively calls itself.
  //           For small arrays it calls insertionsort which is known to
  //           be more efficient in that case.
  ///////////////////////////////////////////////////////////////////////////////////
  public void quicksort(Point A[], int low, int high) {

    //10 is CutOff constant
    if (low + 10 > high) 
     insertionsort(A, low, high);
    else {
      int middle = (low + high) /2;
      if (lessThan(A[middle], A[low]))
	swap_point(A[low], A[middle]);
      if (lessThan(A[high], A[low]))
	swap_point(A[low], A[high]);
      if (lessThan(A[high], A[middle]))
	swap_point(A[middle], A[high]);

      Point pivot = new Point(A[middle].x, A[middle].y);
      swap_point(A[middle], A[high-1]);

      // partition the array using the pivot
      int i, j;
      for (i=low, j=high-1; ; ) {
	while(lessThan(A[++i], pivot));
	while(lessThan(pivot, A[--j]));
	if (i<j)
	  swap_point(A[i], A[j]);
	else
	  break;
      }
      swap_point(A[i], A[high-1]);

      // Now sort each partition
      quicksort(A, low, i-1);
      quicksort(A, i+1, high);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // swap_point: interchanges two points
  ///////////////////////////////////////////////////////////////////////////////////
  public void swap_point(Point p, Point q) {

    Point temp = new Point(p.x, p.y);
    p.x = q.x; 
    p.y = q.y; 
    q.x = temp.x; 
    q.y = temp.y;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // left_turn: returns true if one needs to make a left turn to go point c,
  //            coming from a to b. Note that relational operator used here
  //            is the opposite of the one in lecture notes. This is due
  //            to definition of graphics window which is the 4th quadrant.
  ///////////////////////////////////////////////////////////////////////////////////
  public boolean left_turn(Point a, Point b, Point c) {
    long temp = (a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y));

    // Check if it's degenerate
    if (temp == 0)
      System.out.println("Exception left turn 0");

    return(temp < 0);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // right_turn: returns true if one needs to make a right to go point c,
  //            coming from a to b. Note that relational operator used here
  //            is the opposite of the one in lecture notes. This is due
  //            to definition of graphics window which is the 4th quadrant.
  ///////////////////////////////////////////////////////////////////////////////////
  public boolean rightTurn(Point a, Point b, Point c) {
    long temp = (a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y));

    // Check if it's degenerate
    if (temp == 0)
      System.out.println("Exception right turn 0");

    return(temp > 0);
 }

  ///////////////////////////////////////////////////////////////////////////////////
  // animate: This function handles the details of the animation such
  //          as details refreshing the graphics and execution time 
  //          of the algorithms.
  ///////////////////////////////////////////////////////////////////////////////////
  public void animate() {

    long now = System.currentTimeMillis();

    if (DelaunayWorking)
      delaunayTime += now - prevTime;
    else
      sweepTime += now - prevTime;

    repaint();
    try { Thread.sleep(TIME); }
    catch(InterruptedException e) { }

    prevTime = System.currentTimeMillis();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // statistics: prints execution time statistics and the number of edges
  //             on the corresponding labels
  ///////////////////////////////////////////////////////////////////////////////////
  public void statistics() {
    String h1 = String.valueOf("SweepLine Exec Time: ");
    String h2 = String.valueOf("Delaunay Exec Time: ");
    String h3 = String.valueOf("Animation Time: ");
    String u1 = String.valueOf("msec");
    String u2 = String.valueOf("sec");

    sweepTimeL.setText(h1.concat( String.valueOf(sweepTime)).concat(u1));
    delaunayTimeL.setText(h2.concat(String.valueOf(delaunayTime)).concat(u1));
    long now = System.currentTimeMillis();
    systemTimeL.setText(h3.concat(String.valueOf((now - initialTime)/1000.0).concat(u2)));
    edgeNumL.setText(String.valueOf("Edge Num: ").concat(String.valueOf(T.numOfEdges())));
  }
  
  ///////////////////////////////////////////////////////////////////////////////////
  // isLocalDelaunay: checks if ith edge of the Triangulation is locally
  //                  Delaunay and returns true if so, false otherwise
  ///////////////////////////////////////////////////////////////////////////////////
  public boolean isLocalDelaunay(int i){

    Point p = PointArray[T.start(i)];
    Point q = PointArray[T.end(i)];
    Point r = PointArray[T.rightPointIndex(i)];
    Point s = PointArray[T.leftPointIndex(i)];

    return(signDet3(p,q,r)*signDet4(p,q,r,s) > 0);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // signDet3: returns the sign of the Determinant of three points
  ///////////////////////////////////////////////////////////////////////////////////
  public int signDet3(Point p, Point q, Point r){
    if (rightTurn(p, q, r))
      return(1);
    else 
      return(-1);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // signDet4: returns the sign of the Determinant of four points
  ///////////////////////////////////////////////////////////////////////////////////
  public int signDet4(Point p, Point q, Point r, Point s){
    
    // to prevent overflow I used long
    long pz = p.x*p.x + p.y*p.y;
    long qz = q.x*q.x + q.y*q.y;
    long rz = r.x*r.x + r.y*r.y;
    long sz = s.x*s.x + s.y*s.y;

    long x = det(1,p.x,1,q.x)*det(r.y,rz,s.y,sz) -
      det(1,p.y,1,q.y)*det(r.x,rz,s.x,sz) + 
      det(1,pz,1,qz)*det(r.x,r.y,s.x,s.y) + 
      det(p.x,p.y,q.x,q.y)*det(1,rz,1,sz) - 
      det(p.x,pz,q.x,qz)*det(1,r.y,1,s.y) +
      det(p.y,pz,q.y,qz)*det(1,r.x,1,s.x);
    if (x>0)
      return (1);
    else 
      return (-1);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // det: returns the determinant of the 2x2 matrix  ( a b )
  //                                                 ( c d )
  ///////////////////////////////////////////////////////////////////////////////////
  public long det(long a, long b, long c, long d) {
    return (a*d - b*c);
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // firstTriangle: constructs the first Triangle of the Triangulation
  //                it needs special care. Pattern of the first three
  //                points effects the direction of the circular linked
  //                list
  ///////////////////////////////////////////////////////////////////////////////////
  public void firstTriangle() {
    if (left_turn(PointArray[2], PointArray[1], PointArray[0])) {
      pointlist.insert(1,0); 
      pointlist.insert(0,2);
      pointlist.insert(2,1);
      T.insert(0, 1);
      animate();
      T.insert(1, 2);
      animate();
      T.insert(0, 2, 0, 1);
    }
    else {
      pointlist.insert(0,0); 
      pointlist.insert(1,1);
      pointlist.insert(2,2);
      T.insert(0, 1);
      animate();
      T.insert(1, 2);
      animate();
      T.insert(2, 0, 1, 0);
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // sweepLine: is the implementation of Plane Sweep Algorithm. An imaginery
  //            sweep line goes through all the points and inserts into the
  //            triangulation.
  ///////////////////////////////////////////////////////////////////////////////////
  public void sweepLine() {
    firstTriangle();
    animate();
    
    for (int i=3; i < numPoints ; i++) {
      currPoint = i;
      animate();
      
      int prevNumEdge = T.numOfEdges();
      T.insert(pointlist.win.point, i);
      animate();
      
      Cell revclock = pointlist.win; 
      while (rightTurn(PointArray[i], PointArray[revclock.point], 
		       PointArray[revclock.next.point])) {
	T.insert(i, revclock.next.point, T.numOfEdges()-1, revclock.edge); 
	revclock = revclock.next;
	animate();
      }
      
      int midNumEdge = T.numOfEdges();
      Cell clock = pointlist.win;
      while (left_turn(PointArray[i], PointArray[clock.point], 
		       PointArray[clock.prev.point])) {
	clock = clock.prev;
	T.insert(clock.point, i, clock.edge, prevNumEdge);
	prevNumEdge = T.numOfEdges()-1;
	animate();
      }
      
      pointlist.win = clock;
      while (pointlist.win.next != revclock) {
	pointlist.next();
	pointlist.remove();
      }
      
      if (midNumEdge != T.numOfEdges())
	pointlist.insert(i, midNumEdge-1, T.numOfEdges()-1);
      else 
	pointlist.insert(i, midNumEdge-1, prevNumEdge);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // makeDelaunay: is the implementaion of Edge Flip algorithm. It first
  //               pushes all nonLD edges into a stack. Then flips all
  //               edges taken from the stack.
  ///////////////////////////////////////////////////////////////////////////////////
  public void makeDelaunay() {

    for (int i=T.numOfEdges()-1; i>0; i--)
      if (T.isDiagonal(i) && !isLocalDelaunay(i)) {
	nonDelaunayEdges.push(new Integer(i));
	T.mark(i);
      }
    while (!nonDelaunayEdges.empty()) { 
      Integer temp = (Integer) nonDelaunayEdges.pop();
      int x = temp.intValue();
      T.unmark(x);
      if(!(isLocalDelaunay(x))) {
	T.edgeFlip(x);
	T.pushneighbours(x, nonDelaunayEdges);
	animate();
      }
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // run: method is the main loop of the running applet. It calls the two main
  //      functions that performs the plane sweep and edge-flip algorithms
  ///////////////////////////////////////////////////////////////////////////////////
  public void run() {

    while(true) {
      if (started) {
    	started = false;

	initialize();

	sweepLine();

	repaint();
	DelaunayWorking = true;
      	makeDelaunay();

	statistics();
      }
      else {
	repaint();
	try { Thread.sleep(TIME); }
	catch(InterruptedException e) { }
      }
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // initialize: initializes several structures such as Point array,
  //             linked list, etc.
  ///////////////////////////////////////////////////////////////////////////////////
  public void initialize() {

    numPoints = nextNumPoints;
    PointArray = new Point[numPoints];
    nonDelaunayEdges = new Stack();
    currPoint = 0;
    T = new Triangulation(3*numPoints - 6);

   //Create random points and Sort them first 
    for (int i=0; i<numPoints; i++) {
      int x = (int) (Math.random() * WIDTH);
      int y = (int) (Math.random() * HEIGHT);
      PointArray[i] = new Point(x,y);
    }

    quicksort(PointArray,0,numPoints-1);
    
    // clean up the polygon,  
    pointlist.makeEmpty();

    DelaunayWorking = false;
    sweepTime = delaunayTime = 0;
    prevTime = initialTime = System.currentTimeMillis();

    // reset the statistics labels
    String h1 = String.valueOf("SweepLine Exec Time: ");
    String h2 = String.valueOf("Delaunay Exec Time: ");
    String h3 = String.valueOf("Animation Time: ");
 
    sweepTimeL.setText(h1.concat( String.valueOf(" ?")));
    delaunayTimeL.setText(h2.concat(String.valueOf(" ?")));
    systemTimeL.setText(h3.concat(String.valueOf(" ?")));
    edgeNumL.setText(String.valueOf("Edge Num:  ?"));

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // init: is one of the standard functions of Java Applets. It is the first function
  //       to be called when applet start execution. It is used to initialize the user 
  //       interface.
  ///////////////////////////////////////////////////////////////////////////////////
  public void init() {

    //Create user interface
    setLayout( new GridLayout(5,5,10,10));

    sizeL = new  Label("6");
    sweepTimeL = new  Label("SweepLine Exec Time: ");
    delaunayTimeL = new  Label("Delaunay Exec Time: ");
    systemTimeL = new  Label("System Time : ");
    edgeNumL = new  Label("Edge Num : ");
    
    speedScroll = new Scrollbar(Scrollbar.HORIZONTAL, 1 , 0, 1, 90);
    numPointsScroll = new Scrollbar(Scrollbar.HORIZONTAL, 1, 0, 1, 90);
    startButton = new Button("START");
    
    add(new Label("High Speed"));
    add(speedScroll);
    add(new Label("Low Speed"));
    add(new Label("Number of Points"));
    add(numPointsScroll);
    add(sizeL);
    add(new Label(""));
    add(startButton);
    add(new Label(""));
    add(sweepTimeL);
    add(delaunayTimeL);
    add(systemTimeL);
    add(edgeNumL);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // handleEvent: is another user interface function. It handles the events like
  //              mouse click on start button or adjusting the scroll bar.
  ///////////////////////////////////////////////////////////////////////////////////
  public boolean handleEvent(Event evt) {
    if (evt.target instanceof Scrollbar) {
      if ((Scrollbar)evt.target == speedScroll) {
	int v = ((Scrollbar)evt.target).getValue();
	TIME = 10*v;
      }
      if (((Scrollbar)evt.target) == numPointsScroll) {
	int v = ((Scrollbar)evt.target).getValue();
	nextNumPoints = 3*v;
	sizeL.setText(String.valueOf(3*v));
      }      
    }
    else if (evt.target instanceof Button) 
      if ((Button)evt.target == startButton) 
	if (evt.id == 1001) 
	  started = true;
	
    return true;
  }
  
  ///////////////////////////////////////////////////////////////////////////////////
  // start: provides startup behaviour for the applet
  ///////////////////////////////////////////////////////////////////////////////////
  public void start() {
    if (runner == null) {
      runner = new Thread(this);
      runner.start();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // stop: is a special method of the applet. It is used to stop the execution of 
  //       the applet
  ///////////////////////////////////////////////////////////////////////////////////
  public void stop() {
    if (runner != null) {
      runner.stop();
      runner = null;
    }
  }
  
  ///////////////////////////////////////////////////////////////////////////////////
  // paintAll: paints all the graphical objects into the graphics window. It first
  //           prints the points of the point set, then the edges of the triangulation
  //           and then the edges of the convex hull.
  ///////////////////////////////////////////////////////////////////////////////////
  public void paintAll(Graphics g) {
    
    // print the points in red color
    g.setColor(Color.red);
    for (int i=0; i < numPoints ; i++) 
      g.drawOval(PointArray[i].x, PointArray[i].y, 1, 1);

    // draw the edges of the triangulation in blue
    g.setColor(Color.blue);
    for (int i=0; i < T.numOfEdges() ; i++) 
      g.drawLine(PointArray[T.start(i)].x, PointArray[T.start(i)].y,
		 PointArray[T.end(i)].x, PointArray[T.end(i)].y);

    // overwrite outermost  edges of the triangulation with green which are also 
    // the edges of the convex hull
    g.setColor(Color.green);
    Cell scan;
    scan = pointlist.head;
    while (scan != null && scan.next != null && scan.next != pointlist.head) {
      g.drawLine(PointArray[scan.point].x, PointArray[scan.point].y,
		 PointArray[scan.next.point].x, PointArray[scan.next.point].y);
      scan = scan.next;
    }
    if (scan != null && scan.next != null)
      g.drawLine(PointArray[scan.point].x, PointArray[scan.point].y,
		 PointArray[scan.next.point].x, PointArray[scan.next.point].y);
  }

  // An off screen image is used to get rid of the flickering in the animation
  Image offscreen;
  Dimension offscreensize;
  Graphics offgraphics;

  ///////////////////////////////////////////////////////////////////////////////////
  // update: is called by the repaint function. It is used to clear the screen. 
  //         However, since clearing the screen creates flickering effect, I used
  //         another technique. In this function I load the contents of the offimage
  //         into the main graphic window. Since loading an image is costly, this
  //         approach limits the speed of the animation.
  ///////////////////////////////////////////////////////////////////////////////////
  public void update(Graphics g) {
    Dimension d = size();
    if ((offscreen == null) || (d.width != offscreensize.width) 
	|| (d.height != offscreensize.height)) {
      offscreen = createImage(d.width, d.height);
      offscreensize = d;
      offgraphics = offscreen.getGraphics();
      offgraphics.setFont(getFont());
    }
    
    offgraphics.setColor(getBackground());
    offgraphics.fillRect(0, 0, d.width, d.height);
    
    paintAll(offgraphics);
    g.drawImage(offscreen, 0, 0, null);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // insets: specifies the distance of the current canvas to the borders 
  ///////////////////////////////////////////////////////////////////////////////////
  public Insets insets() {
    return new Insets(HEIGHT + 10 , 10, 10, 10);
  }

}
