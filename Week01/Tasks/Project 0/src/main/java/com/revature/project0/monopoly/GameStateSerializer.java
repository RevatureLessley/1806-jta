package com.revature.project0.monopoly;

import java.io.*;

public class GameStateSerializer {
    private static GameState gs = null;
    private static final String FILENAME = "GameState.ser";
    public static GameState deserialize() throws FileNotFoundException {
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

    public static void serialize(GameState gameState){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(gameState);
            oos.close();
        }
        catch (IOException e){e.printStackTrace();}

        //System.out.println("Serializing class: "+gameState.getClass()+"\n"+gameState.toString());

    }

}

