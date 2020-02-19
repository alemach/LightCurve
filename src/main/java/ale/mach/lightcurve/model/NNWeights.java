package ale.mach.lightcurve.model;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class NNWeights {
    private RealMatrix firstLayer;

    public RealMatrix getFirstLayer() {
        return firstLayer;
    }

    public void setFirstLayer(RealMatrix firstLayer) {
        this.firstLayer = firstLayer;
    }

    private RealMatrix secondLayer;

    public RealMatrix getSecondLayer() {
        return secondLayer;
    }

    public void setSecondLayer(RealMatrix secondLayer) {
        this.secondLayer = secondLayer;
    }

    private RealMatrix thirdLayer;

    public RealMatrix getThirdLayer() {
        return thirdLayer;
    }

    public void setThirdLayer(RealMatrix thirdLayer) {
        this.thirdLayer = thirdLayer;
    }

    private RealMatrix fourthLayer;

    public RealMatrix getFourthLayer() {
        return fourthLayer;
    }

    public void setFourthLayer(RealMatrix fourthLayer) {
        this.fourthLayer = fourthLayer;
    }

    private RealMatrix fifthLayer;

    public RealMatrix getFifthLayer() {
        return fifthLayer;
    }

    public void setFifthLayer(RealMatrix fifthLayer) {
        this.fifthLayer = fifthLayer;
    }

    public void initializeWeights() {
        double[][] doubles = new double[4572][4572];
        for (int i = 0; i < 4572; i++) {
            for (int j = 0; j < 4572; j++) {
                doubles[i][j] = ThreadLocalRandom.current().nextDouble(-1, 1);
            }
        }
        firstLayer = new BlockRealMatrix(doubles);

        doubles = new double[457][4572];

        for (int i = 0; i < 457; i++) {
            for (int j = 0; j < 4572; j++) {
                doubles[i][j] = ThreadLocalRandom.current().nextDouble(-1, 1);
            }
        }
        secondLayer = new BlockRealMatrix(doubles);

        doubles = new double[45][457];

        for (int i = 0; i < 45; i++) {
            for (int j = 0; j < 457; j++) {
                doubles[i][j] = ThreadLocalRandom.current().nextDouble(-1, 1);
            }
        }
        thirdLayer = new BlockRealMatrix(doubles);

        doubles = new double[4][45];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 45; j++) {
                doubles[i][j] = ThreadLocalRandom.current().nextDouble(-1, 1);
            }
        }
        fourthLayer = new BlockRealMatrix(doubles);

        doubles = new double[1][4];

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                doubles[i][j] = ThreadLocalRandom.current().nextDouble(-1, 1);
            }
        }
        fifthLayer = new BlockRealMatrix(doubles);

    }
}