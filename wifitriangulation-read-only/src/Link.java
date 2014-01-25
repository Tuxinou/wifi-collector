///////////////////////////////////////////////////////////////////////////////
/// Link.java
/// Author : Alper UNGOR
/// Last Updated: Wed Apr 30 15:35
///////////////////////////////////////////////////////////////////////////////

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Point;
import Cell;

public class Link extends Applet {

  // Head of the linked list
  public Cell head;

  // a refference to the active window of the linked list
  // all insertions and deletions are performed using this
  public Cell win;

  // length of the linked list
  public int length;

  ///////////////////////////////////////////////////////////////////////
  // Link: is the constructor for Link class. It constructs an empty list.
  ///////////////////////////////////////////////////////////////////////
  public Link() {
    head = null;
    win = head;
  }

  ///////////////////////////////////////////////////////////////////////
  // insert: inserts a new node to the linked list. Paremeters are the 
  //         two features of a node: val is the point index, edge1 is the
  //         edge index.
  ///////////////////////////////////////////////////////////////////////
  public void insert(int val, int edge1) {
    if (head == null) {
      head = new Cell(val, edge1);
      win = head;
    }
    else { 
      win.insert(new Cell(val, edge1));
      this.next();
    }
    length++;
  }

  ///////////////////////////////////////////////////////////////////////
  // insert: is a variation of the previous function. This one has two indices
  //         for edges.
  ///////////////////////////////////////////////////////////////////////
  public void insert(int val, int edge1, int edge2) {
    if (head == null) {
      head = new Cell(val, edge1);
      win = head;
    }
    else { 
      win.insert(new Cell(val, edge1), edge2);
      this.next();
    }
    length++;
  }

  ///////////////////////////////////////////////////////////////////////
  // remove: removes the element refferenced by the win (active window).
  ///////////////////////////////////////////////////////////////////////
  public void remove() {
    if (win != null) {
      if (win == head) head = head.prev;
      win = win.prev;
      win.next.remove();
      length--;
    }
    else 
      head = win = null;
  }

  ///////////////////////////////////////////////////////////////////////
  // next: moves forward in the linked list changing the active window.
  ///////////////////////////////////////////////////////////////////////
  public int next() {
    win = win.next;
    return win.point;
  }

  ///////////////////////////////////////////////////////////////////////
  // prev: moves backward in the linked list changing the active window.
  ///////////////////////////////////////////////////////////////////////
  public int prev() {
    win = win.prev;
    return win.point;
  }

  ///////////////////////////////////////////////////////////////////////
  // empty: checks if the linked list is empty.
  ///////////////////////////////////////////////////////////////////////
  public boolean empty() {
    return (head == null);
  }

  ///////////////////////////////////////////////////////////////////////
  // makeEmpty: removes all elements in the linked list. Since explicit 
  //            deallocation of memory is not available in Java. I just
  //            set the pointers to null and let the automatic garbage
  //            collector take care of them. I think breaking the circular
  //            linked list is important. Otherwise reference counting 
  //            mechanism can not handle circular lists.
  ///////////////////////////////////////////////////////////////////////
  public void makeEmpty() {
    Cell scan, temp;
    scan = head;
    while (scan !=null) {
      temp = scan.next;
      scan.next = null;
      scan.prev = null;
      scan = temp;
    }
    head = win = null;
  }

}



