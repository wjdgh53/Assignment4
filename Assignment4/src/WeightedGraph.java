import Interface.LinkedUnbndQueue;
import Interface.UnboundedQueueInterface;

// Incomplete version

//----------------------------------------------------------------------------
// WeightedGraph.java            by Dale/Joyce/Weems                 Chapter 9
//
// Implements a directed graph with weighted edges.
// Vertices are objects of class T and can be marked as having been visited.
// Edge weights are integers.
// Equivalence of vertices is determined by the vertices' equals method.
//
// General precondition: Except for the addVertex and hasVertex methods, 
// any vertex passed as an argument to a method is in this graph.
//----------------------------------------------------------------------------



public class WeightedGraph<T> implements WeightedGraphInterface<T>
{
  public static final int NULL_EDGE = 0;
  private static final int DEFCAP = 50;  // default capacity
  private int numVertices;
  private int maxVertices;
  private T[] vertices;
  private int[][] edges;
  private boolean[] marks;  // marks[i] is mark for vertices[i]

  public WeightedGraph()
  // Instantiates a graph with capacity DEFCAP vertices.
  {
    numVertices = 0;
    maxVertices = DEFCAP;
    vertices = (T[]) new Object[DEFCAP];
    marks = new boolean[DEFCAP];
    edges = new int[DEFCAP][DEFCAP];
  }
 
  public WeightedGraph(int maxV)
  // Instantiates a graph with capacity maxV.
  {
    numVertices = 0;
    maxVertices = maxV;
    vertices = (T[]) new Object[maxV];
    marks = new boolean[maxV];
    edges = new int[maxV][maxV];
  }

  public boolean isEmpty()
  // Returns true if this graph is empty; otherwise, returns false.
  {
	  return numVertices<=0;
  }

  public boolean isFull()
  // Returns true if this graph is full; otherwise, returns false.
  {
	  return numVertices > maxVertices;
  }

  public void addVertex(T vertex)
  // Preconditions:   This graph is not full.
  //                  Vertex is not already in this graph.
  //                  Vertex is not null.
  //
  // Adds vertex to this graph.
  {
	  if(!isFull()){
		  vertices[numVertices] = vertex;
		  for (int index = 0; index < numVertices; index++)
		  {
			  edges[numVertices][index] = NULL_EDGE;
			  edges[index][numVertices] = NULL_EDGE;
		  }
		  numVertices++;
	  }else{
		  System.out.println("full! no more!!");
	  }
  }

  public boolean hasVertex(T vertex)
  // Returns true if this graph contains vertex; otherwise, returns false.
  {
	  for(int i = 0; i <= numVertices; i++)
	  {
		  if(vertices[i].equals(vertex))
		  {
			  return true;
		  }
	  }
	  return false;
  }
  
  private int indexIs(T vertex)
  // Returns the index of vertex in vertices.
  {
    int index = 0;
    while (!vertex.equals(vertices[index]))
      index++;
    return index;
  }

  public void addEdge(T fromVertex, T toVertex, int weight)
  // Adds an edge with the specified weight from fromVertex to toVertex.
  {
    int row;
    int column;
 
    row = indexIs(fromVertex);
    column = indexIs(toVertex);
    edges[row][column] = weight;
  }

  public int weightIs(T fromVertex, T toVertex)
  // If edge from fromVertex to toVertex exists, returns the weight of edge;
  // otherwise, returns a special �null-edge� value.
  {
    int row;
    int column;
 
    row = indexIs(fromVertex);
    column = indexIs(toVertex);
    return edges[row][column];
  }

  public UnboundedQueueInterface<T> getToVertices(T vertex)
  // Returns a queue of the vertices that are adjacent from vertex.
  {
    UnboundedQueueInterface<T> adjVertices = new LinkedUnbndQueue<T>();
    int fromIndex;
    int toIndex;
    fromIndex = indexIs(vertex);
    for (toIndex = 0; toIndex < numVertices; toIndex++)
      if (edges[fromIndex][toIndex] != NULL_EDGE)
        adjVertices.enqueue(vertices[toIndex]);
    return adjVertices;
  }

  public void clearMarks()
  // Sets marks for all vertices to false.
  {
	  for(int i = 0; i <= numVertices; i++)
	  {
		  marks[i] = false;
	  }
  }

  public void markVertex(T vertex)
  // Sets mark for vertex to true.
  {
	  for(int i = 0; i < numVertices; i++)
	  {
		  if(vertices[i].equals(vertex))
		  {
			  marks[i] = true;
		  }
	  }
  }

  public boolean isMarked(T vertex)
  // Returns true if vertex is marked; otherwise, returns false.
  {
	  for(int i = 0; i < numVertices; i++)
	  {
		  if(vertices[i].equals(vertex))
		  {
			  return marks[i];
		  }
	  }
	  return false;
  }
  

  public T getUnmarked()
  // Returns an unmarked vertex if any exist; otherwise, returns null.
  {
	  for(int i=0; i < numVertices; i++)
	  {
		  if(!marks[i])
		  {
			  return vertices[i];
		  }		  
	  }
	  return null;
  }
}
