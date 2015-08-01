/**
 * This class reads an adjacency list from a file and outputs the adjacency list for the reverse digraph.
 * 
 * @author Zhen Chen
 * @AUID 6438580
 * 
 */

import java.util.ArrayList;

public class list2reverse {
	private static int[][] list, reverselist;
	private static final String INPUT_FILE_NAME = "list.txt";
	private static final String OUTPUT_FILE_NAME = "reverselist.txt";

	public static void main(String[] args) {
		list = fileHelper.readFile(INPUT_FILE_NAME);
		try {
			reverselist = reverse(list);
			fileHelper.writeFile(OUTPUT_FILE_NAME, reverselist);
		} catch (NullPointerException e) {
			System.out.println("Argument is null!");
		}
	}

	/**
	 * Compute the adjacency list for the reverse digraph.
	 * 
	 * @param list
	 *            An adjacency list.
	 * 
	 * @return An adjacency list of the reverse digraph.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int[][] reverse(int[][] list) {
		int i, j, n, m;
		n = list.length;
		int[][] reverselist = new int[n][];
		ArrayList[] temp = new ArrayList[n];
		for (i = 0; i < n; i++) {
			temp[i] = new ArrayList();
		}

		for (i = 0; i < n; i++) {
			for (int vertex : list[i]) {
				temp[vertex].add(i);
			}
		}
		for (i = 0; i < n; i++) {
			m = temp[i].size();
			reverselist[i] = new int[m];
			for (j = 0; j < m; j++) {
				reverselist[i][j] = (int) temp[i].get(j);
			}
		}

		return reverselist;
	}
}
