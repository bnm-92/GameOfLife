import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class GameOfLife {
	public Integer sizeOfGrid;
	public Integer iteration;
	String seedFile;
	String path = "C:\\Users\\babar naveed\\Desktop\\ap_git\\15100142\\midTerm\\";
	public CellM[][] gridOfLife;

	public static int abc = 0;

	public GameOfLife(Integer sizeOfGrid, Integer iteration, String seedFile) {
		this.sizeOfGrid = sizeOfGrid;
		this.iteration = iteration;
		this.seedFile = path + seedFile;
		gridOfLife = new CellM[sizeOfGrid][sizeOfGrid];
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

 			for (int a=0; a<sizeOfGrid; a++) {
 				for (int b=0; b<sizeOfGrid; b++) {
 					CellM cell = new CellM(true);
 					updateLife[a][b] = cell;
 				}
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

	public static void main(String[] args) throws Exception {
		if (args.length <  2) {
			throw new Exception("too few arguments");
		}

		// input format is N G S
		// Integer n = Integer.parseInt(args[0]);
		// Integer g = Integer.parseInt(args[1]);
		// String file = args[2];

		// // System.out.println(n);
		// // System.out.println(g);
		// // System.out.println(s);

		// GameOfLife gol = new GameOfLife(n,g,file);
		// gol.seed();
		// // gol.print();
		// // gol.runIterations();
		// updateCell[] t1 = new updateCell[n*n];
		// int k = 0;
		// for (int i=0; i<n; i++) {
		// 	for (int j=0; j<n; j++) {
		// 		t1[k] = new updateCell(k,i,j,g);
		// 		k++;
		// 	} 
		// }

		// for (int p = 0; p<k; p++) {
		// 	t1[p].start();
		// }

		// for (int p = 0; p<k; p++) {
		// 	t1[p].join();
		// }
		// // updateCell t1 = new updateCell(n);
		// // updateCell t2 = new updateCell(n);

		// // t1.start();
		// // t2.start();
		
		// // t1.join();
		// // t2.join();

		// gol.print();
	}
}