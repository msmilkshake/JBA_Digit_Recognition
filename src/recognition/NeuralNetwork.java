package recognition;

import java.util.Random;

public class NeuralNetwork {
    private int gens;
    private double[][] weights = new double[10][16];
    private double[] biases = new double[10];
    private double[] outputNeurons = new double[10];
    private final double learnRateCoeff = 0.5;
    
    private final int[][] idealNeurons = {
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}
    };
    
    public NeuralNetwork(int gens) {
        this.gens = gens;
        Random r = new Random();
        for (int i = 0; i < 10; ++i) {
            biases[i] = r.nextGaussian();
            for (int j = 0; j < 16; ++j) {
                weights[i][j] = r.nextGaussian();
            }
        }
        process();
    }
    
    private void process() {
        for (; gens > 0 ; --gens) {
            for (int i = 0; i < 10; ++i) {
                updateWeights(i);
            }
        }
        new TextUI(weights, biases).start();
    }
    
    
    private void updateWeights(int n) {
        double[] neuronOutputs = new double[10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 15; ++j) {
                neuronOutputs[i] += weights[i][j] * idealNeurons[n][j];
            }
            neuronOutputs[i] += biases[i];
            neuronOutputs[i] = sigmoid(neuronOutputs[i]);
        }
        double[][] weightVariations = new double[15][10];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 10; ++j) {
                weightVariations[i][j] = learnRateCoeff * idealNeurons[n][i]
                        * (idealNeurons[j][i] - neuronOutputs[j]);
            }
        }
        double[] weightMeanVariations = new double[15];
        for (int i = 0; i < 15; ++i) {
            double sum = 0.0;
            for (int j = 0; j < 10; ++j) {
                sum += weightVariations[i][j];
            }
            weightMeanVariations[i] = sum / 10.0;
        }
        for (int i = 0; i < 15; ++i) {
            weights[n][i] += weightMeanVariations[i];
        }
    }
    
    private static double sigmoid(double x) {
        return 1.0 / (1 + Math.pow(Math.E, -x));
    }
    
    public static void main(String[] args) {
        new NeuralNetwork(100000);
    }
}
