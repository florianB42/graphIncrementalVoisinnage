import java.io.FileNotFoundException;

import graphManagement.Graph;
import graphManagement.OperationsGraph;
import graphManagement.Vertex;
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
			//parserMM = new Parser("D:\\Polytech\\4A-S7\\projet\\wine.Data", ",");
			//parser = new Parser("D:\\Polytech\\4A-S7\\projet\\wine.Data", ",");
			//parserMM = new Parser("D:\\Polytech\\4A-S7\\projet\\winequality-white.csv", ";");
			//parser = new Parser("D:\\Polytech\\4A-S7\\projet\\winequality-white.csv", ";");
			parserMM = new Parser(args[0], args[1]);
			parser = new Parser(args[0], args[1]);
			
			opGraph.readMaxMinValueGraph(parserMM, graph);
			
			vertex = opGraph.readNewVertex(parser, idVertex, graph);
			graph.addVertex(vertex);
			++idVertex;
			
			while ((vertex = opGraph.readNewVertex(parser, idVertex, graph)) != null) {
		System.out.println(vertex);
				//Graph subGraph = opGraph.extractSubgraph(graph, opGraph.findNearest(vertex, graph));
				//subGraph.addVertex(vertex);
		//System.out.println(subGraph.getListVertex());
				//opGraph.constructRNG(subGraph);
				graph.addVertex(vertex);
				//opGraph.mergeSubgraph(graph, subGraph);
				++idVertex;
			}
			opGraph.constructRNG(graph);

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
