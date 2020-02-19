package ale.mach.lightcurve.service;

import ale.mach.lightcurve.model.TestCurveDTO;
import ale.mach.lightcurve.model.TestCurveNegative;
import ale.mach.lightcurve.model.TestCurvePositive;
import ale.mach.lightcurve.repository.TestCurveNegativeRepository;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluateData {

    private final TestCurvePositiveRepository testCurvePositiveRepository;
    private final TestCurveNegativeRepository testCurveNegativeRepository;
    private final NeuralNetwork neuralNetwork;

    public EvaluateData(TestCurvePositiveRepository testCurvePositiveRepository, TestCurveNegativeRepository testCurveNegativeRepository, NeuralNetwork neuralNetwork) {
        this.testCurvePositiveRepository = testCurvePositiveRepository;
        this.testCurveNegativeRepository = testCurveNegativeRepository;
        this.neuralNetwork = neuralNetwork;
    }

    public double[] evaluate(TestCurveDTO testCurveDTO) {
        double[] result = new double[2];
        if (testCurveDTO.getKeplerId() == 1) {
            TestCurvePositive testCurvePositive = testCurvePositiveRepository.findById(testCurveDTO.getId()).get();
            testCurveDTO.setKeplerId(testCurvePositive.getKeplerId());
            testCurveDTO.setTime(TestCurveDTO.stringToTime(testCurvePositive.getTime()));
            testCurveDTO.setFlux(TestCurveDTO.stringToFlux(testCurvePositive.getFlux()));
            result = neuralNetwork.compute(testCurveDTO);
        } else if (testCurveDTO.getKeplerId() == 2) {
            TestCurveNegative testCurveNegative = testCurveNegativeRepository.findById(testCurveDTO.getId()).get();
            testCurveDTO.setKeplerId(testCurveNegative.getKeplerIdInverted());
            testCurveDTO.setTime(TestCurveDTO.stringToTime(testCurveNegative.getTime()));
            testCurveDTO.setFlux(TestCurveDTO.stringToFlux(testCurveNegative.getFlux()));
            result = neuralNetwork.compute(testCurveDTO);
        }
        PlotSeriesToFile.plotToFile(testCurveDTO);

        return result;
    }
}
