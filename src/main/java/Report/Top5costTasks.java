package Report;

import dao.TaskDao;
import dao.TimesheetDao;

import java.util.List;

public class Top5costTasks {
    public static void report() {
        System.out.println("Top 5 Cost Task: ");
        TimesheetDao timesheetDao = new TimesheetDao();
        TaskDao taskDao = new TaskDao();
        // get results of query as list
        List<Object[]> list = timesheetDao.top5taskInCost();
        System.out.printf("%10s | %-20s | %-10s \n", "__________", "____________________", "__________");
        System.out.printf("%10s | %-20s | %-10s \n", "Task ID", "Task Name", "Total Cost");
        System.out.printf("%10s | %-20s | %-10s \n", "__________", "____________________", "__________");
        for (Object[] o : list) {
            Integer TaskID = (Integer)o[0];
            Long TaskTime = (Long) o[1];
            System.out.printf("%10s | %-20s | %-10s \n", TaskID, taskDao.getTaskNameByID(TaskID), TaskTime);
        }
    }
}
