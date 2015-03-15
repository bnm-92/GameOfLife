import java.util.*;

public class Cell {
	boolean alive;
	int iteration;

	public Cell(boolean status) {
		iteration = 0;
		alive = status;
	}

	public synchronized void update(boolean update) {
		alive = update;
		iteration++;
	}

	public synchronized int checkIteration() {
		return iteration;
	}
}