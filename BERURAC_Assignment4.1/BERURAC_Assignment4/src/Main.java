import java.util.concurrent.Semaphore;

class Main extends Thread{
	
	static Semaphore s12 = new Semaphore(0);
	static Semaphore s23 = new Semaphore(0);
	static Semaphore s24 = new Semaphore(0);
	static Semaphore s34 = new Semaphore(0);
	static Semaphore s36 = new Semaphore(0);
	static Semaphore s54 = new Semaphore(0);
	static Semaphore s56 = new Semaphore(0);
	
static public void main( String[] args ){
 // make the boolean array of precedences
 // and the array of Runnable
boolean [][] precedences = { { false, true, false, false, false, false },
			 				 { false, false, true, true, false , false },
			 				 { false, false, false, true, false, true },
			 				 { false, false, false, false, false, false },
			 				 { false, false, false, true, false, true },
			 				 { false, false, false, false, false, false }};
	Runnable task1 = () -> {System.out.println("Step 1: Wash your hands.");
							s12.notify();	   
};
	Runnable task2 = () -> {try {
									s12.wait();
									} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							System.out.println("Step 2: Measure dry ingredients");							
};
	Runnable task3 = () -> {try {
									s23.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Step 3: Measure wet ingredients");
							s34.notify();
							s36.notify();
};
	Runnable task4 = () -> {try {
									s24.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								s34.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								s54.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Step 4: Mix wet and dry ingredients.");
							
};
	Runnable task5 = () -> {
							System.out.println("Step 5: Make icing and prepare decorations");
							s56.notify();
};
	Runnable task6 = () -> {try {
									s36.acquire();
							} catch (InterruptedException e) {
							// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								s56.acquire();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Cool and decorate your cake then slice it up.");
};
	Runnable tasks[] = {task1, task2, task3, task4, task5, task6
};
	
	
	// construct an Executor
	Executor cakeMaker = new Executor (precedences, tasks);
	// run it
	cakeMaker.run();
}
}