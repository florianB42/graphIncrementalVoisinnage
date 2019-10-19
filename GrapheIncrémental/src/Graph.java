import java.util.ArrayList;

public class Graph {

	Matrix matrix; 
    ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
    
    //////////////////////////////Cons/////////////////////////////////
	/**
	 * Constructor
	 * @param matrix
	 * @param listVertex
	 */
	public Graph(Matrix matrix, ArrayList<Vertex> listVertex) {
		this.matrix = new Matrix();
		this.listVertex = listVertex;
	}
	////////////////////////////// Getters ///////////////////////////////////
	
	/**
	 * @return the matrix
	 */
	public Matrix getMatrix() {
		return matrix;
	}

	/**
	 * @return the listVertex
	 */
	public ArrayList<Vertex> getListVertex() {
		return listVertex;
	}
	
	/**
	 * @return the size of the vertices list
	 */
	public int getNbVertices() {
		return listVertex.size();
	}
	
	/**
	 * Gets Vertex from the arrayList listVertex
	 * @param rank
	 * @return Vertex stored in the rank passed in parameter
	 */
	public Vertex getVertexFromList(int rank) {
		return listVertex.get(rank);
	}
	
	////////////////////////////// Methods ///////////////////////////////////
	public void addVertex(Vertex vertexToAdd) {
		listVertex.add(vertexToAdd);
	}
}
