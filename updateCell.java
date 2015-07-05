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
				runIteration();
				itrs++;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void runIteration() throws Exception {
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
	}

	public boolean checkNeighbours() throws Exception {
		int curLevel = cell.getLevel();
		int count = 0;
		for (Cell n : neighbours) {
			if (n.getLevel() == curLevel) {
				count++;
			}
		}

		if (count == neighbours.length) {
			this.calculateSelf();
			cell.setCalculated(true);
			return true;
		}
		return false;
	}

	public void calculateSelf() throws Exception {
		cell.setUpdated(false);
		this.myState = cell.getStatus();
		this.aliveNeighbours = 0;
		for (Cell n : neighbours) {
			if (n.getStatus()) {
				this.aliveNeighbours++;
			}
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
	}

	public boolean updateSelf() {
		int count = 0;
		for (Cell n : neighbours) {
			if (n.getCalculated()) {
				count++;
			}
		}

		if (count == neighbours.length) {
			cell.setStatus(cell.getCalculatedValue());
			cell.setUpdated(true);
			return true;
		}

		return false;
	}

	public boolean reset() {
		int count = 0;
		for (Cell n : neighbours) {
			if(n.getUpdated()) {
				count++;
			}
		}

		if (count == neighbours.length) {
			cell.setCalculated(false);
			cell.setLevel();
			return true;
		}
		return false;
	}
}
