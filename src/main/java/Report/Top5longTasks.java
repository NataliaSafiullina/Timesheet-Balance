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

        List<Object[]> list = timesheetDao.top5taskInTimesheet();
        System.out.printf("%10s | %-20s | %-10s \n", "Task ID", "Task Name", "Time");
        for (Object[] o : list) {
            Integer TaskID = (Integer)o[0];
            Long TaskTime = (Long) o[1];
            //System.out.println(TaskID + "\t" + taskDao.getTaskNameByID(TaskID) + "\t" + TaskTime/3600);
            System.out.printf("%10s | %-20s | %-10s \n", TaskID, taskDao.getTaskNameByID(TaskID), TaskTime/3600);
            /*
            // output item of list
            System.out.println(o);
            // output each field of item of list
            Scanner s = new Scanner(o.toString()).useDelimiter(";");
            String TaskID = s.next();
            String TaskTime = s.next();
            System.out.println(TaskID + "\t" + TaskTime);

             */
        }

    };
}
