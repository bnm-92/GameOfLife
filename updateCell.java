import java.util.*;

public class updateCell extends Thread {

	int size;
	int i;
	int j;
	int total;
	
	public static Support s = new Support(GameOfLife.sizeOfGrid);

	public updateCell(int total, int i, int j, int itr) {
		size = total;
		this.i = i;
		this.j = j;
		this.total = itr;
	}


	public void run() {
		System.out.println(this.getName());
		int count = 0;
		while(count < total){
			try{
				count++;
				runIteration();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void runIteration() throws Exception {
			s.cell_update(this.i,this.j);
	}

	

}