package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* Create fixed number of threads, reuse the threads once free */ 
public class FixedThreadPoolDemo {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		
		ExecutorService execService = Executors.newFixedThreadPool(6);
		
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		
		execService.shutdown();
		
		execService.execute(new LoopTaskA());

		System.out.println("Main thread ends here ......");

	}

}


/* define the task to execute in separate thread */
class LoopTaskA implements Runnable {

	private static int count = 0;
	private int id;
	
	public LoopTaskA() {
		this.id = ++count;
	}

	@Override
	public void run() {
		System.out.println("#### <TASK-" + id + "> STARTING");
		
		for (int i = 10; i > 0; i--) {
			System.out.println("<TASK-" + id + "> TICK TICK " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("**** <TASK-" + id + "> DONE *****");
	}

}
