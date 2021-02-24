public class Executor extends Thread{
		
		private boolean [][] precedences2;
		private Runnable[] tasks2;

		public Executor(boolean[][] precedences, Runnable[] tasks) {
			for(int i=0; i<precedences.length;i++) {
				for(int j=0;j<precedences.length;j++)
					precedences[i][j] = precedences2[i][j];
			}
			for(int l=0; l<precedences.length;l++)
				tasks[l] = tasks2[l];
		}

		public void run() {
			for(int k=0; k<tasks2.length; k++) {
				new Thread(tasks2[k]).start();
				try {
	                sleep((int)(Math.random() * 1000));
	            } catch (InterruptedException e) {}
	            System.out.println("Step " + k + " is done!"); 
	        }
		}
	}