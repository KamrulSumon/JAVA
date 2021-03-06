package com.sumon.prog.threading;

import java.util.concurrent.TimeUnit;

public class FirstWayThread {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		new FirstTask();
		
		Thread t = new FirstTask();

		System.out.println("Main thread ends here ......");
	}

}

/* define the task to execute in separate thread */
class FirstTask extends Thread {

	private static int count = 0;
	private int id;
	
	public FirstTask() {
		this.id = ++count;
		this.start();
	}

	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println("<" + id + "> TICK TICK " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
