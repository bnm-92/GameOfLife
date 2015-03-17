import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
	boolean alive;
	boolean calculatedValue;
	boolean calculated;
	boolean updated;

	int level;
	public ReentrantLock lock = new ReentrantLock();


	public Cell(boolean status) {
		this.level = 0;
		alive = status;
	}

	public int getLevel() {
		return this.level;
	}

	public boolean getStatus() {
		return alive;
	}

	public void update(boolean update) {
		alive = update;
		this.level++;
	}

	public void calculate(boolean newValue) {
		this.calculated = true;
		this.calculatedValue = newValue;
	}

	public boolean getCalculated() {
		return calculated;
	}

	public boolean getCalculatedValue() {
		return calculatedValue;
	}

	public void resetCalculated() {
		this.calculated = false;
	}

	public void updatedValue(boolean input) {
		updated = input;
	}

	public boolean getUpdated() {
		return updated;
	}

	public void printCell() {
		if (alive) {
			System.out.print("x");
		} else{
			System.out.print(".");
		}
	}
}