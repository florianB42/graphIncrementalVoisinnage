import java.io.FileNotFoundException;

import parser.Parser;

public class Main {
	public static void main(String[] args) {
		Graph graph = new Graph();
		OperationsGraph opGraph = new OperationsGraph();
		Vertex vertex;
		Integer idVertex = 0;
		Parser parser;
		try {
			parser = new Parser("D:\\Téléchargement\\iris.Data", ",");

			while ((vertex = opGraph.readNewVertex(parser, idVertex)) != null) {
				System.out.println(vertex);
				graph.addVertex(vertex);
				++idVertex;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
