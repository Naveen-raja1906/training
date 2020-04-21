package com.thread;
/*
 * Implementing Thread concept using Runnable Interface
 */
class Hello implements Runnable{
	
	public void run() {
		for(int i = 0;i<5;i++) {
			System.out.println("Hello");
			try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
		}
	}
}

class Here implements Runnable{
	
	public void run() {
		for(int i = 0;i<5;i++) {
			System.out.println("Here");
			try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
		}
	}
}

public class Runnableinterface {
	
	public static void main(String[] args) {
		Runnable Hello = new Hello();
		Runnable Here = new Here();
		
		Thread t1 = new Thread(Hello);
		Thread t2 = new Thread(Here);
		
		t1.start();
		try { Thread.sleep(5);}catch(Exception e) {e.printStackTrace();}
		t2.start();
	}
}
