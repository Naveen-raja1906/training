package com.thread;
/*
 * Implementing Thread concept using Thread Class
 */
class Hi extends Thread{
	
	public void run() {
		for(int i = 0;i<5;i++) {
			System.out.println("Hi");
			try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
		}
	}
}

class There extends Thread{
	
	public void run() {
		for(int i = 0;i<5;i++) {
			System.out.println("There");
			try{Thread.sleep(1000);}catch (Exception e) {e.printStackTrace();}
		}
	}
}

public class Threadclass {
	
	public static void main(String[] args) {
		Hi hi = new Hi();
		There there = new There();
		
		hi.start();
		try { Thread.sleep(5);}catch(Exception e) {e.printStackTrace();}
		there.start();
	}
}
