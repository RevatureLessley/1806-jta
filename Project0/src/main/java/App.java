package projectzero;

import java.util.HashMap;
import java.util.ArrayList;

import projectzero.entities.VendingMachine;
import projectzero.utilities.Printing;
import projectzero.utilities.Input;
import projectzero.utilities.Container;
import projectzero.utilities.NamedContainers;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    
    public String getGreeting() {
        return "\"Hello world.\" -- Vending Machine.";
    }

    public static void main(String[] args) {
	VendingMachine vend = new VendingMachine("Le Vending Spot", "Mr. Edwards", "An arbitrary forest in Northern New England. This vending machine is comfortably tucked under a rocky alcove. 1368 A.D.");
	Input input = new Input();
	Container textInput = new Container(new ArrayList<String>());
	Container staticContent = new Container(new ArrayList<String>());
	Container textOutput = new Container(new ArrayList<String>());
	String userInput = "";
	HashMap<String, Container> consoleContentMap = new HashMap<String, Container>();
	NamedContainers consoleContainer = new NamedContainers(120, consoleContentMap);
	
	// Add content to consoleContentMap
	consoleContentMap.put("input", textInput);
	consoleContentMap.put("output", textOutput);
	consoleContentMap.put("staticContent", staticContent);
	
	staticContent.add(vend.toString());
        staticContent.add(new App().getGreeting());
	System.out.println(consoleContainer.toString());
	shell:
	while ((userInput = input.next()) != "close") {
	    switch(userInput) {
		default:
		    textInput.add("=>" + userInput);
		    System.out.println(consoleContainer.toString());
		    break;
	        case "history":
		    input.printHistory();
		    break;
	        case "exit":
		    break shell;
	    }
	}
    }
}
