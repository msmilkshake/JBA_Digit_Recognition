package recognition;

import java.io.Serializable;

public class TrainingSample implements Serializable {
    private int expectedResult;
    private double[][] input;
    
    public TrainingSample(int expected, double[] vals) {
        expectedResult = expected;
        input = new double[1][vals.length];
        input[0] = vals;
    }
    
    public double[][] getInput() {
        return input;
    }
    
    public int getExpectedResult() {
        return expectedResult;
    }
}
