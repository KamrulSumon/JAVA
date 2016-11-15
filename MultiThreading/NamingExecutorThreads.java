package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class NamingExecutorThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts here ......");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		execService.execute(new LoopTaskF());
		execService.execute(new LoopTaskF());
		execService.execute(new LoopTaskF());

		System.out.println("[" + currentThreadName + "] Main thread ends here ......");

	}

}


class  NamedThreadFactory implements ThreadFactory{

	private static int count = 0;
	private static String NAME = "MyThread-";
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, NAME + ++count);
		return t;
	}
	
}




/* define the task to execute in separate thread */
class LoopTaskF implements Runnable {

	private static int count = 0;
	private int instanceNumer;
	private String taskId;
	
	public LoopTaskF() {
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

