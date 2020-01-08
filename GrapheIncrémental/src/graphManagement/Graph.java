package graphManagement;

import java.util.ArrayList;

public class Graph {
	////////////////////////////// Attributes/////////////////////////////////

	/**
	 * matrix of edges of the graph
	 */
	private Matrix matrix;
	/** The list of vertices of the graph */
	private ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
	/** Min and Max values of data for the normalization */
	private ArrayList<Float> listVertexDataMax = new ArrayList<Float>();
	private ArrayList<Float> listVertexDataMin = new ArrayList<Float>();

	/** the id of the column which contains the category in the file */
	private Integer colCategory = null;
	
	private Integer nbVerticesExplore = 0;
	private Integer nbVerticesNearestExact = 0;

	////////////////////////////// Cons///////////////////////////////////////
	/**
	 * build an empty graph
	 */
	public Graph() {
		this.matrix = new Matrix();
		this.listVertex = new ArrayList<Vertex>();
	}

	/**
	 * Builder
	 * 
	 * @param matrix
	 * @param listVertex
	 */
	public Graph(Matrix matrix, ArrayList<Vertex> listVertex) {
		this.matrix = matrix;
		this.listVertex = listVertex;
	}
	////////////////////////////// Getters ///////////////////////////////////

	/**
	 * Getter
	 * @return the column of the category in the file (null if there are no
	 *         category)
	 */
	public Integer getColCategory() {
		return colCategory;
	}

	/**
	 * Setter
	 * @param colCategory the column of the category in the file
	 */
	public void setColCategory(Integer colCategory) {
		this.colCategory = colCategory;
	}
	
	/**
	 * Getter
	 * @return the matrix
	 */
	public Matrix getMatrix() {
		return matrix;
	}

	/**
	 * Getter
	 * @return the listVertex
	 */
	public ArrayList<Vertex> getListVertex() {
		return listVertex;
	}

	/**
	 * Getter
	 * @return the size of the vertices list
	 */
	public int getNbVertices() {
		return listVertex.size();
	}

	/**
	 * Gets a vertex at specific rank from the arrayList listVertex
	 * 
	 * @param rank
	 * @return Vertex stored in the rank passed in parameter
	 */
	public Vertex getVertexFromList(int rank) {
		return listVertex.get(rank);
	}
	
	/**
	 * Getter
	 * @return nbVerticesExplore
	 */
	public int getNbVerticesExplore(){
		return nbVerticesExplore;
	}
	
	/**
	 * Getter
	 * @return nbVerticesNearestExact
	 */
	public int getNbVerticesNearestExact(){
		return nbVerticesNearestExact;
	}

	////////////////////////////// Methods ///////////////////////////////////
	/**
	 * This method adds a vertex in the list "listVertex"
	 * @param vertexToAdd
	 */
	public void addVertex(Vertex vertexToAdd) {
		listVertex.add(vertexToAdd);
	}

	/**
	 * It creates the edge between idVertex1 and idVertex2 if it does not exist
	 * 
	 * @param idVertex1
	 * @param idVertex2
	 * @throws Exception @see Matrix.createEdge
	 */
	public void createEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		matrix.createEdge(idVertex1, idVertex2);
	}

	/**
	 * Gets the edge between idVertex1 and idVertex2
	 * 
	 * @param idVertex1
	 * @param idVertex2
	 * @return edge or null if there are no edge
	 * @throws Exception @see Matrix.getEdges
	 */
	public Edge getEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		return matrix.getEdges(idVertex1, idVertex2);
	}

	/**
	 * This method deletes the edge between 2 vertices
	 * 
	 * @param idVertex1
	 * @param idVertex2
	 * @throws Exception @see Matrix.deleteEdge
	 */
	public void deleteEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		matrix.deleteEdge(idVertex1, idVertex2);
	}

	public Float getVertexDataMax(Integer numData) {
		return listVertexDataMax.get(numData);
	}

	public Float getVertexDataMin(Integer numData) {
		return listVertexDataMin.get(numData);
	}

	/**
	 * It sets newValue at index numData in listVertexDataMax if newValue is greater than
	 * listVertexDataMax[numData]
	 * 
	 * @param numData : the position of data in the list "listVertexDataMax"
	 * @param newValue
	 */
	public void setVertexDataMax(Integer numData, Float newValue) {
		if (numData >= listVertexDataMax.size())
			listVertexDataMax.add(numData, newValue);
		else if (newValue > listVertexDataMax.get(numData))
			listVertexDataMax.set(numData, newValue);
	}

	/**
	 * It sets newValue at index numData in listVertexDataMax if newValue is inferior than
	 * listVertexDataMax[numData]
	 * 
	 * @param numData : the position of data in the list "listVertexDataMin"
	 * @param newValue
	 */
	public void setVertexDataMin(Integer numData, Float newValue) {
		if (numData >= listVertexDataMin.size())
			listVertexDataMin.add(numData, newValue);
		else if (newValue < listVertexDataMin.get(numData))
			listVertexDataMin.set(numData, newValue);
	}

	/**
	 * It returns a list of the vertex's neighbours
	 * @param vertex
	 * @return the list of the "vertex" neighbours
	 */
	public ArrayList<Vertex> getNeighbours(Vertex vertex) {
		ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
		for (Vertex vertexOfList : listVertex) {

			if (matrix.getEdges(vertex.getIdVertex(), vertexOfList.getIdVertex()) != null) {
				neighbours.add(vertexOfList);
			}
		}
		return neighbours;
	}
	
	public void incrementNbVerticesExplore(){
		++nbVerticesExplore;
	}
	
	public void incrementNbVerticesNearestExact(){
		++nbVerticesNearestExact;
	}
}
