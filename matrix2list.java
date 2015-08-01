/**
 * This class reads an adjacency matrix from a file and converts it into an
 * adjacency list.
 * 
 * @author Zhen Chen
 * @AUID 6438580
 * 
 */

public class matrix2list {
	private static int[][] matrix, list;
	private static final String INPUT_FILE_NAME = "matrix.txt";
	private static final String OUTPUT_FILE_NAME = "list.txt";

	public static void main(String[] args) {
		matrix = fileHelper.readFile(INPUT_FILE_NAME);
		try {
			list = transform(matrix);
			fileHelper.writeFile(OUTPUT_FILE_NAME, list);
		} catch (IllegalArgumentException e) {
			System.out.println("Input is not a valid matrix!");
		} catch (NullPointerException e) {
			System.out.println("Argument is null!");
		}
	}

	/**
	 * Transform an adjacency matrix into an adjacency list.
	 * 
	 * @param matrix
	 *            An adjacency matrix.
	 * 
	 * @return An adjacency list of the graph.
	 * 
	 * @throws IllegalArgumentException
	 *             If the input is not a valid adjacency matrix.
	 */
	public static int[][] transform(int[][] matrix)
			throws IllegalArgumentException {
		int i, j, k, n;
		n = matrix.length;
		int[][] list = new int[n][];
		int[] temp = new int[n];

		for (i = 0; i < n; i++) {
			if (matrix[i] == null || matrix[i].length != n) {
				throw new IllegalArgumentException();
			}
			k = 0; // number of out neighbours
			for (j = 0; j < n; j++) {
				if (matrix[i][j] > 0) {
					temp[k++] = j;
				}
			}
			list[i] = new int[k];
			for (j = 0; j < k; j++) {
				list[i][j] = temp[j];
			}
		}

		return list;
	}
}
