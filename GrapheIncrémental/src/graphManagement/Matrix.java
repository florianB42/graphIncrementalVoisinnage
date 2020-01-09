package graphManagement;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Matrix {
	
	public Matrix() {
		
	}
	
	public Matrix(Matrix matrixToCopy) {
		matrix.putAll(matrixToCopy.matrix);
	}
	/**
	 * Hashmap that stores the graph edges
	 */
	private HashMap<Edge, Boolean> matrix = new HashMap<Edge, Boolean>();

	/**
	 * Getter of the edge linking the vertices in the parameters.
	 * @param idVertex1
	 * @param idVertex2
	 * @return the edge if it exists and null if not.
	 */
	public Edge getEdges(Integer idVertex1, Integer idVertex2) {
		Edge edge;
		if (idVertex1 < idVertex2) {
			edge = new Edge(idVertex1, idVertex2);
		} else if (idVertex1 > idVertex2) {
			edge = new Edge(idVertex2, idVertex1);
		} else
			return null;
		
		if (matrix.containsKey(edge))
			return edge;
		else
			return null;

	}

	/**
	 * This method creates an edge between both vertices whom ids are 
	 * passed in parameters.
	 * @param idVertex1
	 * @param idVertex2
	 * @throws Exception when both ids in the parameters are the same.
	 */
	public void createEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		Edge edge;
		if (idVertex1 < idVertex2) {
			edge = new Edge(idVertex1, idVertex2);
		} else if (idVertex1 > idVertex2) {
			edge = new Edge(idVertex2, idVertex1);
		} else
			throw new Exception("Matrix_IllegalEdgeCreate");
		matrix.put(edge, true);
	}

	/**
	 * This method deletes the edge linking the vertices whom ids are passed in parameters.
	 * @param idVertex1
	 * @param idVertex2
	 * @throws Exception when both ids in the parameters are the same.
	 */
	public void deleteEdge(Integer idVertex1, Integer idVertex2) throws Exception {
		Edge edge;
		if (idVertex1 < idVertex2) {
			edge = new Edge(idVertex1, idVertex2);
		} else if (idVertex1 > idVertex2) {
			edge = new Edge(idVertex2, idVertex1);
		} else
			throw new Exception("Matrix_IllegalEdgeDelete");
		if (matrix.containsKey(edge))
			matrix.remove(edge);
	}

	/**
	 * Getter
	 * @return the enrySet of the Hashmap matrix
	 */
	public Set<Entry<Edge, Boolean>> getHashSet() {
		return matrix.entrySet();
	}
}

