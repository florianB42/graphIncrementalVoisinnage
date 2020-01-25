package graphManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import parser.Parser;

public class OperationsGraph {

	////////////////////////////// Methods///////////////////////////////////
	/**
	 * The implementation of the RNG algorithm This method build the RNG graph using
	 * a graph without edges
	 * 
	 * @param graph : a graph without edges
	 */
	public void constructRNG(Graph graph) {
		// an array that stores distances between vertices
		double[][] arrayDist = new double[graph.getNbVertices()][graph.getNbVertices()];
		for (int vertexIndex1 = 0; vertexIndex1 < graph.getNbVertices(); vertexIndex1++) {
			for (int vertexIndex2 = vertexIndex1 + 1; vertexIndex2 < graph.getNbVertices(); vertexIndex2++) {
				arrayDist[vertexIndex1][vertexIndex2] = calculDist(graph.getVertexFromList(vertexIndex1),
						graph.getVertexFromList(vertexIndex2));
			}
		}

		// choose the edge to draw ...
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
						graph.createEdge(graph.getVertexFromList(vertexIndex1).getIdVertex(),
								graph.getVertexFromList(vertexIndex2).getIdVertex());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * This method search the nearest vertex from "newVertex" by exploring all the
	 * vertices
	 * 
	 * @param newVertex : we want to find the nearest vertex from the new one
	 * @param graph     : the graph where we seek the nearest vertex from the new
	 *                  one
	 * @return the nearest vertex from "newVertex"
	 */
	public Vertex findNearestFullExplor(Vertex newVertex, Graph graph) {
		double MinDist = Double.MAX_VALUE;
		Vertex nearestVertex = null;
		for (Vertex vertexLoop : graph.getListVertex()) {
			if (calculDist(newVertex, vertexLoop) < MinDist) {
				MinDist = calculDist(newVertex, vertexLoop);
				nearestVertex = vertexLoop;
			}
		}
		return nearestVertex;
	}

	/**
	 * This method search the nearest vertex from the new vertex to add, by
	 * exploring only the neighbour's of a vertex.
	 * 
	 * We start by choosing a random vertex of the graph, then we look for the
	 * nearest vertex of this random vertex among its neighbors, and do the same
	 * thing with the selected vertex until we find no nearest vertex than the
	 * selected one.
	 * 
	 * @param newVertex : we want to find the nearest vertex from the new one
	 * @param graph     : the graph where we seek the nearest vertex from the new
	 *                  one
	 * @return the nearest vertex from "newVertex"
	 */
	public Vertex findNearest(Vertex newVertex, Graph graph) {
		int randomIndex = (int) (Math.random() * (graph.getListVertex().size() - 1));
		Vertex nearestVertex = graph.getListVertex().get(randomIndex); // a random vertex of the graph
		double MinDist = Double.MAX_VALUE;
		Vertex currentVertex = null;
		while (nearestVertex != currentVertex) {
			currentVertex = nearestVertex;
			ArrayList<Vertex> listOfNeighbours = graph.getNeighbours(currentVertex);
			listOfNeighbours.add(currentVertex);
			for (Vertex vertexLoop : listOfNeighbours) {
				graph.incrementNbVerticesExplore();
				if (calculDist(newVertex, vertexLoop) < MinDist) {
					MinDist = calculDist(newVertex, vertexLoop);
					nearestVertex = vertexLoop;
				}
			}
		}
		return nearestVertex;
	}

	/**
	 * Calculate the distance between 2 vertices
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @return the distance between "vertex1" and "vertex2"
	 */
	public double calculDist(Vertex vertex1, Vertex vertex2) {
		double distance, somme = 0;
		for (int i = 0; i < vertex1.getSizeList(); i++) {
			somme += Math.pow((vertex1.getData(i) - vertex2.getData(i)), 2);
		}
		distance = Math.sqrt(somme);
		return distance;
	}

	/**
	 * This method extract the subgraph from the main graph. It uses the nearest
	 * vertex from the new one, and select its neighbors depending on the
	 * parameter"nNeighboring". It selects at least 10 vertices.
	 * 
	 * @param graph         : the graph from which we will extract the subgraph.
	 * @param nearestVertex : the vertex from which the extraction begin.
	 * @return subGraph : the subGraph extracted from "graph".
	 */
	public Graph extractSubgraph(Graph graph, Vertex nearestVertex) {
		Graph subGraph = new Graph();
		Integer nNeighboring = 2;
		ArrayList<Vertex> neighbour = new ArrayList<Vertex>();
		ArrayList<Vertex> newNeighbour;
		neighbour.add(nearestVertex);
		int i = 0;
		while ((i < nNeighboring || subGraph.getListVertex().size() < 10)
				&& (subGraph.getListVertex().size() < graph.getNbVertices())) {
			newNeighbour = new ArrayList<Vertex>();
			for (Vertex vertex : neighbour) {
				newNeighbour.addAll(graph.getNeighbours(vertex));
				if (!subGraph.getListVertex().contains(vertex))
					subGraph.addVertex(vertex);
			}
			neighbour = newNeighbour;
			++i;
		}

		for (Vertex vertex : neighbour) {
			if (!subGraph.getListVertex().contains(vertex))
				subGraph.addVertex(vertex);
		}
		return subGraph;
	}

	/**
	 * This method merges the subgraph but doesn't adds any vertex. All vertices of
	 * the subgraph need to be in the main graph before using this method.
	 * 
	 * @param mainGraph : the main graph from which we extracted the subgraph.
	 * @param subGraph  : the subgraph to merge with the main graph
	 */
	public void mergeSubgraph(Graph mainGraph, Graph subGraph) {
		for (Vertex vertex1 : subGraph.getListVertex()) {
			for (Vertex vertex2 : subGraph.getListVertex()) {
				try {
					/*
					 * we delete from the main graph the old edges whom vertices are in common with
					 * the subgraph vertices
					 */
					mainGraph.deleteEdge(vertex1.getIdVertex(), vertex2.getIdVertex());
				} catch (Exception e) {
					// Nothing to do
				}
			}
		}
		for (Entry<Edge, Boolean> edge : subGraph.getMatrix().getHashSet()) {
			try {
				// we copy the same edges of the subgraph in the main graph
				mainGraph.createEdge(edge.getKey().getIdVertex1(), edge.getKey().getIdVertex2());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method reads all the vertices in the file to find the max and the min
	 * values of a particular dimension.
	 * 
	 * @param graph  : the graph in which the method saves the min and the max
	 *               values.
	 * @param parser : the file that we browse to look for the min and the max
	 *               values.
	 * @throws IOException for readLigne @see parser.readLine()
	 */
	public void readMaxMinValueGraph(Parser parser, Graph graph) throws IOException {
		while (parser.readLine() != null) {
			try {
				Integer numData = 0;
				Integer numColumn = 0;
				do {
					if (!numColumn.equals(graph.getColCategory())) {
						graph.setVertexDataMax(numData, parser.parserFloat());
						graph.setVertexDataMin(numData, parser.parserFloat());
						numData++;
					}
					numColumn++;
				} while (parser.parserNext());

			} catch (Exception e) {
				// TODO end line
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method reads a new vertex in the file and normalizes its data, but does
	 * not adds the new vertex to the graph.
	 * 
	 * @param parser      : the parser which is used to read the file.
	 * @param idNewVertex : the id of the new vertex.
	 * @param graph       : the graph that will contain later the vertex read from
	 *                    the file.
	 * @return the new vertex.
	 */
	public Vertex readNewVertex(Parser parser, Integer idNewVertex, Graph graph) {
		try {
			Float newValue = (float) 0.0;
			Integer numData = 0;
			Integer numColumn = 0;
			Vertex vertex = new Vertex(idNewVertex);
			if (parser.readLine() == null) {
				return null;
			}
			do {
				try {
					if (numColumn.equals(graph.getColCategory())) {
						vertex.setCategory(parser.parserString());
					} else {
						newValue = (parser.parserFloat() - graph.getVertexDataMin(numData))
								/ (graph.getVertexDataMax(numData) - graph.getVertexDataMin(numData));
						vertex.addData(newValue);
						numData++;
					}
					numColumn++;

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
	 * This method writes the graph "graph" in a file in a format compatible with
	 * Tulipe.
	 * 
	 * @param graph    : the graph to write in a file.
	 * @param fileName : name of the file where the graph will be written
	 */
	public void writeTulipFile(Graph graph, String fileName) {
		try {
			File file = new File(fileName);
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("(tlp \"2.3\" \n");
			fileWriter.write("(nb_nodes " + (graph.getNbVertices()) + ")\n");
			fileWriter.write("(nodes 0.." + (graph.getNbVertices() - 1) + ")\n");
			Integer indexEdge = 0;
			for (Entry<Edge, Boolean> edge : graph.getMatrix().getHashSet()) {
				fileWriter.write("(edge " + indexEdge + " " + edge.getKey().getIdVertex1() + " "
						+ edge.getKey().getIdVertex2() + ")\n");
				++indexEdge;
			}
			if (graph.getColCategory() != null) {
				fileWriter.write("(property 0 string \"viewLabel\"\n" + "(default \"\" \"\" )\n");
				for (Vertex vertex : graph.getListVertex()) {
					fileWriter.write("(node " + vertex.getIdVertex() + " \"" + vertex.getCategory() + "\")\n");
				}
				fileWriter.write(")\n");
			} else {
				fileWriter.write("(property 0 string \"viewLabel\"\n" + "(default \"\" \"\" )\n");
				for (Vertex vertex : graph.getListVertex()) {
					fileWriter.write("(node " + vertex.getIdVertex() + " \"" + vertex.getIdVertex() + "\")\n");
				}
				fileWriter.write(")\n");
			}

			// fileWriter.write("(property 0 string \"viewLabelPosition\"\n" + "(default
			// \"1\" )\n" + ")\n");

			fileWriter.write(")\n");

			fileWriter.close();
		} catch (Exception e) {
		}
	}

}
