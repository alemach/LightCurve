package ale.mach.lightcurve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestCurvePositive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer keplerId;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String time;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String flux;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getKeplerId() {
        return keplerId;
    }

    public void setKeplerId(Integer keplerId) {
        this.keplerId = keplerId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFlux() {
        return flux;
    }

    public void setFlux(String flux) {
        this.flux = flux;
    }

}
