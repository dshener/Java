
//Vertex class, represents a vertex on the WUGraph
package graph;
import list.*;
public class Vertex{

	//The object the vertex holds/represents
	private Object item;
	
	//The indegree and outdegree of the vertex
	private int degree;
	
	//Holds true if this vertex is a valid part of the WUGraph
	private boolean valid;
	
	/*Points to the node that where this vertex is represented 
	 * inside a doubly-linked list in the WUGraph */
	private DListNode vertexShell;
	
	//Doubly linked list of all the edges this vertex has
	private DList edgesList;
	
	public Vertex(){
		item = null;
		degree = 0;
		valid = true;
		edgesList = new DList();
	}
	
	public Vertex(Object key){
		this();
		item = key;
	}
	
	//Returns the Vertex's object
	public Object getItem(){
		return item;
	}
	
	//Returns true if this vertex is valid
	public boolean isValid(){
		return valid;
	}
	
	//Returns the degree of the vertex
	public int getDegree(){
		return degree;
	}
	
	//Decrements the degree of the vertex by one
	public void decrementDegree(){
		if(degree > 0){
			degree--;
		}
	}
	
	//Returns the DListNode that holds the vertex
	public DListNode getShell(){
		return vertexShell;
	}
	
	//Returns the DList of edges
	public DList getEdges(){
		return edgesList;
	}

	//Adds an edge to the list of edges connected to this vertex
	public void insertEdge(Edge e){
		edgesList.insertBack(e);
		e.addShell(edgesList.getTail());
		degree++;
	}
	
	public void setValidity(boolean truth){
		valid = truth;
	}
}
	
	
	
	
	
	
	
	