/**
 * 
 */
package Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.*;

import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.Matrix;
import graphManagement.OperationsGraph;
import graphManagement.Vertex;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * @author ahlam
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class OperationsGraphTest {

	static Graph graph = mock(Graph.class);
	static ArrayList<Vertex> listNeiboursVertex1 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex2 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex3 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex4 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex5 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex6 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex7 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex8 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex9 = new ArrayList<Vertex>();
	static ArrayList<Vertex> listNeiboursVertex10 = new ArrayList<Vertex>();
	
	static Vertex vertex1 = new Vertex(1);
	static Vertex vertex2 = new Vertex(2);
	static Vertex vertex3 = new Vertex(3);
	static Vertex vertex4 = new Vertex(4);	
	static Vertex vertex5 = new Vertex(5);
	static Vertex vertex6 = new Vertex(6);
	static Vertex vertex7 = new Vertex(7);	
	static Vertex vertex8 = new Vertex(8);
	static Vertex vertex9 = new Vertex(9);
	static Vertex vertex10 = new Vertex(10);
	
	//MergeSubgraph
	static Graph initialGraph = new Graph();
	static Graph graphToMerge = new Graph();
	static Graph resGraphMerged;
	
	@BeforeAll
	static void setUp() {
		listNeiboursVertex1.add(vertex2);
		when(graph.getNeighbours(vertex1)).thenReturn(listNeiboursVertex1);
		
		listNeiboursVertex2.add(vertex1);
		listNeiboursVertex2.add(vertex3);
		listNeiboursVertex2.add(vertex5);
		when(graph.getNeighbours(vertex2)).thenReturn(listNeiboursVertex2);
		
		listNeiboursVertex3.add(vertex2);
		when(graph.getNeighbours(vertex3)).thenReturn(listNeiboursVertex3);
		
		listNeiboursVertex4.add(vertex5);
		when(graph.getNeighbours(vertex4)).thenReturn(listNeiboursVertex4);
		
		listNeiboursVertex5.add(vertex2);
		listNeiboursVertex5.add(vertex4);
		listNeiboursVertex5.add(vertex6);
		listNeiboursVertex5.add(vertex8);
		when(graph.getNeighbours(vertex5)).thenReturn(listNeiboursVertex5);
		
		listNeiboursVertex6.add(vertex5);
		listNeiboursVertex6.add(vertex7);
		when(graph.getNeighbours(vertex6)).thenReturn(listNeiboursVertex6);
		
		listNeiboursVertex7.add(vertex6);
		when(graph.getNeighbours(vertex7)).thenReturn(listNeiboursVertex7);
		
		listNeiboursVertex8.add(vertex9);
		listNeiboursVertex8.add(vertex10);
		listNeiboursVertex8.add(vertex5);
		when(graph.getNeighbours(vertex8)).thenReturn(listNeiboursVertex8);
		
		listNeiboursVertex9.add(vertex8);
		when(graph.getNeighbours(vertex9)).thenReturn(listNeiboursVertex9);
		
		listNeiboursVertex10.add(vertex8);
		when(graph.getNeighbours(vertex10)).thenReturn(listNeiboursVertex10);
		
		//mergeSubgraph
		initialGraph.addVertex(vertex1);
		initialGraph.addVertex(vertex2);
		initialGraph.addVertex(vertex3);
		initialGraph.addVertex(vertex4);
		initialGraph.addVertex(vertex5);
		initialGraph.addVertex(vertex6);
		initialGraph.addVertex(vertex7);
		initialGraph.addVertex(vertex8);
		initialGraph.addVertex(vertex9);
		initialGraph.addVertex(vertex10);
		try {
			initialGraph.createEdge(1, 2);
			initialGraph.createEdge(2, 3);
			initialGraph.createEdge(2, 5);
			initialGraph.createEdge(5, 4);
			initialGraph.createEdge(5, 6);
			initialGraph.createEdge(5, 8);
			initialGraph.createEdge(6, 7);
			initialGraph.createEdge(8, 9);
			initialGraph.createEdge(8, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resGraphMerged = new Graph(new Matrix(), initialGraph.getListVertex());
		try {
			resGraphMerged.createEdge(1, 2);
			resGraphMerged.createEdge(2, 3);
			resGraphMerged.createEdge(2, 4);
			resGraphMerged.createEdge(4, 6);
			resGraphMerged.createEdge(6, 7);
			resGraphMerged.createEdge(6, 8);
			resGraphMerged.createEdge(8, 5);
			resGraphMerged.createEdge(8, 9);
			resGraphMerged.createEdge(8, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		graphToMerge.addVertex(vertex2);
		graphToMerge.addVertex(vertex4);
		graphToMerge.addVertex(vertex6);
		graphToMerge.addVertex(vertex8);
		graphToMerge.addVertex(vertex5);
		
		try {
			graphToMerge.createEdge(2, 4);
			graphToMerge.createEdge(4, 6);
			graphToMerge.createEdge(6, 8);
			graphToMerge.createEdge(8, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	@Order(1)
	void testGetNeighbours() {
		
		assertEquals(initialGraph.getNeighbours(vertex5), listNeiboursVertex5, "The neignbour's list is not good.");
		//assertTrue(initialGraph.getNeighbours(vertex5).containsAll(listNeiboursVertex5), "The neignbour's list is not good.");

	}
	/**
	 * Test method for {@link OperationsGraph#findNearest(Vertex, Graph)}.
	 */
	@Test
	@Order(2)
	void testFindNearest() {
		OperationsGraph opGraph = new OperationsGraph();
		vertex1.addData(12);
		vertex2.addData(3);
		vertex3.addData(2);
		vertex4.addData(75);
		vertex5.addData(14);
		vertex6.addData((float)10.2);
		vertex7.addData(15);
		vertex8.addData(64);
		vertex9.addData(4);
		vertex10.addData(82);
		
		Vertex vertex11 = new Vertex(11);
		vertex11.addData(11);
		Vertex res = vertex6;
	
		assertEquals(opGraph.findNearest(vertex11, initialGraph), res);
		
	}

	/**
	 * Test method for {@link OperationsGraph#extractSubgraph(Graph, Vertex)}.
	 */
	@Test
	@Order(3)
	void testExtractSubgraph() {
		OperationsGraph opGraph = new OperationsGraph();
		Graph graphRes = new Graph();
		graphRes.addVertex(vertex1);
		graphRes.addVertex(vertex2);
		graphRes.addVertex(vertex3);
		graphRes.addVertex(vertex5);
		
		Graph resToTest = opGraph.extractSubgraph(graph, vertex1);
		assertTrue(resToTest.getListVertex().containsAll(graphRes.getListVertex()),"Missing vertex in the extracted graph1");
		assertTrue(graphRes.getListVertex().containsAll(resToTest.getListVertex()),"Too many vertices in the extracted graph1");
	
		graphRes = new Graph();
		graphRes.addVertex(vertex1);
		graphRes.addVertex(vertex2);
		graphRes.addVertex(vertex3);
		graphRes.addVertex(vertex4);
		graphRes.addVertex(vertex5);
		graphRes.addVertex(vertex6);
		graphRes.addVertex(vertex8);
		
		resToTest = opGraph.extractSubgraph(graph, vertex2);
		assertTrue(resToTest.getListVertex().containsAll(graphRes.getListVertex()),"Missing vertex in the extracted graph2");
		assertTrue(graphRes.getListVertex().containsAll(resToTest.getListVertex()),"Too many vertices in the extracted graph2");

	}

	/**
	 * Test method for {@link OperationsGraph#mergeSubgraph(Graph, Graph)}.
	 */
	@Test
	@Order(4)
	void testMergeSubgraph() {
		OperationsGraph opGraph = new OperationsGraph();
		opGraph.mergeSubgraph(initialGraph, graphToMerge);
		assertTrue(initialGraph.getListVertex().containsAll(resGraphMerged.getListVertex()), "Missing vertex in the initial graph.");
		assertTrue(resGraphMerged.getListVertex().containsAll(initialGraph.getListVertex()), "Too many vertices in the initial graph.");
		
		assertTrue(initialGraph.getMatrix().getHashSet().containsAll(resGraphMerged.getMatrix().getHashSet()), "Missing edges in the initial graph");
		assertTrue(resGraphMerged.getMatrix().getHashSet().containsAll(initialGraph.getMatrix().getHashSet()), "Too many edges in the initial graph");
		
	}

}
