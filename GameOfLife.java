import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {
	public static Integer sizeOfGrid;
	public static Integer iteration;
	String seedFile;
	String path = "C:\\Users\\babar naveed\\Desktop\\ap_git\\15100142\\midTerm\\";
	public static CellM[][] gridOfLife;
	public static CellM[][] updateLife;

	public static int abc = 0;

	public GameOfLife(Integer sizeOfGrid, Integer iteration, String seedFile) {
		this.sizeOfGrid = sizeOfGrid;
		this.iteration = iteration;
		this.seedFile = path + seedFile;
		gridOfLife = new CellM[sizeOfGrid][sizeOfGrid];
		updateLife = new CellM[sizeOfGrid][sizeOfGrid];
	}

	public void seed() {
		BufferedReader br = null;
 		try{
 			String currentLine;
 			br = new BufferedReader(new FileReader(this.seedFile));
 			int i = 0;
 			int j = 0;
 			while((currentLine = br.readLine()) != null) {
 				for (char s : currentLine.toCharArray()) {
 					// System.out.println(s);
 					if (s != '\n') {
 					CellM cell;
 					if (s == '.') {
 						cell = new CellM(false);
 					} else {
 						cell = new CellM(true);
 					}
 					gridOfLife[i][j] = cell;

 					j++;
 				}}
 				i++;
 				j = 0;
 				// System.out.println(currentLine);
 			}
 			// System.out.println();
 			// System.out.println();
 			// //checing read
 			// for (int k=0; k<sizeOfGrid; k++) {
 			// 	for(int l=0; l<sizeOfGrid; l++) {
 			// 		gridOfLife[k][l].printCell();
 			// 	}
 			// 	System.out.println();
 			// }
 			for (int a=0; a<sizeOfGrid; a++) {
 				for (int b=0; b<sizeOfGrid; b++) {
 					CellM cell = new CellM(true);
 					updateLife[a][b] = cell;
 				}
 			}
 		copyGridOfLife();
 		System.out.println("seed comeplete");
 		}catch(Exception e) {
 			e.printStackTrace();
 		} finally{
 			try{
 				if (br != null) {
 					br.close();
 				}
 			}catch(Exception e) {
 				e.printStackTrace();
 			}
 		} 
	}

	public static void print() {
		for (int i=0; i<sizeOfGrid; i++) {
			for (int j=0; j<sizeOfGrid; j++) {
				gridOfLife[i][j].printCell();
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public void copyUpdateLife() {
		for (int i=0; i<sizeOfGrid; i++) {
			for (int j=0; j<sizeOfGrid; j++) {
				gridOfLife[i][j].update(updateLife[i][j].checkStatus());
			}
		}
	}

	public void copyGridOfLife() {
		for (int i=0; i<sizeOfGrid; i++) {
			for (int j=0; j<sizeOfGrid; j++) {
				updateLife[i][j].update(gridOfLife[i][j].checkStatus());
				// updateLife[i][j] = gridOfLife[i][j];
			}
		}
	}

	public void runIterations() throws Exception {
		// System.out.println("in runs");
		for (int i=0; i<this.iteration; i++) {
			runIteration();
		}
	}

	public static synchronized int calculateNeighbours(int row, int col) throws Exception {
		// System.out.println("in calculate");
		if(row >= sizeOfGrid || col >= sizeOfGrid || row < 0 || col < 0) {
			throw new Exception("out of bounds");
		}
		// System.out.println("past exception " + row + " " + col);
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				// System.out.println("in loop " + i + " " + j);
				if(i >= sizeOfGrid || j >= sizeOfGrid || i < 0 || j < 0) {} else {
					if (gridOfLife[i][j].checkStatus()) {
						if (i == row && j == col) {} else {
							// System.out.println(" " + i + " " + j + "  increment");
							count++;
						}
					}
				}
			}
		}
		// System.out.println(count);
		return count;
	}

	public void runIteration() throws Exception {
		// System.out.println("in run");
		copyGridOfLife();
		for (int i=0; i<sizeOfGrid; i++) {
			for (int j=0; j<sizeOfGrid; j++) {
				int neighbours = calculateNeighbours(i,j);
				if (neighbours < 2) {
					updateLife[i][j].update(false);
				} else if (neighbours > 3) {
					updateLife[i][j].update(false);
				} else if (neighbours == 3) {
					updateLife[i][j].update(true);
				} else if (neighbours >=2 && neighbours <= 3 && gridOfLife[i][j].checkStatus()) {
					updateLife[i][j].update(true);
				} else {
					updateLife[i][j].update(false);
				}
			}
		}
		copyUpdateLife();
		print();
	}

	public static synchronized boolean checkFinish() {
		print();
		if (abc> 100) {
			return true;
		}
		abc++;
		// int count = 0;
		// for (int i=0; i<sizeOfGrid; i++) {
		// 	for (int j=0; j<sizeOfGrid; j++) {
		// 		// System.out.println(iteration + "this is iteration");
		// 		// System.out.println(gridOfLife[i][j].checkIteration() + "this is check");
		// 		if (iteration == gridOfLife[i][j].checkIteration()+1) {
		// 			count = count + gridOfLife[i][j].checkIteration()+1;
		// 			System.out.println(count + "\n");
		// 		}
		// 	}
		// }
		// // System.out.println("total count" + count);
		// int total = sizeOfGrid*sizeOfGrid*(iteration - 1);
		// if (count>=total) {
		// 	return true;
		// }
		return false;
	}

	public static void main(String[] args) throws Exception {
		if (args.length <  2) {
			throw new Exception("too few arguments");
		}

		// input format is N G S
		Integer n = Integer.parseInt(args[0]);
		Integer g = Integer.parseInt(args[1]);
		String file = args[2];

		// System.out.println(n);
		// System.out.println(g);
		// System.out.println(s);

		GameOfLife gol = new GameOfLife(n,g,file);
		gol.seed();
		// gol.print();
		// gol.runIterations();
		updateCell[] t1 = new updateCell[n*n];
		int k = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				t1[k] = new updateCell(k,i,j,g);
				k++;
			} 
		}

		for (int p = 0; p<k; p++) {
			t1[p].start();
		}

		for (int p = 0; p<k; p++) {
			t1[p].join();
		}
		// updateCell t1 = new updateCell(n);
		// updateCell t2 = new updateCell(n);

		// t1.start();
		// t2.start();
		
		// t1.join();
		// t2.join();

		gol.print();
	}
}