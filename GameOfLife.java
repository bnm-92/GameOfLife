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

 			while((currentLine = br.readLine()) != null) {
 				System.out.println(currentLine);
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
	}
}