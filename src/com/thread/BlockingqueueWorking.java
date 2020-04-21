package com.thread;

import java.util.LinkedList;
import java.util.List;

class BlockingQueue {
	  
    private List<Integer> queue = new LinkedList<Integer>(); 
    private int limit; 
  
    public BlockingQueue(int limit) 
    { 
        this.limit = limit; 
    } 
  
    public synchronized void enqueue(int item) 
        throws InterruptedException 
    { 
        while (this.queue.size() == this.limit) { 
        	System.out.println("Full Queue");
            wait(); 
        } 
        if (this.queue.size() == 0) { 
        	System.out.println("Empty Queue");
            notifyAll(); 
        } 
        System.out.println("enqueue : "+item);
        this.queue.add(item); 
    } 
  
    public synchronized void dequeue() 
        throws InterruptedException 
    { 
        while (this.queue.size() == 0) {
        	System.out.println("Empty Queue");
            wait(); 
        } 
        if (this.queue.size() == this.limit) {
        	System.out.println("Full Queue");
            notifyAll(); 
        } 
  
        System.out.println("dequeue : "+this.queue.remove(0));
    } 
} 

class Producerbq implements Runnable{
	BlockingQueue bq;
	
	public Producerbq(BlockingQueue bq) {
		this.bq = bq;
		Thread t = new Thread(this,"Producer");
		t.start();
	}
	
	public void run() {
		int i = 0;
		while(true) {
			try {
				bq.enqueue(i++);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}

class Consumerbq implements Runnable{
	BlockingQueue bq;
	
	public Consumerbq(BlockingQueue bq) {
		this.bq = bq;
		Thread t = new Thread(this,"Consumer");
		t.start();
	}
	
	public void run() {
		while(true) {
			try {
				bq.dequeue();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingqueueWorking{
	
	public static void main(String[] args) {
		BlockingQueue bq = new BlockingQueue(10);
		new Producerbq(bq);
		new Producerbq(bq);
		new Consumerbq(bq);
		new Consumerbq(bq);
	}
}