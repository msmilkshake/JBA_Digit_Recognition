package recognition;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    private static final String fileName = "network.ml";
    
    public static void serialize(NeuralNetwork network) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(network);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static NeuralNetwork deSerialize() {
        NeuralNetwork network = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            network = (NeuralNetwork) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return network;
    }
}
