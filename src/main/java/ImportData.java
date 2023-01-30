import dao.EmployeeDao;
import dao.PositionDao;
import dao.TaskDao;
import dao.TimesheetDao;
import entity.Employee;
import entity.Position;
import entity.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImportData {
    private static final String DATA_PATH = "data";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public static void importPositions(String filename) throws FileNotFoundException {
        PositionDao positionDao = new PositionDao();
        // DATA_PATH is described at the beginning
        Scanner scanner = new Scanner(new File(DATA_PATH + File.separatorChar + filename));
        // read lines one by one
        while (scanner.hasNextLine()) {
            // take line from file
            String line = scanner.nextLine();
            Scanner s = new Scanner(line).useDelimiter(",");
            // read name of position from line
            String PositionFile = s.next();
            // read rate of positions from line
            Integer RateFile = s.nextInt();

            // create new object as entity Position
            // TODO Learn why creating a new entity is in while. Is it possible to use the same entity a few times.
            Position position = new Position();
            // data from file save into entity fields
            position.setPosition(PositionFile);
            position.setRate(RateFile);
            // save position into DB
            positionDao.savePosition(position);

            System.out.println(line);
        }
        scanner.close();
    }

    public static void importEmployees(String filename) throws FileNotFoundException {
        EmployeeDao employeeDao = new EmployeeDao();
        // DATA_PATH is described at the beginning
        Scanner scanner = new Scanner(new File(DATA_PATH + File.separatorChar + filename));
        // read lines one by one
        while (scanner.hasNextLine()) {
            // take line from file
            String line = scanner.nextLine();
            Scanner s = new Scanner(line).useDelimiter(",");
            // read name from line
            String EmployeeNameFile = s.next();
            // read employee position from line
            String EmployeePositionFile = s.next();

            // create new object as entity Employee
            Employee employee = new Employee();
            // data from file save into entity fields Name and Position
            employee.setEmployeeName(EmployeeNameFile);
            employee.setEmployeePosition(EmployeePositionFile);
            // save employee into DB
            employeeDao.saveEmployee(employee);

            System.out.println(line);
        }
        scanner.close();
    }

    public static void importTimesheet(String filename) throws FileNotFoundException, ParseException {
        TaskDao taskDao = new TaskDao();
        TimesheetDao timesheetDao = new TimesheetDao();
        int ID;
        // DATA_PATH is described at the beginning
        Scanner scanner = new Scanner(new File(DATA_PATH + File.separatorChar + filename));
        // formatter for dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // read lines one by one
        while (scanner.hasNextLine()) {
            // take line from file
            String line = scanner.nextLine();
            Scanner s = new Scanner(line).useDelimiter(",");

            // read Task name from the line
            String TaskNameFile = s.next();
            // read Employee name from the line
            String EmployeeNameFile = s.next();
            // read start time from the line
            Date StartTimeFile = dateFormat.parse(s.next());
            // read finish time from the line
            Date FinishTimeFile = dateFormat.parse(s.next());

            // get Task by name from file
            Task task_obj = taskDao.getTaskByName(TaskNameFile);
            System.out.print("Task name = ");
            System.out.println(TaskNameFile);

            if(task_obj != null) {
                // get TaskID
                ID = task_obj.getTaskID();
                // task exists in DB;
                System.out.print("Task exists, ID = ");
                System.out.println(ID);
            }
            else {
                // task does not exist, we need to create it
                System.out.println("Task does not exist, ID = null");
                // create new object as entity Task
                Task task_new = new Task();
                // data from file save into entity field Name
                task_new.setTaskName(TaskNameFile);
                // save Task into DB
                taskDao.saveTask(task_new);
                // get Task by name from file
                task_new = taskDao.getTaskByName(TaskNameFile);
                // get TaskID of the task
                ID = task_new.getTaskID();
                System.out.print("New Task ID = ");
                System.out.println(ID);
            }




        }
        scanner.close();

    }
}
