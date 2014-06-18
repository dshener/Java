/* WUGraph.java */

package graph;
import list.*;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  public int vertexNum;
  public int edgeNum;
  public HashTableChained edges;
  public HashTableChained vertices;
  public DList vertexList;
  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
    vertexNum = 0;
    edgeNum = 0;
    edges = new HashTableChained(1);
    vertices = new HashTableChained(1);
    vertexList = new DList();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
    return vertexNum;
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount(){
    return edgeNum;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
    Object[] vertices = new Object[vertexNum];
    if(vertexNum == 0){
      return vertices;
    }
    DListNode node = vertexList.getHead();
    int counter = 0;
    while(node != null){
      if(((Vertex)node.getItem()).isValid()){
        vertices[counter] = ((Vertex)node.getItem()).getItem();
        counter++;
      }
      node = node.getNext();
    }
    return vertices;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){
    if(vertices.find(vertex) != null){
      return;
    } else {
      Vertex v = new Vertex(vertex);
      vertexList.insertBack(v);
      vertices.insert(vertex, v);
    }
    vertexNum++;
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
    if(vertices.find(vertex) == null){
      return;
    }
    Vertex v = (Vertex)vertices.find(vertex).value();
    if(v.isValid()){
      v.setValidity(false);
      DList edges = v.getEdges();
      DListNode node = edges.getHead();

      while(node != null){
        removeEdge(((Vertex)((Edge)node.getItem()).getVert1()).getItem(), ((Vertex)((Edge)node.getItem()).getVert2()).getItem());
        node = node.getNext();
      }
      vertices.remove(vertex);
      vertexNum--;
    }

  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
    if(vertices.find(vertex) != null && ((Vertex)vertices.find(vertex).value()).isValid()){
      return true;
    }else{
      return false;
    }
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex){
    if(vertices.find(vertex) == null || (!(((Vertex)vertices.find(vertex).value()).isValid()))){
      return 0;
    } else {
      return ((Vertex)vertices.find(vertex).value()).getDegree();
    }
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex){
    int d = degree(vertex);
    if(!(isVertex(vertex)) || d == 0){
      return null;
    }

    Neighbors neighbors = new Neighbors();
    neighbors.neighborList = new Object[d];
    neighbors.weightList = new int[d];
    Vertex v = (Vertex)vertices.find(vertex).value();
    DList edges = v.getEdges();
    DListNode node = (DListNode) edges.front();
    for(int i = 0; i <= edges.length() - 1; i++){
      Edge edge = (Edge)node.getItem();
      if(v.equals(edge.getVert1())){
        neighbors.neighborList[i] = ((Vertex)edge.getVert2()).getItem();
      }else{
        neighbors.neighborList[i] = ((Vertex)edge.getVert1()).getItem();
      }
      neighbors.weightList[i] = edge.getWeight();
      node = (DListNode) node.getNext();
    }
    return neighbors;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight){
    if(!(isVertex(u) && isVertex(v))){
      return;
    }
    Vertex v1 = (Vertex)vertices.find(u).value();
    Vertex v2 = (Vertex)vertices.find(v).value();
    Edge edge = new Edge(v1, v2, weight);
    VertexPair vPair = new VertexPair(u,v);
    if(edges.find(vPair) != null){
      ((Edge)edges.find(vPair).value()).setWeight(weight);
    }
    else{
      edges.insert(vPair,edge);
      if(!u.equals(v)){
        v1.insertEdge(edge);
      }
      v2.insertEdge(edge);
      edgeNum++;
    }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v){
    VertexPair vPair = new VertexPair(u,v);
    if(edges.find(vPair) == null){
      return;
    }
    else{
      Edge rEdge = (Edge)edges.find(vPair).value();
      edges.remove(vPair);
      rEdge.removeShells();
      ((Vertex)vertices.find(u).value()).decrementDegree();
      ((Vertex)vertices.find(v).value()).decrementDegree();
      edgeNum--;
    }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v){
    if(isVertex(v) && isVertex(u) && edges.find(new VertexPair(u,v)) != null){
      return true;
    }
    return false;
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v){
    VertexPair vPair = new VertexPair(u,v);
    if(edges.find(vPair) == null){
      return 0;
    }
    else{
      return ((Edge)edges.find(vPair).value()).getWeight();
    }
  }

}
