/**
 * This class helps other classes to read and write files.
 * 
 * @author Zhen Chen
 * @AUID 6438580
 * 
 */

import java.io.*;
import java.util.*;

public class fileHelper {
	/**
	 * Read a graph from a file.
	 * 
	 * @param filename
	 *            The name of file to read.
	 * 
	 * @return A two-dimensional array representing the graph.
	 */
	public static int[][] readFile(String filename) {
		int i, j, n, m;
		int[][] list = null;
		String line;
		ArrayList<String[]> temp = new ArrayList<String[]>();
		File file = new File(filename);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				temp.add(line.split(","));
			}
			n = temp.size();
			list = new int[n][];
			for (i = 0; i < n; i++) {
				m = temp.get(i).length;
				list[i] = new int[m];
				for (j = 0; j < m; j++) {
					list[i][j] = Integer.parseInt(temp.get(i)[j]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + filename);
		}
		
		return list;
	}

	/**
	 * Write a two-dimensional array to a file.
	 * 
	 * @param filename
	 *            The name of file to write.
	 * @param list
	 *            The array to write.
	 */
	public static void writeFile(String filename, int[][] list) {
		int i, j, n, m;
		String line;
		n = list.length;

		try (PrintWriter writer = new PrintWriter(filename)) {
			for (i = 0; i < n; i++) {
				line = "";
				m = list[i].length;
				if (m > 0) {
					j = 0;
					line += list[i][j];
					while (++j < m) {
						line += "," + list[i][j];
					}
				}
				writer.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not create or open file " + filename);
		}
	}
}
