package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="timesheet")
public class Timesheet implements Serializable {
    @Id
    @Column(name="timesheet_id")
    public int TimesheetID;

    @Column(name="employee_id")
    public int TimesheetEmployeeID;

    @Column(name="task_id")
    public int TimesheetTaskID;

    @Column(name="start_time")
    public Date StartTime;

    @Column(name="finish_time")
    public Date FinishTime;

}
