import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import parser.Parser;

public class OperationsGraph {

	////////////////////////////// Methods///////////////////////////////////
	public void constructRNG(Graph graph) {
		// an array that stores distances between vertices
		double[][] arrayDist = new double[graph.getNbVertices()][graph.getNbVertices()];
		for (int vertexIndex1 = 0; vertexIndex1 < graph.getNbVertices(); vertexIndex1++) {
			for (int vertexIndex2 = vertexIndex1 + 1; vertexIndex2 < graph.getNbVertices(); vertexIndex2++) {
				arrayDist[vertexIndex1][vertexIndex2] = calculDist(graph.getVertexFromList(vertexIndex1),
						graph.getVertexFromList(vertexIndex2));
			}
		}
		// TODO : choose the edge to draw ...

		boolean draw = true;

		for (int vertexIndex1 = 0; vertexIndex1 < graph.getNbVertices(); vertexIndex1++) {
			for (int vertexIndex2 = vertexIndex1 + 1; vertexIndex2 < graph.getNbVertices(); vertexIndex2++) {
				draw = true;
				for (int i = 0; i < graph.getNbVertices(); i++) {
					if (i == vertexIndex1 || i == vertexIndex2) {
						continue;
					}
					if (i > vertexIndex1 && i > vertexIndex2) {
						if (arrayDist[vertexIndex1][vertexIndex2] > arrayDist[vertexIndex1][i]
								&& arrayDist[vertexIndex1][vertexIndex2] > arrayDist[vertexIndex2][i]) {
							draw = false;
							break;
						}
					}
					if (i > vertexIndex1 && i < vertexIndex2) {
						if (arrayDist[vertexIndex1][vertexIndex2] > arrayDist[vertexIndex1][i]
								&& arrayDist[vertexIndex1][vertexIndex2] > arrayDist[i][vertexIndex2]) {
							draw = false;
							break;
						}
					}
					if (i < vertexIndex1 && i < vertexIndex2) {
						if (arrayDist[vertexIndex1][vertexIndex2] > arrayDist[i][vertexIndex1]
								&& arrayDist[vertexIndex1][vertexIndex2] > arrayDist[i][vertexIndex2]) {
							draw = false;
							break;
						}
					}
					if (i < vertexIndex1 && i > vertexIndex2) {
						if (arrayDist[vertexIndex1][vertexIndex2] > arrayDist[i][vertexIndex1]
								&& arrayDist[vertexIndex1][vertexIndex2] > arrayDist[vertexIndex2][i]) {
							draw = false;
							break;
						}
					}

				}
				if (draw) {
					try {
						graph.createEdge(vertexIndex1, vertexIndex2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Vertex findNearest(Vertex vertex, Graph graph) {
		return vertex;
	}

	/**
	 * Calculate the distance between 2 vertices
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @return the distance
	 */
	public double calculDist(Vertex vertex1, Vertex vertex2) {
		double distance, somme = 0;
		for (int i = 0; i < vertex1.getSizeList(); i++) {
			somme += Math.pow((vertex1.getData(i) - vertex2.getData(i)), 2);
		}
		distance = Math.sqrt(somme);
		return distance;
	}

	void extractSubgrah(Graph graph, Vertex nearestVertex) {

	}

	void mergeSubgraph(Graph mainGraph, Graph subGraph) {
		// TODO
	}

	/**
	 * read a new vertex in the file
	 * 
	 * @param parser   the parser who is use to read the file
	 * @param idVertex the id of he new vertex
	 * @return the new vertex
	 */
	public Vertex readNewVertex(Parser parser, Integer idVertex) {
		try {
			Vertex vertex = new Vertex(idVertex);
			if (parser.readLigne() == null) {
				return null;
			} /*
				 * for (int i = 0; i < 4; ++i) { vertex.addData(parser.parserFloat());
				 * parser.parserNext(); } vertex.setCategory(parser.parserString()); return
				 * vertex;
				 */
			do {
				try {
					vertex.addData(parser.parserFloat());
				} catch (NumberFormatException e) {
					vertex.setCategory(parser.parserString());
				}
			} while (parser.parserNext());
			return vertex;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO end line
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * write the graph "graph" in a file with compatible format for tulipe
	 * 
	 * @param graph to write in a file
	 */
	public void writeTulipFile(Graph graph) {
		try {
			File file = new File("D:\\Polytech\\4A-S7\\projet\\graph.TLP");
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("(tlp \"2.3\" \n");
			fileWriter.write("(nb_nodes " + (graph.getNbVertices() + 1) + ")\n");
			fileWriter.write("(nodes 0.." + graph.getNbVertices() + ")\n");
			Integer indexEdge = 0;
			for (Entry<Double, Edge> edge : graph.getMatrix().getHashSet()) {
				fileWriter.write("(edge " + indexEdge + " " + edge.getValue().getIdVertex1() + " "
						+ edge.getValue().getIdVertex2() + ")\n");
				++indexEdge;
			}

			fileWriter.write(")\n");

			fileWriter.close();
		} catch (Exception e) {
		}
	}

}
