package discompanydatcompany.vendingmachine.utilities;

import java.util.HashMap;

import discompanydatcompany.vendingmachine.utilities.Printing;

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
       int maxCharacterCountPerContainer = (this.getMaxCharacterCountPerRow() / this.namedContainers.size() - this.namedContainers.size()) + 1; // + 1 for additional newline
       char containerDividerSymbol = '|';
       String toString = "";
       int maxContainerLength = 0;
       int index = 0;
       ArrayList textLines = new ArrayList<String>();

       /* 
	* Get the max container size
	*
	*/
       for (Container container : this.getNamedContainerValues()) {
	   maxContainerLength = maxContainerLength > container.toArrayList().size() ? maxContainerLength : container.toArrayList().size();
	   System.out.println("container length: " + maxContainerLength);
       }
       textLines = new ArrayList<String>(maxContainerLength);
       
       for (Container container : this.getNamedContainerValues()) {
	   StringBuffer containerStringBuffer = new StringBuffer();
	   // container.toArrayList().forEach((String x) ->
	   // {

	   /**
	    *
	    * | container1text[0] || container2text[0] || container3text[0] |
	    * | container1text[1] || container2text[1] || container3text[0] |
	    *
	    */
	   for (int i = 0; i < container.toArrayList().size(); i++) {
	       String withoutNewLines = Printing.removeNewlines(container.toArrayList().get(i));
	       String padded = Printing.rightPadString(withoutNewLines, maxCharacterCountPerContainer - 3, ' ');
	       String margined = Printing.paddedString(padded, maxCharacterCountPerContainer - 2, containerDividerSymbol);
	       // String withNewlineAtTail = margined + "\n";
	       // containerStringBuffer.append(withNewlineAtTail);
	       //                                      });
	       if (textLines.size() <= i) {
		   textLines.add(margined);
	       } else {
		   String newInput = Printing.removeNewlines(String.valueOf(textLines.get(i))) + margined + "\n";
		   textLines.set(i, newInput);
	       }
	   }

	   String stringContainer;
	   if (containerStringBuffer.toString() == null) {
		   stringContainer = "";
	       } else {
		   stringContainer = containerStringBuffer.toString();
	   }
	       // Add stringContainer value to textLines array
	   
       }

       // todo:
       for (int i = 0; i < stringBuffer.toString().toCharArray().length; i++) {
	   stringBuffer.append(containerDividerSymbol);
       }
       
       return textLines.toString();
   }

}
