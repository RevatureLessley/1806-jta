package discompanydatcompany.vendingmachine.utilities;

import java.util.Scanner;

import discompanydatcompany.vendingmachine.utilities.Printing;

import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.Console;
    
public class Input {
    
    private ArrayList<String> history;
    private int historyRecordCapacity;
    private int histIndexPointer;
    
    public Input() {
		this.history = new ArrayList<String>();
		this.historyRecordCapacity = 20;
		this.histIndexPointer = 0;
    }

    public void getScanner() {
    	return ;
    }
    
    public ArrayList<String> getHistory() {
    	return this.history;
    }

    public void printHistory() {
		StringBuffer bf = new StringBuffer();
		int index = this.history.size() == this.historyRecordCapacity ? this.histIndexPointer + 1 % this.history.size() : this.history.size() - 1;
		for (int i = 0; i < this.history.size(); i++){
		    index = index + i % this.history.size();
		    System.out.println("\t\t" + this.history.get(i));
		}
		System.out.println("A review of the past " + this.history.size() + " input values.");
    }

    /**
     * Add input to history array list. If the history array list exceeds the capacity move the index pointer to the beginning.
     *
     */
    public void add(String input) {
		if (this.history.size() >= this.historyRecordCapacity) {
		    this.histIndexPointer = 0;
		    this.history.set(this.histIndexPointer, input);
		}
		this.history.add(input);
		this.histIndexPointer++;
    }

    public String next() {
		String input = "";
		this.add(input);
		return input;
    }
    
    
    public void passNext() {
    	return ;
    }
    public static String password() {
    	Console console = System.console();
    	String password = new String(console.readPassword("Enter password: "));
    	return password;
    }
    
    public static String password(String message) {
    	Console console = System.console();
    	String password = new String(console.readPassword(message));
    	return password;
    }
    
    public static void main(String[] args) {
	
    }
}
