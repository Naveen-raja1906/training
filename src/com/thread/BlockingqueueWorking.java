package com.thread;

/*
 * BlockingQueue is a thread safe data structure.
 */

import java.util.LinkedList;
import java.util.List;

class BlockingQueue {
	  
    public List<Integer> queue = new LinkedList<Integer>(); 
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
        	System.out.println("Queue Ele :");
        	for(int i:queue) {
				System.out.print(i+" ");
			}
            wait(); 
		} /*
			 * if (this.queue.size() == 0) { System.out.println("Empty Queue"); notifyAll();
			 * }
			 */
        System.out.println("enqueue : "+item+" : "+ Thread.currentThread().getName());
        this.queue.add(item); 
        notifyAll();
    } 
  
    public synchronized void dequeue() 
        throws InterruptedException 
    { 
        while (this.queue.size() == 0) {
        	System.out.println("Empty Queue");
            wait(); 
        } 
		/*
		 * if (this.queue.size() == this.limit) { System.out.println("Full Queue");
		 * notifyAll(); }
		 */
        System.out.println("dequeue : "+this.queue.remove(0)+" : "+ Thread.currentThread().getName());
        notifyAll();
    } 
} 

class Producerbq implements Runnable{
	BlockingQueue bq;
	
	public Producerbq(BlockingQueue bq,String name) {
		this.bq = bq;
		Thread t = new Thread(this,name);
		t.start();
	}
	
	public void run() {
		int i = 0;
		while(i<16) { // limit has set to analyze the Output
			try {
				bq.enqueue(i++);
				//Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}

class Consumerbq implements Runnable{
	BlockingQueue bq;
	
	public Consumerbq(BlockingQueue bq,String name) {
		this.bq = bq;
		Thread t = new Thread(this,name);
		t.start();
	}
	
	public void run() {
		while(true) {
			try {
				bq.dequeue();
				//Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingqueueWorking{
	
	public static void main(String[] args) {
		BlockingQueue bq = new BlockingQueue(10);
		try {
			new Producerbq(bq,"Producer1");
			new Producerbq(bq,"Producer2");
			new Consumerbq(bq,"Consumer1");
			new Consumerbq(bq,"Consumer2");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}