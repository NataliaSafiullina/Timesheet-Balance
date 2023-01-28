package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="employees")
public class Employee implements Serializable {
    @Id
    @Column(name="employee_id")
    private int EmployeeID;

    @Column(name="employee_name")
    private String EmployeeName;

    @Column(name="employee_position")
    private String EmployeePosition;

    public Integer getEmployeeID () { return EmployeeID; }

    public String getName () {
        return EmployeeName;
    }

    public String getPosition () { return EmployeePosition; }

    public void setEmployeeName (String fp_EmployeeName) {
        this.EmployeeName =  fp_EmployeeName;
    }

    public void setEmployeePosition (String fp_EmployeePosition) {
        this.EmployeePosition = fp_EmployeePosition;
    }

}