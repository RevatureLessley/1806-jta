package com.revature.project0.monopoly;

import java.io.*;

public class AccountContainerSerializer implements Serializable{
    private static AccountContainer cont = null;
    private final static String FILENAME = "AccountContainer.ser";

    public static AccountContainer deserialize() throws IOException{
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            cont = (AccountContainer) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e){ e.printStackTrace();}
        //catch (FileNotFoundException e){throw e;}
        catch (IOException e) { e.printStackTrace(); }
        //TODO: modify these catches to be more informative
        return cont;

    }

    public static void serialize(AccountContainer container){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(container);
            oos.close();
        }
        catch (IOException e){e.printStackTrace();}

    }
}
