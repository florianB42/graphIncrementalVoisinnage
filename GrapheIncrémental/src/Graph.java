import java.util.ArrayList;

public class Graph {

	/**
	 * matrix of edge
	 */
	Matrix matrix; 
    ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
    
    //////////////////////////////Cons/////////////////////////////////
    /**
     * build an empty graph
     */
    public Graph() {
		this.matrix = new Matrix();
		this.listVertex = new ArrayList<Vertex>();
    }
    
	/**
	 * Constructor
	 * @param matrix
	 * @param listVertex
	 */
	public Graph(Matrix matrix, ArrayList<Vertex> listVertex) {
		this.matrix = matrix;
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
	/**
	 * 
	 * @param vertexToAdd
	 */
	public void addVertex(Vertex vertexToAdd) {
		listVertex.add(vertexToAdd);
	}
	
	/**
	 * create edge if edge between idVertex1 and idVertex2 don't exist
	 * @param idVertex1
	 * @param idVertex2
	 * @throws Exception @see Matrix.createEdges
	 */
	public void createEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		matrix.createEdges(idVertex1, idVertex2);
	}
	
	/**
	 * Get edge between idVertex1 and idVertex2
	 * @param idVertex1
	 * @param idVertex2
	 * @return edge or null if they no have edge
	 * @throws Exception @see Matrix.getEdges
	 */
	public Edge getEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		return matrix.getEdges(idVertex1, idVertex2);
	}
}
