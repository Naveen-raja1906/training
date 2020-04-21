package com.thread;

/*
 * Synchronized methods are Thread Safe ie at any given time only one thread can execute the method 
 */

class Counter{
	int counter;
	public synchronized void increment() {
		counter++;
	}
}

public class Synchronized {

	public static void main(String[] args) throws Exception{
		
		Counter c = new Counter();
		
		Thread t1 = new Thread(() -> {
			for(int i = 0;i<1000;i++) {
				c.increment();
			}
		});
		
		Thread t2 = new Thread(() -> {
			for(int i = 0;i<1000;i++) {
				c.increment();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Count : "+c.counter);
	}

}
