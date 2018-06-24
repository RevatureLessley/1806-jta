package com.revature.project0.monopoly;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class handles the serializing and deserializing of the GameState class.
 */
class GameStateSerializer {
    private static GameState gs = null;
    private static final String FILENAME = "src/main/resources/GameState.ser";

    /**
     * This method deserializes the data for the GameState and returns it.
     * @return the GameState object recovered from the serialization
     * @throws FileNotFoundException if there is no file containing the GameState info.
     */
    static GameState deserialize() throws FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            gs = (GameState) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e){ e.printStackTrace();}
        catch (FileNotFoundException e){throw e;}
        catch (IOException e) { e.printStackTrace(); }
        //TODO: modify these catches to be more informative

        //System.out.println("Deserializing class: "+gs.getClass()+"\n"+gs.toString());
        return gs;

    }

    /**
     * This method handles the serializing of the GameState, to be recovered at a later time.
     * @param gameState the GameState being saved
     */
    static void serialize(GameState gameState){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(gameState);
            oos.close();
        }
        catch (IOException e){e.printStackTrace();}

        //System.out.println("Serializing class: "+gameState.getClass()+"\n"+gameState.toString());

    }

}

