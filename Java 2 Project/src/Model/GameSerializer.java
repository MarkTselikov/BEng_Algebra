package Model;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSerializer implements Serializable {

    private static final String FILE_PATH = "game.ser";


    public static void saveGame(GameState game) {

        try (ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            stream.writeObject(game);
        } catch (IOException ex) {
            Logger.getLogger(GameSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static GameState loadGame() {

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {

            Object object = inStream.readObject();

            if (object instanceof GameState) {
                return (GameState) object;
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(GameSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
