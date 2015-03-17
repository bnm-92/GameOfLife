import java.util.*;

public class updateCell extends Thread {

	Cell cell;
	Cell[] neighbours;
	int iterations;

	int aliveNeighbours = 0;
	boolean myState = false;

	public updateCell(Cell cell, Cell[] neighbours, int iterations) {
		this.cell = cell;
		this.neighbours = neighbours;
		this.iterations = iterations;
	}

	public void run() {
		try {
			int itrs = 0;
			while(itrs < this.iterations) {
				itrs = runIteration(itrs);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int runIteration(int count) throws Exception {
		int op = 0;
		while(op < 3) {
			if ( op == 0) {
				if(checkNeighbours()){
					op++;						
				}
			}

			if ( op == 1) {
				if(updateSelf()){
					op++;
				}
			}

			if ( op == 2) {
				if (reset()) {
					op++;
				}
			}
		}
		return (count++);
	}

	public boolean checkNeighbours() throws Exception {
		cell.lock.lock();
		int curLevel = cell.getLevel();
		this.myState = cell.getStatus();
		cell.lock.unlock();
		int count = 0;
		this.aliveNeighbours = 0;
		for (Cell n : neighbours) {
			n.lock.lock();
			if (n.getLevel() == curLevel) {
				count++;
			}
			if (n.getStatus()) {
				this.aliveNeighbours++;
			}

			n.lock.unlock();
		}

		if (count == neighbours.length) {
			this.calculateSelf();
			return true;
		}
		return false;
	}

	public void calculateSelf() throws Exception {
		cell.lock.lock();
		int neighbours = this.aliveNeighbours;
		if (neighbours < 2) {
			cell.calculate(false);
		} else if (neighbours > 3) {
			cell.calculate(false);
		} else if (neighbours == 3) {
			cell.calculate(true);
		} else if (neighbours >=2 && neighbours <= 3 && myState) {
			cell.calculate(true);
		} else {
			cell.calculate(false);
		}
		cell.updatedValue(false);
		cell.lock.unlock();
	}

	public boolean updateSelf() {
		int count = 0;
		for (Cell n : neighbours) {
			n.lock.lock();
			if (n.getCalculated()) {
				count++;
			}
			n.lock.unlock();
		}

		if (count == neighbours.length) {
			cell.lock.lock();
			cell.update(cell.getCalculatedValue());
			cell.updatedValue(true);
			cell.lock.unlock();
			return true;
		}

		return false;
	}

	public boolean reset() {
		int count = 0;
		for (Cell n : neighbours) {
			n.lock.lock();
			if(n.getUpdated()) {
				count++;
			}
			n.lock.unlock();
		}

		if (count == neighbours.length) {
			cell.lock.lock();
			cell.resetCalculated();
			cell.lock.unlock();
			return true;
		}
		return false;
	}

}
