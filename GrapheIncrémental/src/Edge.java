
public class Edge {
	Integer idVertex1;
	Integer idVertex2;	
	
	/**
	 * builder
	 * @param idVertex1
	 * @param idVertex2
	 */
	public Edge(Integer idVertex1, Integer idVertex2) {
		this.idVertex1 = idVertex1;
		this.idVertex2 = idVertex2;
	}
	
	/**
	 * getter of idVertex1
	 * @return idVertex1
	 */
	public Integer getIdVertex1() {
		return idVertex1;
	}
	
	/**
	 * set idVertex1 at value of idVertex1
	 * @param idVertex1
	 */
	public void setIdVertex1(Integer idVertex1) {
		this.idVertex1 = idVertex1;
	}
	
	/**
	 * getter of idVertex2
	 * @return idVertex2
	 */
	public Integer getIdVertex2() {
		return idVertex2;
	}
	
	/**
	 * set idVertex2 at value of idVertex2
	 * @param idVertex2
	 */
	public void setIdVertex2(Integer idVertex2) {
		this.idVertex2 = idVertex2;
	}

	
	
}
