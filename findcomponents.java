/**
 * This class reads an adjacency matrix from a file and finds the strongly
 * connected components in the input graph.
 * 
 * @author Zhen Chen
 * @AUID 6438580
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

public class findcomponents {
	private static int[][] matrix, components;
	private static final String INPUT_FILE_NAME = "matrix.txt";
	private static final String OUTPUT_FILE_NAME = "components.txt";

	public static void main(String[] args) {
		matrix = fileHelper.readFile(INPUT_FILE_NAME);
		try {
			components = componentsInMatrix(matrix);
			fileHelper.writeFile(OUTPUT_FILE_NAME, components);
		} catch (NullPointerException e) {
			System.out.println("Argument is null!");
		}
	}

	/**
	 * Perform a depth first search on the reverse graph according to the order
	 * of vertex processed in the previous depth first search on the original
	 * graph and give all strongly connected components of this graph.
	 * 
	 * @param reverselist
	 *            An adjacency list representing the reverse graph.
	 * @param reference
	 *            An array containing the result of previous dfs on the original
	 *            graph.
	 * 
	 * @return A two-dimensional array of vertices in each strongly connected
	 *         component of the graph.
	 */
	public static int[][] reversedfs(int[][] reverselist, int[][] reference) {
		int i, j, n, tree, counter;
		int[][] components;
		n = reverselist.length;
		counter = 0;
		int[] order = new int[n];
		int[] lineLength = new int[n];
		boolean[] white = new boolean[n];
		ArrayList<int[]> temp = new ArrayList<int[]>();

		// colour all vertices white initially
		for (i = 0; i < n; i++) {
			white[i] = true;
		}

		// build the order of vertex to search
		i = n;
		for (int[] line : reference) {
			if (line.length == 2) {
				order[--i] = line[1];
			}
		}

		// do we have any white vertices?
		tree = 0; // number of trees (components)
		for (i = 0; i < n; i++) {
			if (white[order[i]] == true) {
				// run dfs once based on the source vertex
				counter = dfs.onedfs(reverselist, white, order[i], temp,
						counter);
				lineLength[tree++] = counter;
				counter = 0;
			}
		}

		components = new int[tree][];
		for (i = 0; i < tree; i++) {
			components[i] = new int[lineLength[i]];
			for (j = 0; j < lineLength[i]; j++) {
				components[i][j] = temp.get(counter++)[1];
			}
			Arrays.sort(components[i]);
			counter++;
		}

		return components;
	}

	/**
	 * Find all strongly connected components in the graph.
	 * 
	 * @param matrix
	 *            An adjacency matrix.
	 * 
	 * @return A two-dimensional array of vertices in each strongly connected
	 *         component of the graph.
	 */
	public static int[][] componentsInMatrix(int[][] matrix) {
		int[][] list;

		list = matrix2list.transform(matrix);
		return componentsInList(list);
	}

	/**
	 * Find all strongly connected components in the graph.
	 * 
	 * @param list
	 *            An adjacency list.
	 * 
	 * @return A two-dimensional array of vertices in each strongly connected
	 *         component of the graph.
	 */
	public static int[][] componentsInList(int[][] list) {
		int[][] reverselist, dfslist;

		reverselist = list2reverse.reverse(list);
		dfslist = dfs.fulldfs(list);
		return reversedfs(reverselist, dfslist);
	}
}
