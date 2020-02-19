package ale.mach.lightcurve.service;

import ale.mach.lightcurve.model.TestCurveDTO;
import ale.mach.lightcurve.model.TestCurveNegative;
import ale.mach.lightcurve.model.TestCurvePositive;
import ale.mach.lightcurve.repository.TestCurveNegativeRepository;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PrepareTrainingData {

    private final TestCurveNegativeRepository testCurveNegativeRepository;
    private final TestCurvePositiveRepository testCurvePositiveRepository;
    private int testGroupSize = 1000;
    private TestCurveDTO testCurveDTO;
    private TestCurvePositive positiveCurve;
    private TestCurveNegative negativeCurve;

    public PrepareTrainingData(TestCurveNegativeRepository testCurveNegativeRepository, TestCurvePositiveRepository testCurvePositiveRepository) {
        this.testCurveNegativeRepository = testCurveNegativeRepository;
        this.testCurvePositiveRepository = testCurvePositiveRepository;
    }

    public int getTestGroupSize() {
        return testGroupSize;
    }

    public void setTestGroupSize(int testGroupSize) {
        this.testGroupSize = testGroupSize;
    }

    public List<TestCurveDTO> prepareTrainingData() {
        int pool = testCurvePositiveRepository.findRowCount();
        List<TestCurveDTO> testCurves = new ArrayList<>();

        for (int i = 0; i < testGroupSize; i++) {
            testCurveDTO = new TestCurveDTO();

            if (i % 2 == 0) {
                positiveCurve = testCurvePositiveRepository.findById(ThreadLocalRandom.current().nextInt(1, pool)).get();
                testCurveDTO.setId(positiveCurve.getId());
                testCurveDTO.setKeplerId(positiveCurve.getKeplerId());
                testCurveDTO.setFlux(TestCurveDTO.stringToFlux(positiveCurve.getFlux()));
                testCurveDTO.setTime(TestCurveDTO.stringToTime(positiveCurve.getTime()));
                testCurves.add(testCurveDTO);
            }else if (i%2 != 0){
                negativeCurve = testCurveNegativeRepository.findById(ThreadLocalRandom.current().nextInt(1, pool)).get();
                testCurveDTO.setId(negativeCurve.getId());
                testCurveDTO.setKeplerId(negativeCurve.getKeplerIdInverted());
                testCurveDTO.setFlux(TestCurveDTO.stringToFlux(negativeCurve.getFlux()));
                testCurveDTO.setTime(TestCurveDTO.stringToTime(negativeCurve.getTime()));
                testCurves.add(testCurveDTO);
            }
        }

        return testCurves;
    }
}
