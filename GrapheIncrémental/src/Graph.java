import java.util.ArrayList;

public class Graph {

	/**
	 * matrix of edge
	 */
	private Matrix matrix; 
	private ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
	private ArrayList<Float> listVertexDataMax = new ArrayList<Float>();
	private ArrayList<Float> listVertexDataMin = new ArrayList<Float>();
    
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
	
	public Float getVertexDataMax(Integer numData) {
		return listVertexDataMax.get(numData);
	}
	
	public Float getVertexDataMin(Integer numData) {
		return listVertexDataMin.get(numData);
	}
	
	/**
	 * set newValue at index numData in listVertexDataMax if newValue > listVertexDataMax[numData]
	 * @param numData
	 * @param newValue
	 */
	public void setVertexDataMax(Integer numData, Float newValue) {
		if (numData >= listVertexDataMax.size())
			listVertexDataMax.add(numData, newValue);	
		else if (newValue > listVertexDataMax.get(numData))
			listVertexDataMax.set(numData, newValue);
	}
	
	/**
	 * set newValue at index numData in listVertexDataMax if newValue < listVertexDataMax[numData]
	 * @param numData
	 * @param newValue
	 */
	public void setVertexDataMin(Integer numData, Float newValue) {
		if (numData >= listVertexDataMin.size())
			listVertexDataMin.add(numData, newValue);	
		else if (newValue < listVertexDataMin.get(numData))
			listVertexDataMin.set(numData, newValue);	
	}
	
	public ArrayList<Vertex> getNeighbour(Vertex vertex) {
		ArrayList<Vertex> neighbour = new ArrayList<Vertex>();
		for (Vertex vertexOfList : listVertex) {
			if (matrix.getEdges(vertex.getIdVertex(), vertexOfList.getIdVertex()) != null)
				neighbour.add(vertexOfList);
		}
		return neighbour;
	}
}
