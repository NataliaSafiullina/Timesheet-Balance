import Report.Top5costTasks;
import Report.Top5employees;
import Report.Top5longTasks;

import dao.EmployeeDao;
import dao.PositionDao;
import dao.TaskDao;
import dao.TimesheetDao;
import entity.Employee;
import entity.Position;
import entity.Task;
import entity.Timesheet;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        if (args.length < 2) {
            System.err.println("Provide parameters: [action] [object]");
            System.exit(1);
        }
        switch (args[0]) {
            case "list" -> {
                switch (args[1]) {
                    case "employee" -> {
                        System.out.println("Employees list: ");
                        EmployeeDao employee_dao = new EmployeeDao();
                        List<Employee> employees = employee_dao.getEmployees();
                        employees.forEach(e -> System.out.println(e.getEmployeeID() + "\t" + e.getName() + "\t" + e.getPosition()));
                    }
                    case "position" -> {
                        System.out.println("Positions list: ");
                        PositionDao positions_dao = new PositionDao();
                        List<Position> positions = positions_dao.getPositions();
                        positions.forEach(p -> System.out.println(p.getPosition() + "\t" + p.getPositionRate()));
                    }
                    case "task" -> {
                        System.out.println("Tasks list: ");
                        TaskDao tasks_dao = new TaskDao();
                        List<Task> tasks = tasks_dao.getTasks();
                        tasks.forEach(t -> System.out.println(t.getTaskID() + "\t" + t.getTaskName()));
                    }
                }
            }
            case "import" -> {
                System.out.println("Importing file " + args[1]);
                switch (args[1]) {
                    case "positions.csv" -> ImportData.importPositions(args[1]);
                    case "employees.csv" -> ImportData.importEmployees(args[1]);
                    case "timesheet.csv" -> ImportData.importTimesheet(args[1]);
                }
            }
            case "get" -> {
                System.out.println("Timesheet for employee " + args[1]);
                printTimesheet(args[1]);
            }
            case "remove" -> {
                System.out.println("Removing timesheet with id " + args[1]);
                removeTimesheet(Integer.valueOf(args[1]));
            }
            case "report" -> {
                System.out.println("Report " + args[1]);
                switch (args[1]) {
                    case "top5longTasks" ->
                        // report top5longTasks
                            Top5longTasks.report();
                    case "top5costTasks" -> Top5costTasks.report();
                    case "top5employees" -> Top5employees.report();
                }
            }
        }
    }

    public static void printTimesheet(String employeeName) {
        // TODO if an employee name is not unique we will get an error, should be a cycle
        // get employee-entity from DB by name
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.getEmployeeByName(employeeName);
        // data access object for task
        TaskDao taskDao = new TaskDao();
        // get list of timesheet for employee
        TimesheetDao timesheetDao = new TimesheetDao();
        List<Timesheet> timesheet_list = timesheetDao.getTimesheet(employee);
        // print
        // print
        System.out.printf("%12s | %-20s | %-30s | %-30s \n", "____________", "____________________", "______________________________", "______________________________");
        System.out.printf("%12s | %-20s | %-30s | %-30s \n", "Timesheet ID", "Task", "Start Time","Finish Time");
        System.out.printf("%12s | %-20s | %-30s | %-30s \n", "____________", "____________________", "______________________________", "______________________________");
        timesheet_list.forEach(timesheet -> System.out.printf("%12s | %-20s | %-30s | %-30s \n",timesheet.getTimesheetID(),taskDao.getTaskNameByID(timesheet.getTimesheetTaskID()), timesheet.getStartTime(), timesheet.getFinishTime()));    }

    public static void removeTimesheet(Integer timesheetId) {
        // get timesheet whether it exists
        TimesheetDao timesheetDao = new TimesheetDao();
        Timesheet timesheet = timesheetDao.getTimesheetByID(timesheetId);
        // if timesheet exists than remove it
        if (timesheet != null) {
            String result = timesheetDao.removeTimesheet(timesheet);
            System.out.println("Timesheet ID "+ timesheetId + result);
        }
        else {
            System.out.println("Timesheet ID "+ timesheetId +" does not exist.");
        }
    }
}
