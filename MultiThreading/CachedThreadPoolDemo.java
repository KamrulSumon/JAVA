package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* Create as many threads as number of tasks, reuse the thread once completed its task */ 
public class CachedThreadPoolDemo {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		
		ExecutorService execService = Executors.newCachedThreadPool();
		
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		
		execService.shutdown();
		
		//execService.execute(new LoopTaskB());

		System.out.println("Main thread ends here ......");

	}

}


/* define the task to execute in separate thread */
class LoopTaskB implements Runnable {

	private static int count = 0;
	private int id;
	
	public LoopTaskB() {
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
