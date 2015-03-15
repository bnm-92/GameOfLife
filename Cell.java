import java.util.*;

public class Cell {
	boolean alive;
	int iteration;

	public Cell(boolean status) {
		iteration = 0;
		alive = status;
	}

	public void update(boolean update) {
		alive = update;
		iteration++;
	}

	public int checkIteration() {
		return iteration;
	}

	public boolean checkStatus() {
		return alive;
	}

	public void printCell() {
		if (alive) {
			System.out.print("x");
		} else{
			System.out.print(".");
		}
	}
}