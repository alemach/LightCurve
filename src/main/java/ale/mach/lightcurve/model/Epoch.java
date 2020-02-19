package ale.mach.lightcurve.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Component
public class Epoch {

    @Min(10)
    private int epochSize;
    @Min(1)
    private int epochAmount;

    public int getEpochSize() {
        return epochSize;
    }

    public void setEpochSize(int epochSize) {
        this.epochSize = epochSize;
    }

    public int getEpochAmount() {
        return epochAmount;
    }

    public void setEpochAmount(int epochAmount) {
        this.epochAmount = epochAmount;
    }
}
