package com.revature.day5.threads;

public class ThreadThread extends Thread{
	/*
	 * The other way of creating a Thread is by extending the Thread class.
	 * Since Thread also implements runnable, this class will transitively do so as
	 * well.
	 * 
	 * So which one to use?
	 * Most developers go the path of implementing runnable. The reason for this is that
	 * by implementing runnable, we still, ourselves, have the option to extend a class.
	 * This is opposed to extending Thread where we are then blocked from extending
	 * anything else.
	 * Arguably, if you extend a class, you are intending to override base methods for
	 * better use, but typically, Thread is already built to be efficient, so chances are
	 * you will not be able to write a better implementation.
	 */
	
	@Override
	public void run() {
		for(int i = 0; i < 20; i++){
			System.out.println("\t\t" + Thread.currentThread().getName());
			try{
				Thread.sleep(50);
			}catch( InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
}
