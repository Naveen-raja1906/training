package com.thread;

public class ThreadPriority {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(() -> {
			for(int i = 0;i<5;i++) {
				System.out.println("Hi " + Thread.currentThread().getPriority());
				try { Thread.sleep(500); }catch (Exception e) {e.printStackTrace();}
			}
		},"Hi Thread");
		
		Thread t2 = new Thread(() -> {
			for(int i = 0;i<5;i++) {
				System.out.println("Hello " + Thread.currentThread().getPriority());
				try { Thread.sleep(500); }catch (Exception e) {e.printStackTrace();}
			}
		},"Hello Thread");
		
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.setPriority(Thread.MIN_PRIORITY);
		
		t1.start();
		try { Thread.sleep(10); }catch (Exception e) {e.printStackTrace();}
		t2.start();
		
		System.out.println(t1.isAlive());
		
		try {
			t1.join(); //waits for the thread to die
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(t1.isAlive());
		System.out.println("bye");
	}
}
