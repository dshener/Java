/* ListSorts.java */
package graphalg;

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    LinkedQueue finalqueue = new LinkedQueue();
    int sze = q.size();
    for (int i = 0; i < sze; i++) {
    	LinkedQueue proxyqueue = new LinkedQueue();
    	try {
    		Comparable element = (Comparable) q.dequeue();
    		proxyqueue.enqueue(element);
    		finalqueue.enqueue(proxyqueue);
    	} catch (QueueEmptyException e1) {
    		System.out.println("Youve got an empty queue bro");
    	}
    }
    return finalqueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    int sze = q1.size();
    LinkedQueue finalqueue = new LinkedQueue();
    while(!q1.isEmpty() && !q2.isEmpty()) {
    	try {
    		if (((Comparable)q1.front()).compareTo(q2.front()) >= 0) {
    			Comparable element = (Comparable) q2.dequeue();
    			finalqueue.enqueue(element);	
        	} else {
        		Comparable element  = (Comparable) q1.dequeue();
        		finalqueue.enqueue(element);
        	}
    	} catch (QueueEmptyException e1) {
    		System.out.println("Youve got an empty queue bro at mergeSortedQueues");
    	}
    }
    boolean q1Empty = q1.isEmpty();
    if (q1Empty) {
    	while (!q2.isEmpty()) {
    		try {
    			Comparable element = (Comparable) q2.dequeue();
    			finalqueue.enqueue(element);
    		} catch (QueueEmptyException e1) {
    			System.out.println("Youve got an empty queue bro at merge SortedQueues");
    		}
    	}
    } else {
    	while (!q1.isEmpty()) {
    		try {
    			Comparable element = (Comparable) q1.dequeue();
    			finalqueue.enqueue(element);
    		} catch (QueueEmptyException e1) {
    			System.out.println("Youve got an empty queue bro at merge SortedQueues");
    		}
    	}
    }
    return finalqueue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    while (!qIn.isEmpty()) {
    	try {
    		Comparable element1 = (Comparable) qIn.dequeue();
    		if (pivot.compareTo(element1) > 0) {
    			qSmall.enqueue(element1);
    		}
    		if (pivot.compareTo(element1) == 0) {
    			qEquals.enqueue(element1);
    		}
    		if (pivot.compareTo(element1) < 0) {
    			qLarge.enqueue(element1);
    		}
    	} catch (QueueEmptyException e1) {
    		System.out.println("You've got an empty queue in partition bro");
    	}
    }
  }
  
  public static void edgepartition(LinkedQueue qIn, Object[] edgepivot, LinkedQueue qSmall,
		  						   LinkedQueue qEquals, LinkedQueue qLarge) {
	  while (!qIn.isEmpty()) {
		  try {
			  Object[] element1 = (Object[])qIn.dequeue();
			  int pivotweight = (int)edgepivot[2];
			  int elementweight = (int)element1[2];
			  if (pivotweight > elementweight) {
				  qSmall.enqueue(element1);
			  }
			  if (pivotweight == elementweight) {
				  qEquals.enqueue(element1);
			  }
			  if (pivotweight < elementweight) {
				  qLarge.enqueue(element1);
			  }
		  } catch (QueueEmptyException e1) {
			  System.out.println("partition error");
		  }
	  }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
	  if (q.size() == 0) {
		  return;
	  }
	  LinkedQueue qOfq = ListSorts.makeQueueOfQueues(q);
	  while (qOfq.size() > 1) {
		  try {
			  LinkedQueue element1 = (LinkedQueue) qOfq.dequeue();
			  LinkedQueue element2 = (LinkedQueue) qOfq.dequeue();
			  LinkedQueue sortedQueue = mergeSortedQueues(element1, element2);
			  qOfq.enqueue(sortedQueue);
		  } catch (QueueEmptyException e1) {
			  System.out.println("Bro you have an empty Queue at mergeSort");
		  }
	  }
	  try {
		  q.append((LinkedQueue) qOfq.dequeue());
	  } catch (QueueEmptyException e1) {
		  System.out.println("Bro you have an empty Queue at mergeSort");
	  }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
	  if (q.size() <= 1) {
		  return;
	  }
	  LinkedQueue newqueue = quickSort2(q);
	  while (!q.isEmpty()) {
		  try {
			  q.dequeue();
		  } catch (QueueEmptyException e1) {
			  System.out.println("You have an empty queue in quicksort");
		  }
	  }
	  q.append(newqueue);
  }
  //Sorts Kruskal internal representation of edges
  public static void edgeQuickSort(LinkedQueue q) {
	  if (q.size() <= 1) {
		  return;
	  }
	  LinkedQueue newqueue = edgeQuickSort2(q);
	  while (!q.isEmpty()) {
		  try {
			  q.dequeue();
		  } catch (QueueEmptyException e1) {
			  System.out.println("You have an empty queue in quicksort");
		  }
	  }
	  q.append(newqueue);
  }
  
  public static LinkedQueue quickSort2(LinkedQueue q) {
	  if (q.size() <= 1) {
		  return q;
	  } else {
		  LinkedQueue small = new LinkedQueue();
		  LinkedQueue med = new LinkedQueue();
		  LinkedQueue big = new LinkedQueue();
		  int randomNumber = (int) ((Math.random()) * q.size()) + 1 ;
		  Comparable pivot = (Comparable)q.nth(randomNumber);
		  ListSorts.partition(q, pivot, small, med, big);
		  LinkedQueue sortedsmall = ListSorts.quickSort2(small);
		  LinkedQueue sortedlarge = ListSorts.quickSort2(big);
		  LinkedQueue finalqueue = new LinkedQueue();
		  finalqueue.append(sortedsmall);
		  finalqueue.append(med);
		  finalqueue.append(sortedlarge);
		  return finalqueue;  
	  }
  }
  // Sorts Kruskal internal representation of edges
  public static LinkedQueue edgeQuickSort2(LinkedQueue q) {
	  if (q.size() <= 1) {
		  return q;
	  } else {
		  LinkedQueue small = new LinkedQueue();
		  LinkedQueue med = new LinkedQueue();
		  LinkedQueue big = new LinkedQueue();
		  int randomNumber = (int) ((Math.random()) * q.size()) + 1 ;
		  Object[] pivot = (Object[])q.nth(randomNumber);
		  ListSorts.edgepartition(q, pivot, small, med, big);
		  LinkedQueue sortedsmall = ListSorts.edgeQuickSort2(small);
		  LinkedQueue sortedlarge = ListSorts.edgeQuickSort2(big);
		  LinkedQueue finalqueue = new LinkedQueue();
		  finalqueue.append(sortedsmall);
		  finalqueue.append(med);
		  finalqueue.append(sortedlarge);
		  return finalqueue; 
	  }
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {
	
	LinkedQueue d = new LinkedQueue();
	Object[] array1 = new Object[3];
	array1[0] = 1;
	array1[1] = 1;
	array1[2] = 6;
	Object[] array2 = new Object[3];
	array2[0] = 2;
	array2[1] = 2;
	array2[2] = 4;
	Object[] array3 = new Object[3];
	array3[0] = 3;
	array3[1] = 3;
	array3[2] = 19;
	Object[] array4 = new Object[3];
	array4[0] = 4;
	array4[1] = 4;
	array4[2] = 6;
	Object[] array5 = new Object[3];
	array5[0] = 5;
	array5[1] = 5;
	array5[2] = 2;
	d.enqueue(array1);
	d.enqueue(array2);
	d.enqueue(array3);
	d.enqueue(array4);
	d.enqueue(array5);
	edgeQuickSort(d);
	while (!d.isEmpty()) {
		try {
			Object[] ourarray = (Object[])d.dequeue();
			System.out.println("Starting new Array");
			System.out.println(ourarray[0]);
			System.out.println(ourarray[1]);
			System.out.println(ourarray[2]);		
		} catch (QueueEmptyException e1) {
			System.out.println("Error at testing");
		}
	}
	
	/*
	LinkedQueue d = makeRandom(10);
	System.out.println(d.toString());
	System.out.println((ListSorts.makeQueueOfQueues(d)).toString());
	
	System.out.println("Testing Partition");
	LinkedQueue g = new LinkedQueue();
	*/
	/*
	g.enqueue(2);
	g.enqueue(6);
	g.enqueue(11);
	g.enqueue(11);
	g.enqueue(6);
	g.enqueue(6);
	g.enqueue(6);
	g.enqueue(27);
	g.enqueue(12);
	g.enqueue(1);
	g.enqueue(4);
	g.enqueue(3);
	g.enqueue(5);
	*/
	
	/*
	System.out.println("Here is what g looks like");
	System.out.println(g.toString());
	LinkedQueue small = new LinkedQueue();
	LinkedQueue med = new LinkedQueue();
	LinkedQueue big = new LinkedQueue();
	ListSorts.partition(g, 6, small, med, big);
	System.out.println("Here are the others from small to bigger than 6");
	System.out.println(small.toString());
	System.out.println(med.toString());
	System.out.println(big.toString());
	*/
	
	/*
	LinkedQueue g = new LinkedQueue();
	LinkedQueue b = new LinkedQueue();
	System.out.println("Was able to make queues");
	g.enqueue(1);
	g.enqueue(3);
	g.enqueue(5);
	b.enqueue(2);
	b.enqueue(6);
	b.enqueue(12);
	System.out.println("Was able to insert okay");
	LinkedQueue mergedqueue = ListSorts.mergeSortedQueues(b, g);
	System.out.println(mergedqueue);
	*/

    LinkedQueue q = makeRandom(1000);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    System.out.println("starting quicksort");
    q = makeRandom(1);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
  }

}