package com.sumon.prog.threading;

import java.util.concurrent.TimeUnit;

public class FifthWayInline {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ......");
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 10; i > 0; i--) {
					System.out.println("TICK TICK Inline " + i);

					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}); //.start();
		
		t.start();

		System.out.println("Main thread ends here ......");

	}

}
