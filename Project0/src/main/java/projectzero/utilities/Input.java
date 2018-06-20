package projectzero.utilities;

import java.util.Scanner;
import java.util.ArrayList;

public class Input {
    private final Scanner scanner;
    private ArrayList<String> history;
    private int historyRecordCapacity;
    private int histIndexPointer;
    
    public Input() {
	this.scanner = new Scanner(System.in);
	this.history = new ArrayList<String>();
	this.historyRecordCapacity = 20;
	this.histIndexPointer = 0;
    }

    public Scanner getScanner() {
	return this.scanner();
    }

    public String getInput() {
	this.add(scanner.next());
    }
    
    public ArrayList<String> getHistory() {
	return this.history();
    }
    
    private void add() {
	if (this.history.size() >= this.historyRecordCapacity || this.histIndexPointer >= this.historyRecordCapacity) {

	}
    }
}
