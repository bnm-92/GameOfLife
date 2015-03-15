import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {
	Integer sizeOfGrid;
	Integer iteration;
	String seedFile;
	String path = "C:\\Users\\babar naveed\\Desktop\\ap_git\\15100142\\midTerm\\";
	Cell[][] gridOfLife;

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
 					// System.out.println(s);
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
 			// System.out.println();
 			// System.out.println();
 			// //checing read
 			// for (int k=0; k<sizeOfGrid; k++) {
 			// 	for(int l=0; l<sizeOfGrid; l++) {
 			// 		gridOfLife[k][l].printCell();
 			// 	}
 			// 	System.out.println();
 			// }

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
		for (int i=0; i<sizeOfGrid; i++) {
			for (int j=0; j<sizeOfGrid; j++) {
				gridOfLife[i][j].printCell();
			}
			System.out.println();
		}
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
		gol.print();
	}
}