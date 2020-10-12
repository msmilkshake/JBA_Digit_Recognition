package recognition;

import java.io.Serializable;
import java.util.List;

import static recognition.Matrix.*;

public class NeuralNetwork implements Serializable {
    
    private static final long serialVersionUID = 2L;
    private static final int ins = 15;
    private static final int outs = 10;
    
    private int gens;
    private double[][] weights = new double[ins][outs];
    private double[][] biases = new double[outs][1];
    private final double learnRate = 0.5;
    
    private final List<TrainingSample> trainingSamples =
            TrainingData.getFromFile("TrainingData.csv");
    
    private final double[][] idealOuts = Matrix.identity(outs);
    
    public NeuralNetwork(int gens) {
        this.gens = gens;
        gaussianRand(weights);
        gaussianRand(biases);
        process();
    }
    
    private void process() {
        for (; gens > 0; --gens) {
            updateWeights();
        }
    }
    
    private void updateWeights() {
        double[][] weightVariations = new double[ins][outs];
        
        for (int n = 0; n < trainingSamples.size(); ++n) {
            double[][] outputs = calcOutputs(
                    transpose(trainingSamples.get(n).getInput()));
            
            double[][] idealOut = transpose(getRow(idealOuts, n));
            double[][] outDiff = subtract(idealOut, outputs);
            for (int i = 0; i < outDiff.length; ++i) {
                double[][] variation = transpose(trainingSamples.get(n).getInput());
                variation = dot(learnRate, variation);
                variation = dot(outDiff[i][0], variation);
                weightVariations = addToCol(weightVariations, variation, i);
            }
        }
        
        weightVariations = dot(1.0 / outs, weightVariations);
        weights = add(weights, weightVariations);
    }
    
    private double[][] calcOutputs(double[][] inputs) {
        double[][] outputs = dot(transpose(weights),inputs);
        return sigmoid(add(outputs, biases));
    }
    
    public int recognize(double[] input) {
        double[][] output = calcOutputs(
                transpose(matrify(input)));
        double brightestResult = output[0][0];
        int brightestNeuron = 0;
        for (int i = 1; i < outs; ++i) {
            if (output[i][0] > brightestResult) {
                brightestResult = output[i][0];
                brightestNeuron = i;
            }
        }
        return brightestNeuron;
    }
}
