package graph;
import list.*;

public class Edge{
	private Object vert1;
	private Object vert2;
	private DListNode edgeShell1;
	private DListNode edgeShell2;
	private boolean visited;
	private int weight;

	public Edge(Vertex vert1, Vertex vert2, int weight){
		this.vert1 = vert1;
		this.vert2 = vert2;
		edgeShell1 = null;
		edgeShell2 = null;
		visited = false;
		this.weight = weight;
	}

	// returns a status on whether the edge has been visited
	public boolean hasVisited(){
		return visited;
	}

	// marks edge as visited
	public void visit(){
		visited = true;
	}

	// returns first vertice edge is connected to
	public Object getVert1(){
		return vert1;
	}

	// returns second vertice edge is connected to
	public Object getVert2(){
		return vert2;
	}

	// returns weight of edge
	public int getWeight(){
		return weight;
	}

	// sets weight of edge
	public void setWeight(int weight){
		this.weight = weight;
	}

	// sets edgeshell pointer to spot in vertex adjacency list
	public void addShell(DListNode n){
		if(edgeShell1 == null){
			edgeShell1 = n;
		}
		else{
			edgeShell2 = n;
		}
	}

	//removes edgeshell from vertex adjacency list
	public void removeShells(){
		if(edgeShell1.getPrev() == null && edgeShell1.getNext() == null){
			edgeShell1.getList().removeFront();
		}
		else if(edgeShell1.getPrev() == null && edgeShell1.getNext() != null){
			edgeShell1.getList().removeFront();
		}
		else if(edgeShell1.getPrev() != null && edgeShell1.getNext() == null){
			edgeShell1.getList().removeBack();
		}
		else if(edgeShell1.getPrev() != null && edgeShell1.getNext() != null){
			edgeShell1.getPrev().setNext(edgeShell1.getNext());
			edgeShell1.getNext().setPrev(edgeShell1.getPrev());
			edgeShell1.getList().decrementSize();
		}
		if(edgeShell2 != null){
			if(edgeShell2.getPrev() == null && edgeShell2.getNext() == null){
				edgeShell2.getList().removeFront();
			}
		
			else if(edgeShell2.getPrev() == null && edgeShell2.getNext() != null){
				edgeShell2.getList().removeFront();
			}
		
			else if(edgeShell2.getPrev() != null && edgeShell2.getNext() == null){
				edgeShell2.getList().removeBack();
			}
		
			else if(edgeShell2.getPrev() != null && edgeShell2.getNext() != null){
				edgeShell2.getPrev().setNext(edgeShell2.getNext());
				edgeShell2.getNext().setPrev(edgeShell2.getPrev());
				edgeShell1.getList().decrementSize();
			}
		}	
	}
}
