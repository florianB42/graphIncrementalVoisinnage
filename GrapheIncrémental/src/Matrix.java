import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Matrix {
	private int sizeMatrix = 0;
	public HashMap<Double, Edge> matrix = new HashMap<Double, Edge>();

	/**
	 * @param sommet1
	 * @param sommet2
	 * @return
	 */
	private static Double calculateHashCode(Integer sommet1, Integer sommet2) {
		Double hashcode = (sommet1 * 0.314159265359 + sommet2 * 0.161803398875) % 100;
		return hashcode;
	}

	/**
	 * @param sommet1
	 * @param sommet2
	 * @return
	 * @throws Exception
	 */
	public Edge getEdges(Integer sommet1, Integer sommet2) throws Exception {
		Double hashcode;
		if (sommet1 < sommet2) {
			hashcode = calculateHashCode(sommet1, sommet2);
		} else if (sommet1 > sommet2) {
			hashcode = calculateHashCode(sommet2, sommet1);
		} else
			throw new Exception("Matrix_IllegalEdgeRead");
		return matrix.containsKey(hashcode) ? matrix.get(hashcode) : null;

	}

	/**
	 * @param sommet1
	 * @param sommet2
	 * @throws Exception
	 */
	public void createEdges(Integer sommet1, Integer sommet2) throws Exception {
		Double hashcode;
		if (sommet1 < sommet2) {
			hashcode = calculateHashCode(sommet1, sommet2);
		} else if (sommet1 > sommet2) {
			hashcode = calculateHashCode(sommet2, sommet1);
		} else
			throw new Exception("Matrix_IllegalEdgeCreate");
		matrix.put(hashcode, new Edge(sommet1, sommet2));
	}

	/**
	 * @param sommet1
	 * @param sommet2
	 * @throws Exception
	 */
	public void deleteEdges(Integer sommet1, Integer sommet2) throws Exception {
		Double hashcode;
		if (sommet1 < sommet2) {
			hashcode = calculateHashCode(sommet1, sommet2);
		} else if (sommet1 > sommet2) {
			hashcode = calculateHashCode(sommet2, sommet1);
		} else
			throw new Exception("Matrix_IllegalEdgeDelete");
		if (matrix.containsKey(hashcode))
			matrix.remove(hashcode);
	}
	
	public Set<Entry<Double, Edge>> getHashSet() {
		return matrix.entrySet();
	}

	/*
	public static void main(String[] args) throws Exception {
		Matrix test = new Matrix();

	}
	
	public static void main(String[] args) throws Exception {
		Matrix test = new Matrix();
		int taille = 5000;
		ArrayList<Integer> dispertion = new ArrayList<Integer>();

		for (int i = 1; i <= taille; ++i) {
			System.out.println("construction : " + i);
			for (int j = i + 1; j <= taille; ++j) {
				test.createEdges(i, j, j + taille * i);
			}
		}

		for (int i = 1; i <= taille; ++i) {
			System.out.println("test : " + i);
			for (int j = i + 1; j <= taille; ++j) {
				// System.out.println(test.getEdges(i, j));
				// System.out.println("testj : " + j);
				if (test.getEdges(i, j) != j + taille * i)
					throw new Exception("Matrix_Marche_pas");
				Integer dis = (calculateHashCode(i, j)).intValue();
				try {
					Integer prov = dispertion.get(dis);
					dispertion.set(dis, ++prov);
				} catch (IndexOutOfBoundsException e) {
					// System.out.println("eetrgret");
					dispertion.add(dis, 1);
				}

			}
		}
		System.out.println("fin");
		System.out.println(dispertion);
		Integer min = Integer.MAX_VALUE;
		Integer max = 0;
		Integer moy = 0;

		for (Integer i : dispertion) {
			if (i < min)
				min = i;
			if (i > max)
				max = i;
			moy += i;
		}
		
		System.out.println(max);
		System.out.println(min);
		System.out.println(moy/100);
		
		ArrayList<ArrayList<Double>> test1 = new ArrayList<ArrayList<Double>>();
		test1.add(new ArrayList<Double>());
		//test1.get(0).add(5,2);
		

	}*/
	
}

