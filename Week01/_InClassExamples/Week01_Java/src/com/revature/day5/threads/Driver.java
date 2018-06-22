package com.revature.day5.threads;

public class Driver {

	public static void main(String[] args) {
		/*
		 * With Threads, you create a Thread instance, and pass it our instance
		 * of a class implementing runnable, OR thread.
		 * Optionally, you can give it a name as well.
		 * When you finally want to fork the thread on its own path,
		 * you will use the start() method! <---important.
		 */
		
		Thread t1 = new Thread(new RunnableThread(), "Thread1");
		Thread t2 = new Thread(new ThreadThread(), "Thread2");
		System.out.println("BEGINNING THREAD EXECUTION");
		t1.start();
		t2.start();
		System.out.println("ENDING THREAD EXECUTION");
	}
	/*
	 * Notable Thread methods
	 * -sleep: Put the thread in a wait status (can be interrupted)
	 * -wait:  Put the thread in a wait status (can not)
	 * -notify: Wake up ONE of the sleeping threads.
	 * -notifyAll: Wake up all sleeping threads
	 * -isDaemon: Checks to see if a thread is a daemon thread.
	 * 	-NOTE: Daemon meaning the thread runs, even after the main
	 * 			thread has ended.
	 * -join(): Have a specific thread end execution and merge into
	 * 		another active thread.
	 * -isAlive: Checks if thread is finished yet
	 * -start: start the thread
	 * 
	 * 
	 * 
	 */
}
