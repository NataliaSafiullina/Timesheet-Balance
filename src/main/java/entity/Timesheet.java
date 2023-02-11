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

    public void setTimesheetTaskID(int fp_TaskID){
        this.TimesheetTaskID = fp_TaskID;
    }

    public void setTimesheetEmployeeID (int fp_EmployeeID){
        this.TimesheetEmployeeID = fp_EmployeeID;
    }

    public void setStartTime (Date fp_StartTime) {
        this.StartTime = fp_StartTime;
    }

    public void setFinishTime (Date fp_FinishTime) {
        this.FinishTime = fp_FinishTime;
    }

    public Date getStartTime () { return StartTime; }

    public Date getFinishTime () { return FinishTime; }

    public Integer getTimesheetTaskID () { return TimesheetTaskID; }

    public Integer getTimesheetEmployeeID () { return TimesheetEmployeeID; }

}
