import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class GameOfLife {
	Integer sizeOfGrid;
	Integer iteration;
	String seedFile;
	String path = "C:\\Users\\babar naveed\\Desktop\\ap_git\\15100142\\midTerm\\";
	public Cell[][] gridOfLife;

	public static int abc = 0;

	public GameOfLife(Integer sizeOfGrid, Integer iteration, String seedFile) {
		this.sizeOfGrid = sizeOfGrid;
		this.iteration = iteration;
		this.seedFile = path + seedFile;
		gridOfLife = new Cell[sizeOfGrid][sizeOfGrid];
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
 					if (s != '\n') {
 					Cell cell;
 					if (s == '.') {
 						cell = new Cell(false);
 					} else {
 						cell = new Cell(true);
 					}
 					gridOfLife[i][j] = cell;

 					j++;
 				}}
 				i++;
 				j = 0;
 				// System.out.println(currentLine);
 			}
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

	public void print() {
		for (int i=0; i<this.sizeOfGrid; i++) {
			for (int j=0; j<this.sizeOfGrid; j++) {
				gridOfLife[i][j].printCell();
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public Cell[] getNeighbours(int row, int col) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		// int count = 1;
		for (int i = row-1; i<= row+1; i++) {
			for (int j=col-1; j<= col+1; j++) {
				// System.out.println(count);
				if (i>=0 && j>=0 && i< sizeOfGrid && j< sizeOfGrid) {
					if (i != row || j != col) {
						// System.out.println(i+" "+j);
						cells.add(gridOfLife[i][j]);
					}
				}
				// count++;
			}
		}
		return cells.toArray(new Cell[cells.size()]);
	}

	public static void main(String[] args) throws Exception {
		if (args.length <  2) {
			throw new Exception("too few arguments");
		}

		// input format is N G S
		Integer n = Integer.parseInt(args[0]);
		Integer iterations = Integer.parseInt(args[1]);
		String file = args[2];

		// // System.out.println(n);
		// // System.out.println(g);
		// // System.out.println(s);

		GameOfLife gol = new GameOfLife(n,iterations,file);
		gol.seed();
		gol.print();
		// // gol.runIterations();

		updateCell[] t1 = new updateCell[n*n];
		int k = 0;

		// System.out.println(gol.getNeighbours(0,0).length);

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				t1[k] = new updateCell(gol.gridOfLife[i][j], gol.getNeighbours(i,j), iterations);
				k++;
			} 
		}

		for (int p = 0; p<k; p++) {
			t1[p].start();
		}

		for (int p = 0; p<k; p++) {
			t1[p].join();
		}

		gol.print();
	}
}