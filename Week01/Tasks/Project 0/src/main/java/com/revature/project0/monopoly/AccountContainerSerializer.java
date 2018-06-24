package com.revature.project0.monopoly;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import static com.revature.project0.monopoly.LogWrapper.Severity.DEBUG;


/**
 * This class handles the serializing of the Accounts via the AccountContainer
 */
class AccountContainerSerializer {
    private static AccountContainer cont = null;
    private final static String FILENAME = "src/main/resources/AccountContainer.ser";

    /**
     * This method deserializes the AccountContainer and returns it.
     * @return the AccountContainer containing the list of Accounts
     * @throws FileNotFoundException if there is no file containing the serialized data.
     */
    static AccountContainer deserialize() throws FileNotFoundException{
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            cont = (AccountContainer) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e){ LogWrapper.log(AccountContainerSerializer.class, e);}
        catch (FileNotFoundException e){throw e;}
        catch (IOException e) { LogWrapper.log(AccountContainerSerializer.class, e); }
        //TODO: modify these catches to be more informative

        LogWrapper.log(AccountContainerSerializer.class, "Returning AccountContainer " + cont, DEBUG);
        //System.out.println("Deserializing class: "+cont.getClass()+"\n"+cont.toString());
        return cont;

    }

    /**
     * This method handles the serializing of the Accounts via the AccountContainer
     * @param container the AccountContainer (containing Accounts) to be serialized.
     */
    static void serialize(AccountContainer container){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(container);
            oos.close();
        }
        catch (IOException e){LogWrapper.log(AccountContainerSerializer.class, e);}
        LogWrapper.log(AccountContainerSerializer.class, "AccountContainer serialized.", DEBUG);

        //System.out.println("Serializing class: "+container.getClass()+"\n"+container.toString());

    }

}
