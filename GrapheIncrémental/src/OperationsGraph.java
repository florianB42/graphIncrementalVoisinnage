
public class OperationsGraph {
	
	//////////////////////////////Methods///////////////////////////////////
	public void constructRNG(Graph graph) {
//		int compteur = 0;
//		double[] arrayDist = new double[compteur];
		for(int vertexIndex1 = 0; vertexIndex1 < graph.getNbVertices(); vertexIndex1++) {
			for(int vertexIndex2 = vertexIndex1 + 1; vertexIndex2 < graph.getNbVertices(); vertexIndex2++) {
				calculDist(graph.getVertexFromList(vertexIndex1), graph.getVertexFromList(vertexIndex2));
				//compteur += 1;
			}
		}
		//TODO : store results in 2D array ...
	}
	public Vertex findNearest(Vertex vertex, Graph graph) {
		return vertex ;
	}
	
	/**
	 * Calculate the distance between 2 vertices
	 * @param vertex1
	 * @param vertex2
	 * @return the distance
	 */
	public double calculDist(Vertex vertex1, Vertex vertex2) {
		double distance, somme = 0;
		for(int i = 0; i < vertex1.getSizeList(); i++) {
			somme = Math.pow((vertex1.getData(i) - vertex2.getData(i)), 2);
		}
		distance = Math.sqrt(somme);
		return distance;
	}
	
	void extractSubgrah(Graph graph, Vertex nearestVertex) {
		
	}
	void mergeSubgraph(Graph mainGraph, Graph subGraph) {
		
	}
}
