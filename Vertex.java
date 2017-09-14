import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Representation of a graph vertex
 */
public class Vertex {
	private final String label;   
	private int cost;	
	private Vertex path;
	
	/**
	 * Constructs a new vertex
	 * @param label the label attached to this vertex
	 */
	
	public Vertex(String label) {
		this(label,Integer.MAX_VALUE, null);
	}
	
	/**
	 * Constructs a new vertex
	 * @param label the label attached to this vertex
	 * @param cost is the tentative cost at that vertex
	 * @param path helps determine the path that it took to get to that vertex
	 */
	
	public Vertex(String label,int cost, Vertex path) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.cost = cost;
		this.path = path;
	}
	

	/**
	 * 
	 * @return the cost at that vertex object
	 */
	public int getCost(){
		return this.cost;
	}
	
	/**
	 * @param c this takes in an int value to set the cost for the vertex object
	 */
	public void setCost(int c){
		this.cost = c;
	}
	
	/**
	 * 
	 * @param path takes in a Vertex path value to set the path of the vertex object
	 */
	public void setPath(Vertex path){
		this.path = path;
	}
	
	/**
	 * 
	 * @return the vertex path value for this vertex object
	 */
	public Vertex getPath(){
		return this.path;
	}
	
	/**(This comment cannot be seen by the client only for TA since private method)
	 * This is used to help get the list of vertices in the getPath function
	 * @param v is the vertex object that is added
	 * @param s is the data structure that holds the actual path backwards
	 * (if you pop everything it will be the correct order) 
	 */
	private void getVertexList(Vertex v, Stack<Vertex> s){
		if(v.path != null){
			s.add(v.path);
			this.path = v.path;
			getVertexList(this.path,s);
		}
	}
	/**This returns a list of vertices that represents the full path of the vertex object
	 * to its current position
	 * 
	 * @param beginning is where the vertex object starts
	 * @param end is where the vertex object final destination is 
	 * @return This returns a list of vertices for the path of this vertex object
	 */
	public List<Vertex> getPath(Vertex beginning, Vertex end){
		Stack<Vertex> pathReveresed = new Stack<Vertex>();
		getVertexList(end, pathReveresed);
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		while (!pathReveresed.isEmpty()) {
			list.add(new Vertex(pathReveresed.pop().getLabel()));
		}
		list.add(new Vertex(end.getLabel()));
		
		return list;
	}

	
	

	


	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	// Auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	// Auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
            return other.label == null;
		} else {
		    return label.equals(other.label);
		}
	}


}
