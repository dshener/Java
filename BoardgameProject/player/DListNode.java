package player;

/* DListNode.java */

/**
 *  A DListNode is a node in a DList1 (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public Object item;
  protected DListNode prev;
  protected DListNode next;

  /**
   *  DListNode() constructor.
   */
  public DListNode() {
    item = null;
    prev = null;
    next = null;
  }
  
  public Object getItem() {
	  return item;
  }
  
  public DListNode getNext() {
	  return next;
  }
  
  public DListNode getPrev() {
	  return prev;
  }
  
  public void setItem(Square square) {
	  item = square;
  }
  
  public void setItem(Object object) {
	  item = object;
  }
  

  
  public DListNode findNth(long n) {
	  return findNthH(n, this);
  }
  
  private DListNode findNthH(long counter, DListNode node) {
	  if (counter == 1) {
		  return node;
	  } else {
		  return findNthH(counter - 1, node.next);
	  }
  }
  

  public DListNode(int i) {
    item = i;
    prev = null;
    next = null;
  }
  
  public DListNode(Square i) {
	  item = i;
	  prev = null;
	  next = null;
  }
  
  public DListNode(Object i) {
	  item = i;
	  prev = null;
	  next = null;
  }
   
}
