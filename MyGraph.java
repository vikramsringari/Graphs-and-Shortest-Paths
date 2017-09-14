import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 * This class has a vertices function to get all vertices.
 * This class has an edges function to get all edges.
 * This class has adjacentvertices function to get all adjacent vertices
 * This class has edgeCost function to get the cost of an edge between vertices
 * This class has a shortestPath function to get the shortesPath between two vertices  
 */
public class MyGraph implements Graph {
	private final Map<Vertex, Set<Edge>> partialGraph;
    private final Set<Edge> allEdges;

	/**
	 * This Creates the MyGraph object with the given collection of vertices and 
     * and the given collection of edges. These vertices an edges and are 
     * organized into a graph like structure that connects edges between 
     * vertices.
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		partialGraph = new HashMap<Vertex, Set<Edge>>();
		allEdges = new HashSet<Edge>();
		for (Vertex verticies : v) {
			if (find(verticies)==null) {
				partialGraph.put(new Vertex(verticies.getLabel()), new HashSet<Edge>());
			}
		}
		for (Edge edges : e) {
			if (edges.getWeight() > 0) {
				for (Edge edgesFromSoure : partialGraph.get(edges.getSource())) {
					if (edgesFromSoure.getDestination().equals(edges.getDestination()) && edgesFromSoure.getWeight() != edges.getWeight()) {
						throw new IllegalArgumentException();
					}
				}
				partialGraph.get(edges.getSource()).add(new Edge(find(edges.getSource()),find(edges.getDestination()), edges.getWeight()));
				allEdges.add(new Edge(edges.getSource(), edges.getDestination(), edges.getWeight()));
			}
		}	
	}

	/**
	 * This function returns the entire collection of vertices that make this graph
	 * @return the vertices as a collection (which is anything iterable)
	 */
	public Collection<Vertex> vertices() {
		return partialGraph.keySet();
	}

	/**
	 * This returns the entire collection of edges that makes this graph
	 * @return the edges as a collection (which is anything iterable)
	 */
	public Collection<Edge> edges() {
		return this.allEdges;
	}

	/**
	 * This returns a collection of vertices connected to a given the Vertex v.
	 * This means the set of all vertices w where edges that connect v to another 
	 * like this : vertex v -> w exist in the graph. This returns as an
	 * empty collection if there are no adjacent vertices
	 * @param v is one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException if v does not exist.
	 */
	public Collection<Vertex> adjacentVertices(Vertex v) {
		if (find(v)==null) {
			throw new IllegalArgumentException();
		}
		List<Vertex> verticies = new ArrayList<Vertex>();
		for (Edge edge : partialGraph.get(v)) {
			verticies.add(new Vertex(edge.getDestination().getLabel()));
		}
		return verticies;
	}

	/**
	 * This returns the cost of the edge that connects vertex a to vertex b 
	 * like this : a -> b, (in this direction specifically) assumming there 
	 * is an edge between them in the graph. 
	 * This assumes that we do not have negative cost edges in the graph.
	 * @param a is the fires vertex
	 * @param b is the second vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException if a or b do not exist.
	 */
	public int edgeCost(Vertex a, Vertex b) {
		
		vertexChecker(a,b);
		
		for (Edge edge : partialGraph.get(a)) {
			if (edge.getDestination().equals(b)) {
				return edge.getWeight();
			}
		}
		return -1;
	}

	/**
	 * This returns the shortest path from vertex a to vertex b in the graph
	 * This will return  null if there if there is no such path.
	 * This assumes all edge weights are nonnegative. 
	 * @param a is the starting vertex
	 * @param b is the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	
	public Path shortestPath(Vertex a, Vertex b) {
		
		vertexChecker(a,b);
		
		if (a.equals(b)) {
			List<Vertex> selfPath = new ArrayList<Vertex>();
			selfPath.add(a);
			return new Path(selfPath, 0);
		}
		List<Vertex> unknown = new ArrayList<Vertex>();
		for (Vertex v : partialGraph.keySet()) {
			v.setCost(Integer.MAX_VALUE);
			v.setPath(null);
			unknown.add(v);
		}

		Vertex beginning = a;
		beginning.setCost(0);

		Vertex end = shortestPathHelper(beginning, find(b), unknown);
		if (end == null) {
			return null;
		}
		int endCost = end.getCost();

		List<Vertex> list = end.getPath(beginning, end);
		

		
		return new Path(list, endCost);
	}
	
	/** (This comment cannot be seen by the client only for TA since private method) 
	 * This Helper Uses Dijkstra's algorithm to get from the initial vertex to the final vertex 
	 * in the graph. This returns that vertex, which includes all the cost and path information.
	 * This recurses through the graph and sets the most cost effective path to traverse with each 
	 * recursive call.
	 * @param currentVertex
	 * 			This is the current vertex that this recursive function bases on where to (based on 
	 * 			connecting vertecies) to go to next, this is the variable that changes with each call.
	 * @param endingVertex
	 * 			This is the end goal, when the currentVertex is the same as the ending Vertex the base
	 *			case has been met and the path and cost to get to that position is return as a vertex
	 * @param unknown
	 * 			This is the list of unknown (or nontraveresed) Vertecies in the graph and is used to 
	 * 			dettermine what path to go to next since each new direction this function traverses
	 * 			is based on going to the lowest cost unknown vertex
	 * @return
	 * 			This returns a vertex with cost and path information it took to get to the destination
	 */
	private Vertex shortestPathHelper(Vertex currentVertex, Vertex endingVertex, List<Vertex> unknown) {
				if (unknown.isEmpty() || currentVertex == endingVertex) {
					return new Vertex(currentVertex.getLabel(),currentVertex.getCost(),currentVertex.getPath());
				}
				if (currentVertex == null) {
					return null;
				}
				unknown.remove(currentVertex);

				for (Edge edge : partialGraph.get(currentVertex)) {

					if (currentVertex.getCost() + edge.getWeight() < edge.getDestination().getCost()) {
						edge.getDestination().setCost(currentVertex.getCost() + edge.getWeight());
						edge.getDestination().setPath(currentVertex);
					}
				}
				
				int smallCost = Integer.MAX_VALUE;
				Vertex cheapestPath = null;
				
				for (Vertex vertex : unknown) {
					if (vertex.getCost() < smallCost) {
						smallCost = vertex.getCost();
						cheapestPath = vertex;
					}
				}

				return shortestPathHelper(cheapestPath, endingVertex, unknown);
		
	}

	/**(This comment cannot be seen by the client only for TA since private method)
	 * This returns the vertex it is looking for based on if it is in the graph
	 * @param v is the vertex that is checked if it is in the graph 
	 * @return a vertex if it is in the graph, else it returns null
	 * 			
	 */		
	private Vertex find(Vertex v) {
		for (Vertex vertex : partialGraph.keySet()) {
			if (vertex.equals(v)) {
				return vertex;
			}
		}

		return null;
	}
	
	/***(This comment cannot be seen by the client only for TA since private method)
	 * This takes two Vertecies (beginning and end) and checks if they are in the graph
	 * @param a is the beginning vertex
	 * @param b is the end vertex
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	private void vertexChecker(Vertex a, Vertex b){
		if (find(a)==null || find(b)==null) {
			throw new IllegalArgumentException();
		}
	}

}