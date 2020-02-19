package ale.mach.lightcurve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestCurveNegative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer keplerIdInverted;

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

    public Integer getKeplerIdInverted() {
        return keplerIdInverted;
    }

    public void setKeplerIdInverted(Integer keplerIdInverted) {
        this.keplerIdInverted = keplerIdInverted;
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
