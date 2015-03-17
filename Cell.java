import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
	boolean alive;
	boolean calculatedValue;
	boolean calculated;

	int level;
	Lock lock = new ReentrantLock();


	public Cell(boolean status) {
		this.level = 0;
		alive = status;
	}

	public void update(boolean update) {
		alive = update;
		this.level++;
		this.calculated = false;
	}

	public void calculate(boolean newValue) {
		this.calculated = true;
		this.calculatedValue = newValue;
	}

	public int checkLevel() {
		return this.level;
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