package projectzero.utilities;

import java.util.HashMap;
import java.util.ArrayList;

public class NamedContainers extends Containers {

    HashMap<String, Container> namedContainers;
    
    public NamedContainers() {
	this.namedContainers = new HashMap<String, Container>();
    }
    
    public NamedContainers(HashMap<String, Container> namedContainers) {
	this.namedContainers = namedContainers;
    }

    public NamedContainers(int maxCharacterCountPerRow, HashMap<String, Container> namedContainers) {
	super(maxCharacterCountPerRow, null);
	this.namedContainers = namedContainers;
    }
    
    public ArrayList<Container> getNamedContainerValues() {
	ArrayList<Container> result = new ArrayList<Container>(this.namedContainers.values());
	/*
	for (Container container : this.namedContainers.values()) {
	    result.add(container);
	}
	*/
	return result;
    }
    
   @Override
   public String toString(){
       StringBuffer stringBuffer = new StringBuffer();
       
       /**
	* Divide maxCharacterCountPerRow across all textContainers 
	*
	*/
       int maxCharacterCountPerContainer = this.getMaxCharacterCountPerRow() / this.namedContainers.size() - this.namedContainers.size();
       char containerDividerSymbol = '|';
       String toString = "";
       
       for (Container container : this.getNamedContainerValues()) {
	   StringBuffer containerStringBuffer = new StringBuffer();
	   container.toArrayList().forEach(containerStringBuffer::append);
	   String stringContainer = containerStringBuffer.toString();
       }

       // todo:
       for (int i = 0; i < stringBuffer.toString().toCharArray().length; i++) {
	   stringBuffer.append(containerDividerSymbol);
       }
       
       return stringBuffer.toString();
   }

}
