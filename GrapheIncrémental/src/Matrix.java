import java.util.HashMap;

public class Matrix {
	// public HashMap<PosMatrix, Boolean> matrix = new HashMap<PosMatrix,
	// Boolean>();
	// public HashMap<Integer, HashMap<Integer, Boolean>> matrix = new
	// HashMap<Integer, HashMap<Integer, Boolean>>();
	private int sizeMatrix = 0;
	public HashMap<Integer, Boolean> matrix = new HashMap<Integer, Boolean>();

	public Boolean getEdges(Integer i, Integer j) throws Exception {
//		if (i < j)
//			if (matrix.containsKey(i))
//				if (matrix.get(i).containsKey(j))
//					return true;
//				else
//					return false;
//			else
//				return false;
//		else if (i > j)
//			if (matrix.containsKey(j))
//				if (matrix.get(j).containsKey(i))
//					return true;
//				else
//					return false;
//			else
//				return false;
//		else
//			throw new Exception("Matrix_IllegalEdgeRead");
		String stringToHash;
		Integer hashcode;
		if (i < j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else if (i > j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else
			throw new Exception("Matrix_IllegalEdgeRead");
		return matrix.containsKey(hashcode) ? matrix.get(hashcode) : false;

	}

	public void createEdges(Integer i, Integer j) throws Exception {
//		String test = (i.toString() + j.toString());
//		int tet = test.hashCode();
//		System.out.println(tet);
//		if (i < j)
//			if (matrix.containsKey(i))
//				matrix.get(i).put(j, true);
//			else {
//				matrix.put(i, new HashMap<Integer, Boolean>());
//				matrix.get(i).put(j, true);
//			}
//		else if (i > j)
//			if (matrix.containsKey(i))
//				matrix.get(j).put(i, true);
//			else {
//				matrix.put(j, new HashMap<Integer, Boolean>());
//				matrix.get(j).put(i, true);
//			}
//		else
//			throw new Exception("Matrix_IllegalEdgeCreate");
		
		String stringToHash;
		Integer hashcode;
		if (i < j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else if (i > j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else
			throw new Exception("Matrix_IllegalEdgeCreate");
		matrix.put(hashcode, true);
	}

	public void deleteEdges(Integer i, Integer j) throws Exception {
		/*if (i < j)
			matrix.get(i).remove(j);

		else if (i > j)
			matrix.get(j).remove(i);

		else
			throw new Exception("Matrix_IllegalEdgeDelete");*/
		String stringToHash;
		Integer hashcode;
		if (i < j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else if (i > j) {
			stringToHash = (i.toString() + j.toString());
			hashcode = stringToHash.hashCode();
		} else
			throw new Exception("Matrix_IllegalEdgeCreate");
		if (matrix.containsKey(hashcode))
			matrix.remove(hashcode);
	}

	public static void main(String[] args) throws Exception {
		Matrix test = new Matrix();
		test.createEdges(0, 1);
		test.createEdges(2, 1);
		test.createEdges(1, 2);
		test.createEdges(1, 0);
		test.deleteEdges(0, 1);

		System.out.println(test.getEdges(0, 1));
		System.out.println(test.getEdges(2, 0));
		System.out.println(test.getEdges(2, 1));
		/*
		 * test.matrix.put(new PosMatrix(1, 2), false); test.matrix.put(new PosMatrix(1,
		 * 3), false); test.matrix.put(new PosMatrix(1, 2), true);
		 * 
		 * System.out.println(test.matrix.get(new PosMatrix(1, 2)));
		 * System.out.println(new PosMatrix(1, 2).equals(new PosMatrix(1, 2)));
		 */
	}
}
/*
 * class PosMatrix implements Comparable<PosMatrix> { int i; int j;
 * 
 * public PosMatrix(int i, int j) { this.i = i; this.j = j; }
 * 
 * @Override public boolean equals(Object obj) { System.out.println("eed"); if
 * (obj == null || obj.getClass() != getClass()) return false; else { }
 * PosMatrix nObj = (PosMatrix) obj; if (nObj.i == i && nObj.j == j) return
 * true; else return false; }
 * 
 * @Override public int compareTo(PosMatrix obj) { System.out.println("eed"); if
 * (obj == null) return 1; else { if (obj.i == i && obj.j == j) return 0; else
 * return 1; } } }
 */
