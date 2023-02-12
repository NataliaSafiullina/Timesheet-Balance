package Report;

//import dao.TaskDao;
//import entity.Task;
import dao.TimesheetDao;
import entity.Timesheet;
import entity.Report;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

public class Top5longTasks {

    public static void report() {
        System.out.println("Top 5 Long Task: ");
        TimesheetDao timesheetDao = new TimesheetDao();
/*
        List list = timesheetDao.top5taskInTimesheet();
        for (Object o : list) {
            Report report = (Report) o;
            System.out.println(report.getObjectID() + "\t" + report.getObjectValue());
        }
*/

        List timesheet = timesheetDao.top5taskInTimesheet();
        for (Object o : timesheet) {
            // output item of list
            System.out.println(o);
            // output each field of item of list
            Scanner s = new Scanner(o.toString()).useDelimiter(";");
            String TaskID = s.next();
            String TaskTime = s.next();
            System.out.println(TaskID + "\t" + TaskTime);
        }

    };
}
