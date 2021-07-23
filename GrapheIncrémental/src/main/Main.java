package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import graphManagement.Graph;
import graphManagement.OperationsGraph;
import graphManagement.Vertex;
import parser.Parser;
import result.CompareGraph;

public class Main {
	static private Integer mode = 0;

	public static void main(String[] args) {
		/* Verificationn of the number of arguments */
		if (args.length < 2) {
			System.out.println("Arguments manquants");
		}
		/* initialization of all variable */
		mode = Integer.parseInt(args[0]);

		Graph graphIncNew = new Graph();
		Graph graphIncNormal = new Graph();
		Graph subGraph;
		Graph graphDirect = new Graph();
		if (args.length >= 4 && !args[3].equalsIgnoreCase("null"))
			graphIncNew.setColCategory(Integer.parseInt(args[3]));
		OperationsGraph opGraph = new OperationsGraph();
		CompareGraph compareGraph = new CompareGraph();
		Vertex vertex;
		Vertex nearestNew;
		Vertex nearestFull;
		Integer idVertex = 0;
		Parser parserMM;
		Parser parser;
		String fileNameGraphIncNew = "graphIncrementalNew.TLP";
		String fileNameGraphDirect = "graphDirect.TLP";
		String fileNameGraphIncNormal = "graphIncrementalNormal.TLP";
		graphDirect.setColCategory(graphIncNew.getColCategory());

		try {
			parserMM = new Parser(args[1], args[2]);
			parser = new Parser(args[1], args[2]);
			opGraph.readMaxMinValueGraph(parserMM, graphIncNew);

			vertex = opGraph.readNewVertex(parser, idVertex, graphIncNew);
			graphIncNew.addVertex(vertex);
			if (mode == 1) {
				graphDirect.addVertex(vertex);
				graphIncNormal.addVertex(vertex);
			}
			++idVertex;

			/* build incremental graph */
			while ((vertex = opGraph.readNewVertex(parser, idVertex, graphIncNew)) != null) {
				if (idVertex % 50 == 0)
					System.out.println(idVertex);
				nearestNew = opGraph.findNearest(vertex, graphIncNew);
				subGraph = opGraph.extractSubgraph(graphIncNew, nearestNew);
				subGraph.addVertex(vertex);
				opGraph.constructRNG(subGraph);
				graphIncNew.addVertex(vertex);
				opGraph.mergeSubgraph(graphIncNew, subGraph);

				if (mode == 1) {
					nearestFull = opGraph.findNearestFullExplor(vertex, graphIncNormal);
					subGraph = opGraph.extractSubgraph(graphIncNormal, nearestFull);
					subGraph.addVertex(vertex);
					opGraph.constructRNG(subGraph);
					graphIncNormal.addVertex(vertex);
					opGraph.mergeSubgraph(graphIncNormal, subGraph);
					
					if (nearestFull == nearestNew) 
						graphIncNew.incrementNbVerticesNearestExact();
					
					graphDirect.addVertex(vertex);
				}
				++idVertex;
			}

			/* build direct graph */
			if (mode == 1)
				opGraph.constructRNG(graphDirect);

			/* get name of output file */
			if (args.length >= 5) {
				fileNameGraphIncNew = args[4];
			}
			if (args.length >= 6) {
				fileNameGraphDirect = args[5];
			}
			if (args.length >= 7) {
				fileNameGraphIncNormal = args[6];
			}

			/* Writing of all file and information */
			opGraph.writeTulipFile(graphIncNew, fileNameGraphIncNew);

			if (mode == 1) {
				opGraph.writeTulipFile(graphDirect, fileNameGraphDirect);
				opGraph.writeTulipFile(graphIncNormal, fileNameGraphIncNormal);
				System.out.println("Le graph contient " + graphIncNew.getNbVertices() + " sommet et au total "
						+ graphIncNew.getNbVerticesExplore() + " sommet on été parcourue pour trouver a chaque "
						+ "fois le sommet le plus proche. \nUne recherche en calculant tout aurait explorer : "
						+ graphIncNew.getNbVertices() * (graphIncNew.getNbVertices() + 1) / 2);
				System.out.println("le sommet le plus proche a été trouver " + graphIncNew.getNbVerticesNearestExact()
						+ " fois sur " + (graphIncNew.getNbVertices() - 1));

				System.out.println(
						"\nmatrice de confusion entre le graph Direct et le graph incremental nouvelle version");
				compareGraph.calculConfusionMatrix(graphDirect, graphIncNew);
				System.out.println("\nmatrice de confusion entre le graph Direct et le graph incremental normal");
				compareGraph.calculConfusionMatrix(graphDirect, graphIncNormal);
				System.out.println(
						"\nmatrice de confusion entre le graph incremental normal et le graph incremental nouvelle version");
				compareGraph.calculConfusionMatrix(graphIncNormal, graphIncNew);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier");
		} catch (IOException e) {
			System.out.println("Echec de lecture de fichier");
		}
	}

	static public Integer getMode() {
		return mode;
	}
}
