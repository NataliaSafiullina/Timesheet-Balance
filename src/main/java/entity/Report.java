package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class Report implements Serializable {
    @Id
    public Integer ObjectID;
    public Integer ObjectValue;

    public Integer getObjectID() {
        return ObjectID;
    }
    public Integer getObjectValue () {
        return ObjectValue;
    }
}
