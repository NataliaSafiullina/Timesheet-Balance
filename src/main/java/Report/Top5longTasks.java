package Report;

//import dao.TaskDao;
//import entity.Task;
import dao.TaskDao;
import dao.TimesheetDao;

import java.util.List;

public class Top5longTasks {

    public static void report() {
        System.out.println("Top 5 Long Task: ");
        TimesheetDao timesheetDao = new TimesheetDao();
        TaskDao taskDao = new TaskDao();
        // get results of query as list
        List<Object[]> list = timesheetDao.top5taskInTimesheet();
        System.out.printf("%10s | %-20s | %-10s \n", "__________", "____________________", "__________");
        System.out.printf("%10s | %-20s | %-10s \n", "Task ID", "Task Name", "Time");
        System.out.printf("%10s | %-20s | %-10s \n", "__________", "____________________", "__________");
        for (Object[] o : list) {
            Integer TaskID = (Integer)o[0];
            Long TaskTime = (Long) o[1];
            System.out.printf("%10s | %-20s | %-10s \n", TaskID, taskDao.getTaskNameByID(TaskID), TaskTime/3600);
        }
    }
}
