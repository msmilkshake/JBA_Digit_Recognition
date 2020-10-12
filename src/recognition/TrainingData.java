package recognition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TrainingData implements Serializable {
    public static List<TrainingSample> getFromFile(String filename) {
        List<TrainingSample> samples = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int expected = Integer.parseInt(data[0]);
                double[] activations = new double[data.length - 1];
                IntStream.range(0, data.length - 1)
                        .forEach(i -> activations[i] = Double.parseDouble(data[i + 1]));
                samples.add(new TrainingSample(expected, activations));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return samples;
    }
}
