package graphManagement;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (idVertex1 == null) {
			if (other.idVertex1 != null)
				return false;
		} else if (!idVertex1.equals(other.idVertex1))
			return false;
		if (idVertex2 == null) {
			if (other.idVertex2 != null)
				return false;
		} else if (!idVertex2.equals(other.idVertex2))
			return false;
		return true;
	}

	
	
}
