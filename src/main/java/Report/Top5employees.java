package Report;

import dao.TaskDao;
import dao.TimesheetDao;

import java.util.List;

public class Top5employees {
    public static void report() {
        System.out.println("Top 5 Employees: ");
        TimesheetDao timesheetDao = new TimesheetDao();
        // get results of query as list
        List<Object[]> list = timesheetDao.top5employeeInTime();
        System.out.printf("%11s | %-20s | %-11s \n", "___________", "____________________", "___________");
        System.out.printf("%11s | %-20s | %-11s \n", "Employee ID", "Employee Name", "Total Hours");
        System.out.printf("%11s | %-20s | %-11s \n", "___________", "____________________", "___________");
        for (Object[] o : list) {
            Integer EmplID = (Integer)o[0];
            String EmplName = (String)o[1];
            Long EmplTime = (Long) o[2];
            System.out.printf("%11s | %-20s | %-11s \n", EmplID, EmplName, EmplTime);
        }
    };
}
