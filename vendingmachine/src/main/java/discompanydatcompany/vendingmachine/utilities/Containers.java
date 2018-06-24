package discompanydatcompany.vendingmachine.utilities;

import java.util.ArrayList;
import java.util.HashMap;

import discompanydatcompany.vendingmachine.utilities.Container;

/**
 * maxCharacterCounterPerRow minimum is 60. 
 *
 */
public class Containers extends Input {
	
    private ArrayList<Container> containers;
    private int maxCharacterCountPerRow;

    public Containers(){
	this.containers = new ArrayList<Container>();
	this.maxCharacterCountPerRow = 60;
    }
    
    public Containers(int maxCharacterCountPerRow, ArrayList<Container> containers) {
 	this.containers = containers;
	this.maxCharacterCountPerRow = maxCharacterCountPerRow;
    }

    public Containers(int maxCharacterCountPerRow) {
       this.containers = new ArrayList<Container>();
       this.maxCharacterCountPerRow = maxCharacterCountPerRow;
    }

    public ArrayList<Container> getContainers() {
	return this.containers;
    }

    public void setContainers(ArrayList<Container> containers) {
	this.containers = containers;
    }

    public int getMaxCharacterCountPerRow() {
	return this.maxCharacterCountPerRow;
    }

    @Override
    public String toString(){
	StringBuffer stringBuffer = new StringBuffer();
       
	/**
	 * Divide maxCharacterCountPerRow across all textContainers 
	 *
	 */
	int maxCharacterCountPerContainer = this.maxCharacterCountPerRow / this.containers.size() - this.containers.size();
	char containerDividerSymbol = '|';
       
	for (Container container : this.containers) {
	    StringBuffer containerStringBuffer = new StringBuffer();
	    container.toArrayList().forEach(containerStringBuffer::append);
	    String stringContainer = containerStringBuffer.toString();
	    stringBuffer.append(Printing.rowLengthLimitedString(stringContainer, maxCharacterCountPerContainer, containerDividerSymbol));
	}
	return stringBuffer.toString();
    }
}
