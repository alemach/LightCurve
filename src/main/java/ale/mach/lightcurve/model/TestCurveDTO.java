package ale.mach.lightcurve.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Component
public class TestCurveDTO {

    @Min(1)
    private int id;
    @Min(value = 1, message = "choose one")
    private int keplerId;
    private int expected;
    private double[] time;
    private double[] flux;

    public int getExpected() {
        return expected;
    }

    public void setExpected() {
        if (this.keplerId > 0) {
            this.expected = 1;
        } else if (this.keplerId < 0) {
            this.expected = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeplerId() {
        return keplerId;
    }

    public void setKeplerId(int keplerId) {
        this.keplerId = keplerId;
        setExpected();
    }

    public double[] getTime() {
        return time;
    }

    public void setTime(double[] time) {
        this.time = time;
    }

    public double[] getFlux() {
        return flux;
    }

    public void setFlux(double[] flux) {
        this.flux = flux;
    }

    public static double[] stringToTime(String sTime) {
        String temp = sTime.substring(1, sTime.length() - 2);
        String[] stringTimeTable = temp.split(", ");
        double[] time = new double[stringTimeTable.length];
        for (int i = 0; i < stringTimeTable.length; i++) {
            time[i] = Double.valueOf(stringTimeTable[i]);
        }
        return time;
    }

    public static double[] stringToFlux(String sFlux) {
        String temp = sFlux.substring(6, sFlux.length() - 1);
        String[] stringFluxTable = temp.split(", ");
        double[] flux = new double[stringFluxTable.length];
        double mAv = 0;
        for (int i = 0; i < stringFluxTable.length; i++) {

            if (!stringFluxTable[i].equals("NaN")) {
                flux[i] = Double.valueOf(stringFluxTable[i]);
                if (i > 1) {
                    mAv = (flux[i - 1] + flux[i - 2]) / 2;
                } else {
                    mAv = flux[i];
                }
            } else {
                flux[i] = mAv;
            }
        }
        return flux;
    }
}
