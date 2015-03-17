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
		// System.out.println(this.getName() + " starting thread");
		try {
			int itrs = 0;
			while(itrs < this.iterations) {
				runIteration();
				// runIteration(itrs);
				// runIteration(itrs);
				itrs++;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void runIteration() throws Exception {
		int op = 0;
		
		while(op < 3) {
			// cell.setUpdated(false);// this.sleep(2000);
			// cell.setLevel();
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
					// count++;
				}
			}
		}
	}

	public boolean checkNeighbours() throws Exception {
		// cell.lock.lock();
		int curLevel = cell.getLevel();
		// cell.lock.unlock();
		int count = 0;
		for (Cell n : neighbours) {
			// n.lock.lock();
			if (n.getLevel() == curLevel) {
				count++;
			}
			// n.lock.unlock();
		}

		if (count == neighbours.length) {
			this.calculateSelf();
			cell.setCalculated(true);
			return true;
		}
		return false;
	}

	public void calculateSelf() throws Exception {
		// cell.lock.lock();
		cell.setUpdated(false);
		this.myState = cell.getStatus();
		this.aliveNeighbours = 0;
		// int count = 0;
		for (Cell n : neighbours) {
			// n.lock.lock();
			if (n.getStatus()) {
				this.aliveNeighbours++;
			}

			// n.lock.unlock();
		}		
		int neighbours = this.aliveNeighbours;
		if (neighbours < 2) {
			cell.setCalculatedValue(false);
		} else if (neighbours > 3) {
			cell.setCalculatedValue(false);
		} else if (neighbours == 3) {
			cell.setCalculatedValue(true);
		} else if (neighbours >=2 && neighbours <= 3 && myState) {
			cell.setCalculatedValue(true);
		} else {
			cell.setCalculatedValue(false);
		}
		// cell.lock.unlock();
	}

	public boolean updateSelf() {
		// cell.resetUpdated();
		// asdasfadsd
		int count = 0;
		for (Cell n : neighbours) {
			// n.lock.lock();
			if (n.getCalculated()) {
				count++;
			}
			// n.lock.unlock();
		}

		if (count == neighbours.length) {
			// cell.lock.lock();
			cell.setStatus(cell.getCalculatedValue());
			cell.setUpdated(true);
			// cell.lock.unlock();
			return true;
		}

		return false;
	}

	public boolean reset() {
		int count = 0;
		for (Cell n : neighbours) {
			// n.lock.lock();
			if(n.getUpdated()) {
				count++;
			}
			// n.lock.unlock();
		}

		if (count == neighbours.length) {
			cell.setCalculated(false);
			cell.setLevel();
			// cell.lock.lock();
			// cell.resetCalculated();
			// cell.resetUpdated();
			// cell.lock.unlock();
			return true;
		}
		return false;
	}

}
