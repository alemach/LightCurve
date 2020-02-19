package ale.mach.lightcurve.service;

import ale.mach.lightcurve.model.Epoch;
import ale.mach.lightcurve.model.NNWeights;
import ale.mach.lightcurve.model.TestCurveDTO;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeuralNetwork {
    private final PrepareTrainingData trainingData;
    private final NNWeights nnWeights;
    private RealVector x;
    private double[] result = new double[2];
    private static double cost;
    private final TestCurvePositiveRepository testCurvePositiveRepository;
    private RealVector x1;
    private RealVector x2;
    private RealVector x3;
    private RealVector x4;
    private RealVector x5;
    private int counter;
    private int maxSamples = 1;

    public NeuralNetwork(PrepareTrainingData trainingData, NNWeights nnWeights, TestCurvePositiveRepository testCurvePositiveRepository) {
        this.trainingData = trainingData;
        this.nnWeights = nnWeights;
        this.testCurvePositiveRepository = testCurvePositiveRepository;
    }

    public int getCounter() {
        return counter;
    }

    public int getMaxSamples() {
        return maxSamples;
    }

    
    @Async
    public int[] train(Epoch epoch) {
        maxSamples = epoch.getEpochAmount()*epoch.getEpochSize();
        counter = 0;
        int[] successRate = new int[]{0, 0};
        double[] partialResult = new double[2];
        for (int i = 0; i < epoch.getEpochAmount(); i++) {
            trainingData.setTestGroupSize(epoch.getEpochSize());
            List<TestCurveDTO> testCurves = trainingData.prepareTrainingData();
            for (int j = 0; j < testCurves.size(); j++) {
                partialResult = compute(testCurves.get(j));
                if ((partialResult[1] == 1 && partialResult[0] > 0.5) || (partialResult[1] == 0 && partialResult[0] < 0.5)) {
                    successRate[0] += 1;
                }
                partialResult[1] += 1;
            }
        }
        return successRate;
    }

    /**
     * feeding input vector through NN
     * @param testCurveDTO
     * @return
     */
    public double[] compute(TestCurveDTO testCurveDTO) {
        if (nnWeights.getFirstLayer() == null) {
            nnWeights.initializeWeights();
        }
        System.out.println(++counter);
        x = (new ArrayRealVector(testCurveDTO.getFlux()));
        x1 = nnWeights.getFirstLayer().operate(x);
        x1.mapToSelf(v -> sigmoid(v));
        x2 = nnWeights.getSecondLayer().operate(x1);
        x2.mapToSelf(v -> sigmoid(v));
        x3 = nnWeights.getThirdLayer().operate(x2);
        x3.mapToSelf(v -> sigmoid(v));
        x4 = nnWeights.getFourthLayer().operate(x3);
        x4.mapToSelf(v -> sigmoid(v));
        x5 = nnWeights.getFifthLayer().operate(x4);
        x5.mapToSelf(v -> sigmoid(v));


        result[0] = x5.toArray()[0];
        result[1] = testCurveDTO.getExpected();
        costFunction(testCurveDTO.getExpected(), result[0]);
        backProp();
        return result;
    }

    /**
     * normalized relU activation function
     *
     * @param d
     * @param max
     * @return
     */
    private double relU(double d, double max) {
        if (d > 0) {
            return d / max;
        } else {
            return 0;
        }
    }

    /**
     * sigmoid activation function
     *
     * @param d
     * @return
     */
    private double sigmoid(double d) {
        return 1 / (1 + Math.pow(Math.E, -d));
    }


    /**
     * cost function
     *
     * @param expected
     * @param calculated
     */
    private void costFunction(double expected, double calculated) {
//        cost = Math.pow((expected - calculated), 2) / 2;
        cost = expected * Math.log10(calculated) + (1 - expected) * Math.log10(1 - calculated);
    }

    /**
     * backpropagation algorithm
     */

    private void backProp() {

        RealMatrix d5 = nnWeights.getFifthLayer().transpose().scalarMultiply((1 - result[0]) * sigmoid(result[0]) * (1 - sigmoid(result[0])));
        RealMatrix d4 = nnWeights.getFourthLayer().transpose().multiply(d5);

        RealMatrix d3 = nnWeights.getThirdLayer().transpose().multiply(d4);
        RealMatrix d2 = nnWeights.getSecondLayer().transpose().multiply(d3);
        RealMatrix d1 = nnWeights.getFirstLayer().transpose().multiply(d2);
        nnWeights.setFifthLayer(nnWeights.getFifthLayer().subtract(d5.multiply(MatrixUtils.createRowRealMatrix(x5.toArray())).transpose()));
        nnWeights.setFourthLayer(nnWeights.getFourthLayer().subtract(d4.multiply(MatrixUtils.createRowRealMatrix(x4.toArray())).transpose()));
        nnWeights.setThirdLayer(nnWeights.getThirdLayer().subtract(d3.multiply(MatrixUtils.createRowRealMatrix(x3.toArray())).transpose()));
        nnWeights.setSecondLayer(nnWeights.getSecondLayer().subtract(d2.multiply(MatrixUtils.createRowRealMatrix(x2.toArray())).transpose()));
        nnWeights.setFirstLayer(nnWeights.getFirstLayer().subtract(d1.multiply(MatrixUtils.createRowRealMatrix(x1.toArray())).transpose()));
    }

}
