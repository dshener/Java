/* Kruskal.java */

package graphalg;

import list.*;
import dict.*;
import graph.*;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g) {
	 /* STEP 1: CREATE NEW EMPTY GRAPH WITH SAME VERTICES BUT
	  * NO EDGES
	  */
	  WUGraph newgraph = new WUGraph();
	  Object[] verticesarray = g.getVertices();
	  for (int i = 0; i < verticesarray.length; i++) {
		  newgraph.addVertex(verticesarray[i]);
	  }
	  
	/* STEP 2: MAKE A LIST OF ALL THE EDGES
	 */
	  DList edgelist = new DList();
	  Neighbors[] neighborarray = new Neighbors[verticesarray.length];
	  for (int i = 0; i < verticesarray.length; i++) {
		  neighborarray[i] = g.getNeighbors(verticesarray[i]);
	  }
	  for (int i = 0; i < verticesarray.length; i++) {
		  for (int j = 0; j < neighborarray[i].neighborList.length; j++) {
			  Object[] edge = new Object[3];
			  edge[0] = verticesarray[i];
			  edge[1] = neighborarray[i].neighborList[j];
			  edge[2] = neighborarray[i].weightList[j];
			  edgelist.insertFront(edge);
		  }
	  }
	  /* STEP 3: SORT THE EDGE LIST
	   * */
	  LinkedQueue sortqueue = new LinkedQueue();
	  DListNode currentnode = edgelist.front(); 
	  while (currentnode != null) {
		  Object[] comparearray = (Object[])currentnode.item;
		  sortqueue.enqueue(comparearray);
		  currentnode = currentnode.getNext();  
	  }
	  ListSorts.edgeQuickSort(sortqueue);
	  
	  /* STEP 4: USE DISJOINT SETS TO ADD TO NEWGRAPH
	   * 
	   */
	  HashTableChained objectht = new HashTableChained();
	  DisjointSets disjointvert = new DisjointSets(verticesarray.length);
	  for (int i = 0; i < verticesarray.length; i++) {
		  objectht.insert(verticesarray[i], i);  
	  }
	  while (!sortqueue.isEmpty()) {
		  Object[] currentedge;
		  try {
			  currentedge = (Object[])sortqueue.dequeue();
			  Object object1 = (Object)currentedge[0];
			  Object object2 = (Object)currentedge[1];
			  int weight = (int)currentedge[2];
			  int obj1int = (int)(objectht.find(object1)).value();
			  int obj2int = (int)(objectht.find(object2)).value();
			  if (disjointvert.find(obj1int) == disjointvert.find(obj2int)) {
				  continue;
			  } else {
				  newgraph.addEdge(object1, object2, weight);
				  disjointvert.union(disjointvert.find(obj1int), disjointvert.find(obj2int));
			  }
		  } catch (QueueEmptyException e1) {
			  System.out.println("error in Disjoint set part");
		  }
	  }
	  return newgraph;  
  }

}

