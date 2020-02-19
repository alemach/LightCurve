package ale.mach.lightcurve.service;

import ale.mach.lightcurve.repository.TestCurveNegativeRepository;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InsertTestData {

    private final String urlRepoPath = "src/main/resources/downloadScript/injected-light-curves-dr25-inj1.txt"; //TODO create download method for download script
    private final String fitsDirectory = "src/main/resources/FitsFiles/";
    private final String urlRegex = "https://exoplanetarchive.ipac.caltech.edu/data/KeplerData/Simulated/.+\\.gz";
    private final String fileName = "kplr\\d+-\\d+\\w+-\\w+\\.fits\\.gz";
    private final TestCurvePositiveRepository positiveRepository;
    private final TestCurveNegativeRepository negativeRepository;

    private int i = 1;

    public InsertTestData(TestCurvePositiveRepository positiveRepository, TestCurveNegativeRepository negativeRepository) {
        this.positiveRepository = positiveRepository;
        this.negativeRepository = negativeRepository;
    }

    public void insertData() {
        Pattern urlPattern = Pattern.compile(urlRegex);
        Pattern filePattern = Pattern.compile(fileName);
        File urlRepoFile = new File(urlRepoPath);
        try (LineIterator it = FileUtils.lineIterator(urlRepoFile, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                String url = "";
                String fileName = "";
                Matcher matcher = urlPattern.matcher(line);
                if (matcher.find()) {
                    url = matcher.group();
                }
                matcher = filePattern.matcher(line);
                if (matcher.find()) {
                    fileName = matcher.group();
                }
                DataCourier.fetchFile(url, fileName);
                DataCourier.unzip(fileName);
                FitsReader fitsReader = new FitsReader(positiveRepository, negativeRepository);
                fitsReader.readDirectoryContents(fitsDirectory);
                System.out.println(i++ + " " + LocalTime.now());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            Files.lines(urlRepoPath).forEach(lines -> {
//                String url = "";
//                String fileName = "";
//                Matcher matcher = urlPattern.matcher(lines);
//                if (matcher.find()) {
//                    url = matcher.group();
//                }
//                matcher = filePattern.matcher(lines);
//                if (matcher.find()) {
//                    fileName = matcher.group();
//                }
//                DataCourier.fetchFile(url,fileName);
//                DataCourier.unzip(fileName);
//                FitsReader fitsReader = new FitsReader(positiveRepository, negativeRepository);
//                fitsReader.readDirectoryContents(fitsDirectory);
//                System.out.println(i++);
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
