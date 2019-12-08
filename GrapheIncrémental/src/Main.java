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
			Integer vertex1;
			Integer vertex2;
			for (int i = 0; i < 500; ++i) {
				do {				
					vertex1 = (int) (Math.random()*1000 %idVertex);
					vertex2 = (int) (Math.random()*1000 %idVertex);
					System.out.println(i);
				}while (vertex1 == vertex2 || graph.getEdge(vertex1, vertex2) != null);
				
				graph.createEdge(vertex1,vertex2);
			}

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
