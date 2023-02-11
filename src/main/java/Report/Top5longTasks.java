package Report;

//import dao.TaskDao;
//import entity.Task;
import dao.TimesheetDao;
import entity.Timesheet;

import java.util.List;

public class Top5longTasks {
    public static void report() {
        System.out.println("Top 5 Long Task: ");
        TimesheetDao timesheetDao = new TimesheetDao();
        List timesheet = timesheetDao.top5taskInTimesheet();
        for (timesheet) {
            System.out.println("element of list: " + );
        }
    };
}
