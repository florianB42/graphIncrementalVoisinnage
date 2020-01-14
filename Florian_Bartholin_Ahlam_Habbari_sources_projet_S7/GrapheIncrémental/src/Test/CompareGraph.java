package Test;

import java.util.Map.Entry;

import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.Matrix;
import graphManagement.OperationsGraph;

public class CompareGraph {
	/**
	 * This method takes as parameter two graph with the same vertices and create two graph:
	 * the first have the missing edge of the "graphToCompare" compared to "referenceGraph"
	 * the second have the extra edge of the "graphToCompare" compared to "referenceGraph"
	 * @param referenceGraph
	 * @param graphToCompare
	 */
	public void createDifferenceGraph(Graph referenceGraph, Graph graphToCompare, String fileNameMissingGraph,String fileNameExtraGraph) {
		Graph missingGraph = new Graph(new Matrix(referenceGraph.getMatrix()),referenceGraph.getListVertex());
		Graph extraGraph = new Graph(new Matrix(graphToCompare.getMatrix()),referenceGraph.getListVertex());
		OperationsGraph opGraph = new OperationsGraph();
		
		for(Entry<Edge,Boolean> edge : graphToCompare.getMatrix().getHashSet()) {
			try {
				missingGraph.deleteEdge(edge.getKey().getIdVertex1(), edge.getKey().getIdVertex2());
			} catch (Exception e) {
				// nothing todo
			}
		}
		opGraph.writeTulipFile(missingGraph, fileNameMissingGraph);
		
		for(Entry<Edge,Boolean> edge : referenceGraph.getMatrix().getHashSet()) {
			try {
				extraGraph.deleteEdge(edge.getKey().getIdVertex1(), edge.getKey().getIdVertex2());
			} catch (Exception e) {
				// nothing todo
			}
		}
		opGraph.writeTulipFile(extraGraph, fileNameExtraGraph);
	}
}
