import java.util.ArrayList;

public class Vertex {
	private int idVertex;
	private ArrayList<Float> listData = new ArrayList<Float>();
	//category
	
	//////////////////////////////Getters & setters///////////////////////////////////

	/**
	 * Constructor
	 * @param idVertex
	 */
	public Vertex(int idVertex) {
		this.idVertex = idVertex;
	}
	
	//////////////////////////////Getters & setters///////////////////////////////////
	/**
	 * @return the idVertex
	 */
	public int getIdVertex() {
		return idVertex;
	}

	/**
	 * @return the listData
	 */
	public ArrayList<Float> getListData() {
		return listData;
	}
	
	/**
	 * @param idVertex : the idVertex to set
	 */
	public void setIdVertex(int idVertex) {
		this.idVertex = idVertex;
	}
	
//	toString
	//////////////////////////////Methods///////////////////////////////////
	
	
}
