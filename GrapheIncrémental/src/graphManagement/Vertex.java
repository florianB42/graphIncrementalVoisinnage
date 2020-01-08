package graphManagement;
import java.util.ArrayList;

public class Vertex {
	/**
	 * The id of the vertex
	 */
	private int idVertex;
	/**
	 * The list of data of the vertex
	 */
	private ArrayList<Float> listData = new ArrayList<Float>();
	/**
	 * The category of the vertex, it can be null
	 */
	String category;
	
	//////////////////////////////Constructor///////////////////////////////////
	/**
	 * Constructor
	 * @param idVertex
	 */
	public Vertex(int idVertex) {
		this.idVertex = idVertex;
	}
	
	//////////////////////////////Getters & setters///////////////////////////////////
	/**
	 * Getter
	 * @return the idVertex
	 */
	public int getIdVertex() {
		return idVertex;
	}

	/**
	 * Getter
	 * @return the listData
	 */
	public ArrayList<Float> getListData() {
		return listData;
	}
	
	/**
	 * Getter
	 * @return the size of listData
	 */
	public int getSizeList() {
		return listData.size();
	}
	
	/**
	 * Gets data from the arrayList listData
	 * @param rank : the position of the data we want
	 * @return data stored in the rank passed in parameters
	 */
	public float getData(int rank) {
		return listData.get(rank);
	}
	
	/**
	 * Getter
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Setter
	 * @param category : the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	//////////////////////////////Methods///////////////////////////////////
	/**
	 * Adds data to the list of data
	 * @param dataToAdd
	 */
	public void addData(float dataToAdd) {
		listData.add(dataToAdd);
	}
	
	@Override
	public String toString() {
		return "id = " + idVertex /*+ "; liste : " + listData.toString()*/ + "; " + category;
	}
}
