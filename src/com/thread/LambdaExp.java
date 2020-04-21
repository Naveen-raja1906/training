package com.thread;
/*
 * Using Anonymous class and Lambda Expression 
 */
public class LambdaExp {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for(int i = 0;i<5;i++) {
				System.out.println("Hey");
				try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
			}
		});
		Thread t2 = new Thread(() -> {
			for(int i = 0;i<5;i++) {
				System.out.println("Where?");
				try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
			}
		});
		
		t1.start();
		try { Thread.sleep(5);}catch(Exception e) {e.printStackTrace();}
		t2.start();
	}
}
