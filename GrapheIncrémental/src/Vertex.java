import java.util.ArrayList;

public class Vertex {
	private int idVertex;
	private ArrayList<Float> listData = new ArrayList<Float>();
	//category
	
	//////////////////////////////Cons///////////////////////////////////
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
	 * @return the size of listData
	 */
	public double getSizeList() {
		return listData.size();
	}
	
	/**
	 * Gets data from the arrayList listData
	 * @param rank
	 * @return data stored in the rank passed in parameters
	 */
	public float getData(int rank) {
		return listData.get(rank);
	}
	
	/**
	 * @param idVertex : the idVertex to set
	 */
	public void setIdVertex(int idVertex) {
		this.idVertex = idVertex;
	}
	
	//////////////////////////////Methods///////////////////////////////////
	/**
	 * Adds data to the list of data
	 * @param dataToAdd
	 */
	public void addData(float dataToAdd) {
		listData.add(dataToAdd);
	}

}
