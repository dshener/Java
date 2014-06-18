/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  protected int size;
  protected DList[] htable;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    this.size = 0;
    this.htable = new DList[sizeEstimate + 1];
    
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    this.size = 0;
    this.htable = new DList[100];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    return mod(mod(((493786 * code) + 86325435), 179425579), htable.length);
  }
  
  private int mod(int a, int moduler) {
	  int ianswer = a % moduler;
	  if (ianswer < 0) {
		  return ianswer + moduler;
	  } else {
		  return ianswer;
	  }
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    if (size == 0) {
    	return true;
    } else {
    	return false;
    }
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
	if ((float)size/ (float)htable.length > .75) {
		DList[] mastertable = htable;
		DList[] htable2 = new DList[size * 2];
		htable = htable2;
		size = 0;
		for (int i = 0; i < mastertable.length; i++) {
			if (mastertable[i] == null) {
				continue;
			} else {
				DList currentlist = mastertable[i];
				DListNode currentnode = currentlist.front();
				while (currentnode != null) {
					Entry currententry = (Entry)currentnode.item;
					this.insert(currententry.key, currententry.value);
					currentnode = currentnode.getNext();	
				}
			}
		}	
	}
    int hcode = key.hashCode();
    int ccode = compFunction(hcode);
    Entry newentry = new Entry();
    newentry.key = key;
    newentry.value = value;
    if (htable[ccode] == null) {
    	htable[ccode] = new DList();
    	htable[ccode].insertFront(newentry);
    	size = size + 1;
    } else {
    	htable[ccode].insertFront(newentry);
    	size = size + 1;
    }
    return newentry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int hcode = key.hashCode();
    int ccode = compFunction(hcode);
    if (htable[ccode] == null) {
    	return null;
    }
    if (((DList)htable[ccode]).length() == 0) {
    	return null;
    } else {
    	int size1 = htable[ccode].length();
    	DListNode indexnode = htable[ccode].front();
    	for (int j = 0; j < size1; j++) {
    		if (((Entry)indexnode.item).key().equals(key)) {
    			return (Entry)indexnode.item;
    		}
    		indexnode = indexnode.getNext();
    	}
    	return null;
    }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    int hcode = key.hashCode();
    int ccode = compFunction(hcode);
    if (htable[ccode] == null) {
    	return null;
    }
    if (htable[ccode].length() == 0) {
    	return null;
    } else {
    	DList ourlist = htable[ccode];
    	int size1 = ourlist.length();
    	DListNode nodeindex = ourlist.front();
    	for (int i = 0; i < size1; i ++) {
    		if (((Entry)nodeindex.item).key.equals(key)) {
    			ourlist.remove(nodeindex);
    			size = size - 1;
    			return (Entry)nodeindex.item;
    		}
    		nodeindex = nodeindex.getNext();
    	}
    	return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    int size1 = htable.length;
    htable = new DList[size1];
    size = 0;
  }
  
  public int numcollisions() {
	  int counter = 0;
	  for (int i = 0; i < size; i++) {
		  if (htable[i] == null) {
			  continue;
		  }
		  if (htable[i].getSize() == 0) {
			  continue;
		  } else {
			  counter = counter + htable[i].getSize() - 1;
		  }
	  }
	  return counter;
  }
  
  public void histogram() {
	  for (int i = 0; i < size; i++) {
		  if (htable[i] == null) {
			  System.out.println(0);
			  continue;
		  }
		  if (htable[i].getSize() == 0) {
			  System.out.println(0);
			  continue;
		  } else {
			  System.out.println(htable[i].getSize());
		  }
	  }
  }
  
  public static void main(String[] args) {
	  HashTableChained table = new HashTableChained(10);
	  table.insert("Happy", 4);
	  table.insert("Sad", 1);
	  table.insert("My Bad", 5);
	  table.insert("I don't like", 2);
	  table.remove("Happy");
	  System.out.println((table.htable)[0]);
	  System.out.println((table.htable)[1]);
	  System.out.println((table.htable)[2]);
	  System.out.println((table.htable)[3]);
	  System.out.println((table.htable)[4]);
	  System.out.println((table.htable)[5]);
	  System.out.println((table.htable)[6]);
	  System.out.println((table.htable)[7]);
	  System.out.println((table.htable)[8]);
	  System.out.println((table.htable)[9]);
	  System.out.println((table.htable)[10]);
  }

}