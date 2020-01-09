package main;
import java.io.FileNotFoundException;
import java.io.IOException;

import Test.CompareGraph;
import graphManagement.Graph;
import graphManagement.OperationsGraph;
import graphManagement.Vertex;
import parser.Parser;

public class Main {
	static private Integer mode = 0;
	
	public static void main(String[] args) {
		/*Verificationn of the number of arguments */
		if (args.length < 2) {
			System.out.println("Arguments manquants");
		}
		/*initialization of all variable */
		mode = Integer.parseInt(args[0]);

		Graph graphInc = new Graph();
		Graph subGraph;
		Graph graphDirect = new Graph();
		if (args.length >= 3 && !args[3].equalsIgnoreCase("null"))
			graphInc.setColCategory(Integer.parseInt(args[3]));
		OperationsGraph opGraph = new OperationsGraph();
		CompareGraph compareGraph = new CompareGraph();
		Vertex vertex;
		Integer idVertex = 0;
		Parser parserMM;
		Parser parser;
		String fileNameGraphInc = "graphIncremental.TLP";
		String fileNameGraphDirect = "graphDirect.TLP";
		String fileNameMissingGraph = "graphDiferenceArcManquant.TLP";
		String fileNameExtraGraph = "graphDiferenceArcSupplementaire.TLP";
		graphDirect.setColCategory(graphInc.getColCategory());
		
		try {
			parserMM = new Parser(args[1], args[2]);
			parser = new Parser(args[1], args[2]);
			opGraph.readMaxMinValueGraph(parserMM, graphInc);

			vertex = opGraph.readNewVertex(parser, idVertex, graphInc);
			graphInc.addVertex(vertex);
			if (mode > 1)
				graphDirect.addVertex(vertex);
			++idVertex;
			
			/*build incremental graph*/
			while ((vertex = opGraph.readNewVertex(parser, idVertex, graphInc)) != null) {
				if (idVertex % 50 == 0)
					System.out.println(idVertex);
				if (mode == 3)
					subGraph = opGraph.extractSubgraph(graphInc, opGraph.findNearestFullExplor(vertex, graphInc));
				else
					subGraph = opGraph.extractSubgraph(graphInc, opGraph.findNearest(vertex, graphInc));

				subGraph.addVertex(vertex);
				opGraph.constructRNG(subGraph);
				graphInc.addVertex(vertex);
				opGraph.mergeSubgraph(graphInc, subGraph);

				if (mode > 1)
					graphDirect.addVertex(vertex);
				++idVertex;
			}
			
			/*build direct graph*/
			if (mode > 1)
				opGraph.constructRNG(graphDirect);

			/*get name of output file*/
			if (args.length >= 4) {
				fileNameGraphInc = args[4];
			}
			if (args.length >= 5) {
				fileNameGraphDirect = args[5];
			}
			if (args.length >= 6) {
				fileNameMissingGraph = args[6];
			}
			if (args.length >= 7) {
				fileNameExtraGraph = args[7];
			}
			
			/*Writing of all file and information */
			opGraph.writeTulipFile(graphInc, fileNameGraphInc);
			if (mode >= 1) {
				opGraph.writeTulipFile(graphDirect, fileNameGraphDirect);
				System.out.println("Le graph contient " + graphInc.getNbVertices() + " sommet et au total "
						+ graphInc.getNbVerticesExplore() + " sommet on été parcourue pour trouver a chaque "
						+ "fois le sommet le plus proche. \nUne recherche en calculant tout aurait explorer : "
						+ graphInc.getNbVertices() * (graphInc.getNbVertices() + 1) / 2);
				System.out.println("le sommet le plus proche a été trouver " + graphInc.getNbVerticesNearestExact()
						+ " fois sur " + (graphInc.getNbVertices() - 1));
			}

			if (mode == 2)
				compareGraph.createDifferenceGraph(graphDirect, graphInc, fileNameMissingGraph, fileNameExtraGraph);

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

