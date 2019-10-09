import java.util.ArrayList;

public class Graph {

	Matrix matrix; 
    ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
    
	////////////////////////////// Methods ///////////////////////////////////
	public void addVertex(Vertex vertexToAdd) {
		listVertex.add(vertexToAdd);
	}
}
