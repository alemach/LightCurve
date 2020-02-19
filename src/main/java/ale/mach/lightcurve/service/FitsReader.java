package ale.mach.lightcurve.service;

import ale.mach.lightcurve.model.TestCurveNegative;
import ale.mach.lightcurve.model.TestCurvePositive;
import ale.mach.lightcurve.repository.TestCurveNegativeRepository;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.TableHDU;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class FitsReader {
    private final TestCurvePositiveRepository positiveRepository;
    private final TestCurveNegativeRepository negativeRepository;

    public FitsReader(TestCurvePositiveRepository positiveRepository, TestCurveNegativeRepository negativeRepository) {
        this.positiveRepository = positiveRepository;
        this.negativeRepository = negativeRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void readDirectoryContents(String fitsDirectory) {
        TestCurvePositive testCurvePositive = new TestCurvePositive();
        TestCurveNegative testCurveNegative = new TestCurveNegative();
        try (Stream<Path> paths = Files.walk(Paths.get(fitsDirectory))) {
            paths.filter(Files::isRegularFile).forEach(
                    path -> {
                        try (Fits fitsFile = new Fits(fitsDirectory + path.getFileName())) {
                            Header header = fitsFile.getHDU(1).getHeader();
                            TableHDU tab = (TableHDU) fitsFile.getHDU(1);
                            double[] time = (double[]) tab.getColumn(0);
                            float[] fluxPDC = (float[]) tab.getColumn(7);

                            float max = 0;
                            float min = 0;
                            for (float fx : fluxPDC) {

                                if (!Float.isNaN(fx)) {
                                    if (fx > max) {
                                        max = fx;
                                    }
                                    if (min == 0) {
                                        min = fx;
                                    } else if (fx < min) {
                                        min = fx;
                                    }
                                }
                            }
                            float[] fluxNormalisedInverted = new float[fluxPDC.length];
                            float[] fluxNormalised = new float[fluxPDC.length];
                            for (int i = 0; i < fluxPDC.length; i++) {
                                fluxNormalised[i] = (fluxPDC[i] - min) / (max - min);
                                fluxNormalisedInverted[i] = fluxNormalised[i] * -1 + 1;
                            }
                            testCurvePositive.setFlux(Arrays.toString(fluxNormalised));
                            testCurvePositive.setTime(Arrays.toString(time));
                            testCurvePositive.setKeplerId(header.getIntValue("KEPLERID"));
                            savePositiveData(testCurvePositive);

                            testCurveNegative.setFlux(Arrays.toString(fluxNormalisedInverted));
                            testCurveNegative.setTime(Arrays.toString(time));
                            testCurveNegative.setKeplerIdInverted(header.getIntValue("KEPLERID") * -1);
                            saveNegativeData(testCurveNegative);
                            Files.delete(path);
                        } catch (FitsException | IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePositiveData(TestCurvePositive curvePositive) {
        positiveRepository.save(curvePositive);
    }

    private void saveNegativeData(TestCurveNegative curveNegative) {
        negativeRepository.save(curveNegative);
    }
}
