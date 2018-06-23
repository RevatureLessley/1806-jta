package com.revature.day5.threads;


/*
 * There are two different ways to create thread in java.
 * A thread is a simple flow of execution.
 * Multithreading is when multiple threads have branched odd of the main thread and
 * execute concurrently.
 * 
 * The goal of multithreading would be to share the work load among multile
 * threads in an effort to finish a task faster.
 * 
 * With threads, Java has two ways to create a Thread. These two ways include:
 * -IMPLEMENTING the runnable interface
 * -EXTENDING the THREAD class
 */
public class RunnableThread implements Runnable{

	/*
	 * In either case for creating a thread, you must implement the run()
	 * method. This method contains the tasks your separate thread will run.
	 * When you start the Thread, it will execute the run method and be on its
	 * merry way.
	 */
	
	@Override
	public void run() {
		for(int i = 0; i < 20; i++){
			System.out.println(Thread.currentThread().getName());
			try{
				Thread.sleep(50);
			}catch( InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
}
