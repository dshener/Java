package player;

/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList {

  /**
   *  head references the first node.
   *  tail references the last node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected DListNode tail;
  protected int size;

  /* DList invariants:
   *  1)  head.prev == null.
   *  2)  tail.next == null.
   *  3)  For any DListNode x in a DList, if x.next == y and x.next != null,
   *      then y.prev == x.
   *  4)  For any DListNode x in a DList, if x.prev == y and x.prev != null,
   *      then y.next == x.
   *  5)  The tail can be accessed from the head by a sequence of "next"
   *      references.
   *  6)  size is the number of DListNodes that can be accessed from the
   *      head by a sequence of "next" references.
   */

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(int a) {
    head = new DListNode();
    tail = head;
    head.item = a;
    size = 1;
  }

  /**
   *  DList() constructor for a two-node DList.
   */
  public DList(int a, int b) {
    head = new DListNode();
    head.item = a;
    tail = new DListNode();
    tail.item = b;
    head.next = tail;
    tail.prev = head;
    size = 2;
  }
  
  public DList(Square a) {
	  head = new DListNode();
	  tail = head;
  }
  
  
  public DListNode getHead() {
	  return head;
  }
  
  public DListNode getTail() {
	  return tail;
  }
  
  public int getSize() {
	  return size;
  }

  /**
   *  insertFront() inserts an item at the front of a DList.
   */
  public void insertFront(int i) {
    DListNode mhead = new DListNode(i);
    if (this.head == null) {
    	this.head = mhead;
    	this.tail = this.head;
    	size = size + 1;
    } else {
    this.head.prev = mhead;
    mhead.next = this.head;
    this.head = mhead;
    size = size + 1;
    if (size == 1) {
    	this.tail = this.head;
    }
    }
  }
  

  
  public void insertBack(Object i) {
	  DListNode mback = new DListNode(i);
	  if (this.head == null) {
		  this.head = mback;
		  this.tail = mback;
	  }else if(size == 1){
		  	head.next = mback;
		  	tail = mback;
		  	tail.prev = head;
	  }else {
		  this.tail.next = mback;
		  mback.prev = this.tail;
		  this.tail = mback;
	  } size++;
  }
  
  public void insertFront(Square i) {
	    DListNode mhead = new DListNode(i);
	    if (this.head == null) {
	    	this.head = mhead;
	    	this.tail = this.head;
	    	size = size + 1;
	    } else {
	    this.head.prev = mhead;
	    mhead.next = this.head;
	    this.head = mhead;
	    size = size + 1;
	    if (size == 1) {
	    	this.tail = this.head;
	    }
	    }
	  }
  
  public void insertFront(Object i) {
	    DListNode mhead = new DListNode(i);
	    if (this.head == null) {
	    	this.head = mhead;
	    	this.tail = this.head;
	    	size = size + 1;
	    } else {
	    this.head.prev = mhead;
	    mhead.next = this.head;
	    this.head = mhead;
	    size = size + 1;
	    if (size == 1) {
	    	this.tail = this.head;
	    }
	    }
  }

  /**
   *  removeFront() removes the first item (and node) from a DList.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    if (size == 0) {
    } else {
    	DListNode proxy = this.head.next;
    	this.head = proxy;
    	if (size > 1) {
    	this.head.prev = null;
    	size = size - 1;
    	} else {
    	size = size - 1; 
    	tail = null;
    	}
    	}
  }
  
  public void removeBack() {
	  if (size == 0) {
		  
	  } else {
		  DListNode proxy = this.tail.prev;
		  this.tail = proxy;
		  if (size > 1) {
			  this.tail.next = null;
			  size = size - 1;
		  } else {
			  size = size - 1;
			  head = null;
		  }
	  }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head;
    while (current != null) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

}