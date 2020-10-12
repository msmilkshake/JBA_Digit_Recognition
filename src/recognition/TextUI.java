package recognition;

import java.util.Scanner;

public class TextUI {
    private final Scanner scn = new Scanner(System.in);
    private final int gens = 100;
    private NeuralNetwork network;
    
    public void start() {
        System.out.println("1. Learn the network\n" +
                "2. Guess a number\n" +
                "Your choice: ");
        String choice = scn.nextLine();
        switch (choice) {
            case "1":
                learn();
                break;
            case "2":
                loadNetwork();
                grid();
                break;
            default:
                break;
        }
    }
    
    private void loadNetwork() {
        network = Serialize.deSerialize();
    }
    
    private void learn() {
        System.out.println("Learning...");
        network = new NeuralNetwork(gens);
        Serialize.serialize(network);
        System.out.println("Done! Saved to the file.");
    }
    
    public void grid() {
        System.out.println("Input grid:");
        double[] input = new double[15];
        for (int row = 0; row < 5; ++row) {
            String inRow = scn.nextLine();
            for (int col = 0; col < 3; ++col) {
                if (inRow.charAt(col) == 'X' || inRow.charAt(col) == 'x') {
                    input[row * 3 + col] = 1.0;
                }
            }
        }
        int digit = network.recognize(input);
        System.out.println("The number is " + digit);
    }
}
