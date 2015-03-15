import java.util.*;

public class CellM {
	// ArrayList<Boolean> alive = new ArrayList<Boolean>();
	// ArrayList<Integer> iteration = new ArrayList<Integer>();
	int itr = 0;
	boolean alive;
	boolean updated = false;
	boolean calculated = false;
	boolean copied = false;

	public CellM(boolean status) {
		alive = status;
	}

	public  void update(boolean update) {
		alive = update;//.add(update);
		// updated = true;
		if (!updated) {
			updated = true;
			itr++;
		}

		if (!calculated) {
			calculated = true;
		}
		
	}

	public  void reset() {
		copied = true;
		// updated = false;
	}

	public  boolean getUpdate() {
		return updated;
	}

	public  boolean getCalculated() {
		return calculated;
	}

	public  int getIteration() {
		return itr;
	}

	public  boolean checkStatus() {
		return alive;//.get(this.itr);
	}

	public  boolean getAll() {
		return (copied && updated);
	}

	public  void resetAll() {
		updated = false;
		calculated = false;
		copied = false;
	}

	public void printCell() {
		if (alive) {
			System.out.print("x");
		} else{
			System.out.print(".");
		}
	}
}