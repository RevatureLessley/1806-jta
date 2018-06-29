package Project0_PartII;

public class Project0 implements ConsoleReference, LogReference{
	/**
	 * main() runs the RevatureBank application.
	 * @param args unused
	 */
	public static void main(String args[]){
		logger.debug("Project0/Project0.java: " + 
	                 "Application Started, entered main().");
		
  		if(console == null) {
   			System.err.print("Thank you for choosing RevatureBank. Please " + 
   							 "note that this is a console application. " + 
   					         "Please open your command prompt.");
			logger.info("Project0/Project0.java: " + 
   					    "User tried to run application in an IDE.");
  		}

  		else {
   			RevatureBank bank = RevatureBank.entrance;
   			bank.enter();
   			bank.exit();
  		}
  		
  		logger.debug("Project0/Project0.java: " + 
  		             "Application Ended, exiting main().");
  	}
}
