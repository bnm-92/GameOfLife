import java.util.*;

public class Support {
	int size;

	public Support (int s) {
		this.size = s;
	}

	public synchronized void cell_update(int i, int j) throws Exception {
		this.notifyAll();
		while(!checkCellNeighbour(i, j)) {
			this.notifyAll();	
			// yield();
			// System.out.println(this + "cell update");
			wait();
		}
		// if (checkCellNeighbour(i, j)) { // check them for calculated
			System.out.println("calculate");
			int neighbours = countNeighbours(i, j);
			if (neighbours < 2) {
					GameOfLife.updateLife[i][j].update(false);
				} else if (neighbours > 3) {
					GameOfLife.updateLife[i][j].update(false);
				} else if (neighbours == 3) {
					GameOfLife.updateLife[i][j].update(true);
				} else if (neighbours >=2 && neighbours <= 3 && GameOfLife.gridOfLife[i][j].checkStatus()) {
					GameOfLife.updateLife[i][j].update(true);
				} else {
					GameOfLife.updateLife[i][j].update(false);
				}
		this.notifyAll();
			// }

		while(!checkCellUpdate(i,j)) {
			// yield();
			this.notifyAll();
			wait();
		}
			System.out.println("update");
			GameOfLife.updateLife[i][j].reset();
			GameOfLife.gridOfLife[i][j].update(GameOfLife.updateLife[i][j].checkStatus());
		this.notifyAll();

		while(!calculateAndCopied(i,j)) {
			// yield();
			this.notifyAll();
			wait();
		}
		// if () {
			GameOfLife.updateLife[i][j].resetAll();
			
		// }
		this.notifyAll();
	}


	public  boolean checkCellNeighbour(int row, int col) throws Exception {
		int cur = GameOfLife.gridOfLife[row][col].getIteration();
		if(row >= size || col >= size || row < 0 || col < 0) {
			return false;
			// throw new Exception(row + " " +col  + " " + "out of bounds");
		}
		// System.out.println("past exception " + row + " " + col);
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				// System.out.println("in loop " + i + " " + j);
				if(i >= size || j >= size || i < 0 || j < 0) {
					count++;
				} else {
					if (cur == GameOfLife.gridOfLife[i][j].getIteration()) {
						if (i == row && j == col) {} else {
							// System.out.println(" " + i + " " + j + "  increment");
							count++;
						}
					}
				}
			}
		}
		if(count >= 8) {
			return true;
		}
		return false;
	}

	public  boolean checkCellUpdate(int row, int col) throws Exception {
		// System.out.println("cell update check");
		// int cur = GameOfLife.updateLife[row][col].getIteration();
		if(row >= size || col >= size || row < 0 || col < 0) {
			return false;
			// throw new Exception("out of bounds");
		}
		// System.out.println("past exception " + row + " " + col);
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				// System.out.println("in loop " + i + " " + j);
				if(i >= size || j >= size || i < 0 || j < 0) {
					count++;
				} else {
					if (GameOfLife.updateLife[i][j].getCalculated()) {
						if (i == row && j == col) {} else {
							// System.out.println(" " + i + " " + j + "  increment");
							count++;
						}
					}
				}
			}
		}
		// System.out.println(count);
		if(count == 8) {
			return true;
		}
		return false;	
	}

	public  boolean calculateAndCopied(int row, int col) {
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				// System.out.println("in loop " + i + " " + j);
				if(i >= size || j >= size || i < 0 || j < 0) {
					count++;
				} else {
					if (GameOfLife.updateLife[i][j].getAll() ) {
						if (i == row && j == col) {} else {
							// System.out.println(" " + i + " " + j + "  increment");
							count++;
						}
					}
				}
			}
		}
		// System.out.println(count);
		if(count == 8) {
			return true;
		}
		return false;	
	}

	public  int countNeighbours(int i,int j) throws Exception{
		return GameOfLife.calculateNeighbours(i,j);
	}
}