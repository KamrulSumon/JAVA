package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* Create as many threads as number of tasks, reuse the thread once completed its task */ 
public class CachedThreadPoolDemo2 {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory2());
		
		execService.execute(new LoopTaskG());  //Task-1
		execService.execute(new LoopTaskG());  //Task-2
		execService.execute(new LoopTaskG());  //Task-3
		
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		execService.execute(new LoopTaskG());  //Task-4
		execService.execute(new LoopTaskG());  //Task-5
		execService.execute(new LoopTaskG());  //Task-6
		execService.execute(new LoopTaskG());  //Task-7
		execService.execute(new LoopTaskG());  //Task-8
		
		execService.shutdown();
		
		//execService.execute(new LoopTaskB());

		System.out.println("Main thread ends here ......");

	}

}



class  NamedThreadFactory2 implements ThreadFactory{

	private static int count = 0;
	private static String NAME = "MyThread-";
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, NAME + ++count);
		return t;
	}
	
}




/* define the task to execute in separate thread */
class LoopTaskG implements Runnable {

	private static int count = 0;
	private int instanceNumer;
	private String taskId;
	
	public LoopTaskG() {
		this.instanceNumer = ++count;
		taskId = "LoopTaskE " + this.instanceNumer;
	}

	@Override
	public void run() {
		//Thread.currentThread().setName("ThredName " + instanceNumer);
		String currentThreadName = Thread.currentThread().getName();
		
		//System.out.println("#### [" + currentThreadName + "] <" + taskId +"> STARTING ####");
		System.out.println("#### [" + Thread.currentThread().getName() + "] <" + taskId +"> STARTING ####");
		
		for (int i = 10; i > 0; i--) {
			//System.out.println("[" + currentThreadName + "] <" + taskId + "> TICK TICK " + i);
			System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> TICK TICK " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("**** [" + Thread.currentThread().getName() + "] <" + taskId + "> DONE *****");
	}

}
