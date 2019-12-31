import java.io.FileNotFoundException;

import parser.Parser;

public class Main {
	public static void main(String[] args) {
		Graph graph = new Graph();
		OperationsGraph opGraph = new OperationsGraph();
		Vertex vertex;
		Integer idVertex = 0;
		Parser parserMM;
		Parser parser;
		try {
			parserMM = new Parser("D:\\Polytech\\4A-S7\\projet\\wine.Data", ",");
			parser = new Parser("D:\\Polytech\\4A-S7\\projet\\wine.Data", ",");

			opGraph.readMaxMinValueGraph(parserMM, graph);
			
			while ((vertex = opGraph.readNewVertex(parser, idVertex, graph)) != null) {
				System.out.println(vertex);
				graph.addVertex(vertex);
				Graph subGraph = opGraph.extractSubgraph(graph, opGraph.findNearest(vertex, graph));
				subGraph.addVertex(vertex);
				opGraph.constructRNG(subGraph);
				opGraph.mergeSubgraph(graph, subGraph);
				++idVertex;
			}
			//opGraph.constructRNG(graph);

			opGraph.writeTulipFile(graph);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
