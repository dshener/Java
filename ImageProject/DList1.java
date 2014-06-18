/* DList1.java */

/**
 *  A DList1 is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList1 {

  /**
   *  head references the first node.
   *  tail references the last node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode1 head;
  protected DListNode1 tail;
  protected long size;

  /* DList1 invariants:
   *  1)  head.prev == null.
   *  2)  tail.next == null.
   *  3)  For any DListNode1 x in a DList, if x.next == y and x.next != null,
   *      then y.prev == x.
   *  4)  For any DListNode1 x in a DList, if x.prev == y and x.prev != null,
   *      then y.next == x.
   *  5)  The tail can be accessed from the head by a sequence of "next"
   *      references.
   *  6)  size is the number of DListNode1s that can be accessed from the
   *      head by a sequence of "next" references.
   */

  /**
   *  DList1() constructor for an empty DList1.
   */
  public DList1() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  DList1() constructor for a one-node DList1.
   */
  public DList1(int[] a) {
    head = new DListNode1();
    tail = head;
    head.item = a;
    size = 1;
  }

  /**
   *  DList1() constructor for a two-node DList1.
   */

  /**
   *  insertFront() inserts an item at the front of a DList1.
   */
  public void insertFront(int[] i) {
    DListNode1 front = new DListNode1();
    front.item = i;
    if(head == null){
      head = front;
      tail = front;
    }
    else if (tail == head){
      head = front;
      head.next = tail;
      tail.prev = head;
    }
    else {
      front.next = head;
      head.prev = front;
      head = front;
    }
    size++;
    }
  public void insertEnd(int[] i){
    DListNode1 end = new DListNode1();
    end.item = i;
    if(head == null){
      head = end;
      tail = end;
    }
    else if(tail == head){
      tail = end;
      head.next = end;
      tail.prev = head;
    }
    else{
      tail.next = end;
      end.prev = tail;
      tail = tail.next;
    }
    size++;
  }

  /**
   *  removeFront() removes the first item (and node) from a DList1.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    if(head == null)
      return;
    if (head == tail){
      head = null;
      tail = null;
    }
    else{
      head = head.next;
      head.prev = null;
    }
    size --;
  }
  public void removeEnd(){
    if(head == null)
      return;
    if (head == tail){
      head = null;
      tail = null;
    }
    else{
      tail = tail.prev;
      tail.next = null;
    }
    size--;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
 // public String toString() {
 //   String result = "[  ";
 //   DListNode1 current = head;
 //   while (current != null) {
 //     result = result + current.item + "  ";
  //    current = current.next;
  //  }
    //return result + "]";
  //}

  public static void main(String[] args) {
  }

}
