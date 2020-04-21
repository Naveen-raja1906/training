package com.thread;

/*
 * Here Consumer thread wait for the Producer thread to set value then tries to get the value
 * 
 * valueSet is a boolean check: True -- get(); False -- set() 
 */

class Q{
	int num;
	boolean valueSet;
	
	public synchronized void get() {
		while(!valueSet) {
			try{wait();}catch(Exception e) {e.printStackTrace();}
		}
		System.out.println("get : "+ num);
		valueSet = false;
		notify();
	}
	
	public synchronized void set(int num) {
		while(valueSet) {
			try{wait();}catch(Exception e) {e.printStackTrace();}
		}
		System.out.println("set : "+num);
		this.num = num;
		valueSet = true;
		notify();
	}
}

class Producer implements Runnable{
	Q q ;

	public Producer(Q q) {
		this.q = q;
		Thread t = new Thread(this,"Producer");
		t.start();
	}
	
	public void run() {
		int i = 0;
		while(true) {
			q.set(i++);
			try {Thread.sleep(1000);} catch(Exception e) {e.printStackTrace();}
		}
	}
	
}

class Consumer implements Runnable{
	Q q ;

	public Consumer(Q q) {
		this.q = q;
		Thread t = new Thread(this,"Consumer");
		t.start();
	}
	
	public void run() {
		while(true) {
			q.get();
			try {Thread.sleep(1000);} catch(Exception e) {e.printStackTrace();}
		}
	}
	
}

public class InterThreadCom {
	
	public static void main(String[] args) {
		
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}
}
