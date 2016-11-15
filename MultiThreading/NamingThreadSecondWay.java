package com.sumon.prog.threading;

import java.util.concurrent.TimeUnit;

public class NamingThreadSecondWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here ......");
		
		new Thread(new LoopTaskE(), "MyThread-1").start();
		
		Thread t2 = new Thread(new LoopTaskE());
		//t2.setName("MyThread-2");
		t2.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.setName("MyThread-2");
		
		System.out.println("[" + currentThreadName + "] Main thread ends here ......");

	}

}



/* define the task to execute in separate thread */
class LoopTaskE implements Runnable {

	private static int count = 0;
	private int instanceNumer;
	private String taskId;
	
	public LoopTaskE() {
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
