package com.revature.project0;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EntryClass {
	static Scanner in = null;
	public static void main(String[] args) {

		 int userChoice = menu();
		 switch (userChoice) {
	        case 1:
	        	in = new Scanner(System.in);
	        	System.out.println("Username:");
	        	String username = in.nextLine();
	            System.out.println("Password:");
	            String password = in.nextLine();
	            System.out.println(username+" "+password);
	            User u = new User(username,password);
	            System.out.println(u.getAccount().getAccNum());
	            try{
	            	ObjectOutputStream oos = new ObjectOutputStream(
							new FileOutputStream(u.getUsername()+".ser"));
	            	oos.writeObject(u); //Serialize
	            }catch(IOException e){
	            	e.printStackTrace();
	            }  
	            break;
	        case 2:
	        	in = new Scanner(System.in);
	            break;
	        case 3:
	            System.out.println("Perform withdraw case.");
	            break;
	        case 4:
	            System.out.println("Perform quit case.");
	            break;
	        default:
	            // The user input an unexpected choice.
	    }
	}


	
	public static int menu() {

        int selection;
        in = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - create an account");
        System.out.println("2 - deposit to account");
        System.out.println("3 - withdraw from account");
        System.out.println("4 - Quit");

        selection = in.nextInt();
        return selection;    
    }
	
	public void scannerMenu(){
		StringTokenizer st;
		int i;
		breakhere:
		while(true){		
			String input = in.nextLine();
			st = new StringTokenizer(input);
			while(st.hasMoreTokens()){
				String token = st.nextToken();
				try{
					i = Integer.parseInt(token);
					if(i == 5){
						break breakhere;
					}
					else{
						System.out.println("That's not the secret number!");
					}
				}catch(NumberFormatException e){
					System.out.println("YO, '" + token + "' isn't a number!");
				}
			}
			
		}	
		
		if(in != null){
			in.close();
		}
	}


}
