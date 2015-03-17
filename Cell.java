import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
	boolean alive;
	boolean calculatedValue;
	boolean calculated;
	boolean updated;

	int level;
	// public ReentrantLock lock = new ReentrantLock();


	public Cell(boolean status) {
		this.level = 0;
		alive = status;
		calculated = false;
		updated = false;
		calculatedValue = false;
	}

	public synchronized int getLevel() {
		return this.level;
	}

	public synchronized void setLevel() {
		this.level++;
	}

	public synchronized boolean getStatus() {
		return alive;
	}

	public synchronized void setStatus(boolean input) {
		alive = input;
	}

	public synchronized void setCalculatedValue(boolean input) {
		// this.calculated = true;
		this.calculatedValue = input;
	}

	public synchronized boolean getCalculatedValue() {
		return calculatedValue;
	}

	public synchronized void setCalculated(boolean input) {
		calculated = input;
	}

	public synchronized boolean getCalculated() {
		return calculated;
	}

	public synchronized void setUpdated(boolean input) {
		updated = input;
	}

	public synchronized boolean getUpdated() {
		return updated;
	}

	public void update(boolean update) {
		alive = update;
		level++;
	}

	public int checkIteration() {
		return level;
	}

	public boolean checkStatus() {
		return alive;
	}

	public synchronized void printCell() {
		if (alive) {
			System.out.print("x");
		} else{
			System.out.print(".");
		}
	}
}