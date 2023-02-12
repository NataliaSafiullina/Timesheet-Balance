package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tasks")
public class Task implements Serializable {
    @Id
    @Column(name="task_id")
    public int TaskID;

    @Column(name="task_name")
    public String TaskName;

    @Column(name="task_text")
    public String TaskText;

    public void setTaskName (String fp_TaskName){
        this.TaskName = fp_TaskName;
    }

    public int getTaskID () { return TaskID; }

    public String getTaskName () { return TaskName; }
}