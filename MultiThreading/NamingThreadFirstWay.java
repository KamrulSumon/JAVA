package com.sumon.prog.threading;

import java.util.concurrent.TimeUnit;

public class NamingThreadFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here ......");
		
		new Thread(new LoopTaskD()).start();
		
		Thread t2 = new Thread(new LoopTaskD());
		t2.start();
		
		System.out.println("[" + currentThreadName + "] Main thread ends here ......");

	}

}


/* define the task to execute in separate thread */
class LoopTaskD implements Runnable {

	private static int count = 0;
	private int instanceNumer;
	private String taskId;
	
	public LoopTaskD() {
		this.instanceNumer = ++count;
		taskId = "LoopTaskD " + this.instanceNumer;
	}

	@Override
	public void run() {
		//Thread.currentThread().setName("ThredName " + instanceNumer);
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("#### [" + currentThreadName + "] <" + taskId +"> STARTING ####");
		
		for (int i = 10; i > 0; i--) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> TICK TICK " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("**** [" + currentThreadName + "] <" + taskId + "> DONE *****");
	}

}
