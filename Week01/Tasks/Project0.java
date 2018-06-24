package Tasks;

import java.io.*;

public class Project0 implements ConsoleReference{
	public static void main(String args[]){

  		if(console == null) {
   			System.err.print("Thank you for choosing RevatureBank. ");
   			System.err.print("Please note that this is a console application. ");
   			System.err.print("Please exit out of your IDE, including 'git bash', ");
			System.err.println("and open your command prompt.");
  		}

  		else {
   			RevatureBank bank = RevatureBank.entrance;
   			bank.enter();
   			bank.exit();
  		}
  	}
}
