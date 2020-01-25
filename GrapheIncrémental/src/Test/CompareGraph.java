package Test;

import java.util.Map.Entry;

import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.Matrix;
import graphManagement.OperationsGraph;

public class CompareGraph {
	/**
	 * This method takes as parameter two graph with the same vertices and create
	 * two graph: the first have the missing edge of the "graphToCompare" compared
	 * to "referenceGraph" the second have the extra edge of the "graphToCompare"
	 * compared to "referenceGraph"
	 * 
	 * @param referenceGraph
	 * @param graphToCompare
	 */
	public void calculConfusionMatrix(Graph referenceGraph, Graph graphToCompare) {
		OperationsGraph opGraph = new OperationsGraph();
		Edge getedEdge = null;
		Integer correcteEdge = 0;
		Integer missingCorrecteEdge = 0;
		Integer correcteNoEdge = (referenceGraph.getNbVertices()-1) * (referenceGraph.getNbVertices()) / 2;// le nombre max d'arc
		Integer falseEdge = 0;
		
		
		for (Entry<Edge, Boolean> edge : referenceGraph.getMatrix().getHashSet()) {
			try {
				getedEdge = graphToCompare.getEdge(edge.getKey().getIdVertex1(), edge.getKey().getIdVertex2());
				if (getedEdge == null) {
					missingCorrecteEdge++;
				} else {
					correcteEdge++;
				}
			} catch (Exception e) {
				// nothing todo
			}
		}
		for (Entry<Edge, Boolean> edge : graphToCompare.getMatrix().getHashSet()) {
			try {
				getedEdge = referenceGraph.getEdge(edge.getKey().getIdVertex1(), edge.getKey().getIdVertex2());
				if (getedEdge == null) {
					falseEdge++;
				}
			} catch (Exception e) {
				// nothing todo
			}
		}
		correcteNoEdge -= falseEdge;
		correcteNoEdge -= missingCorrecteEdge;
		correcteNoEdge -= correcteEdge;
		System.out.println("Arcs corrects : " + correcteEdge + " | arcs manquant : " + missingCorrecteEdge);
		System.out.println("Arcs faux : " + falseEdge + " | absences normales d'arcs : " + correcteNoEdge);
	}
}
