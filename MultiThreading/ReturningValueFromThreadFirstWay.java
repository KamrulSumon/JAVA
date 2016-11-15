package com.sumon.prog.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReturningValueFromThreadFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts here ......");

		ValueReturningTaskA task1 = new ValueReturningTaskA(2, 3, 2000);
		Thread t1 = new Thread(task1, "Thread-1");

		ValueReturningTaskA task2 = new ValueReturningTaskA(3, 4, 1000);
		Thread t2 = new Thread(task2, "Thread-2");

		ValueReturningTaskA task3 = new ValueReturningTaskA(4, 5, 500);
		Thread t3 = new Thread(task3, "Thread-3");

		t1.start();
		t2.start();
		t3.start();

		System.out.println("Result-1 = " + task1.getSum());
		System.out.println("Result-2 = " + task2.getSum());
		System.out.println("Result-3 = " + task3.getSum());

		System.out.println("[" + currentThreadName + "] Main thread ends here ......");

	}

}

/* Task that return value */
class ValueReturningTaskA implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private volatile boolean done = false;

	public ValueReturningTaskA(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;

		this.instanceNumber = ++count;
		this.taskId = "ValueReturnTaskA-" + instanceNumber;
	}

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("#### [" + currentThreadName + "[ <" + taskId + "> STARTING ####");
		System.out.println("[" + currentThreadName + "[ <" + taskId + "> sleeping for " + sleepTime + " millis");

		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sum = a + b;

		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> DONE *****");

		done = true;
		
		synchronized (this) {
			System.out.println("***** [" + currentThreadName + "] <" + taskId + "> NOTIFYING ....");
			this.notifyAll();
		}
	}

	public int getSum() {

		if (!done) {
			synchronized (this) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + "] === Waiting for result from " + taskId + " ...") ;
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("[" + Thread.currentThread().getName() + "] === WOKEN-UP for " + taskId + " ...") ;
		}
		return sum;
	}

}
