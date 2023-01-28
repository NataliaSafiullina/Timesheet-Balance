package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="positions")
public class Position implements Serializable {
    @Id
    @Column(name="position")
    private String Position; // name of position

    @Column(name="rate")
    private Integer Rate; // rate of position, aka salary

    public String getPosition () { return Position; }

    public Integer getPositionRate () { return Rate; }

    public void setPosition (String fp_Position) {
        this.Position = fp_Position;
    }

    public void setRate (Integer fp_Rate) {
        this.Rate = fp_Rate;
    }
}