package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* Serialize or sequentially execute one task at a time */ 
public class SingleThreadExecutorDemo {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		
		//sequentially execute one task at a time
		ExecutorService execService = Executors.newSingleThreadExecutor();
		
		execService.execute(new LoopTaskC());
		execService.execute(new LoopTaskC());
		execService.execute(new LoopTaskC());
		//execService.execute(new LoopTaskC());
		//execService.execute(new LoopTaskC());
		//execService.execute(new LoopTaskC());
		
		execService.shutdown();
		
		//execService.execute(new LoopTaskB());

		System.out.println("Main thread ends here ......");

	}

}



/* define the task to execute in separate thread */
class LoopTaskC implements Runnable {

	private static int count = 0;
	private int id;
	
	public LoopTaskC() {
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
