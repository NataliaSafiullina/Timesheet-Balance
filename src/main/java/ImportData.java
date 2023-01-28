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
        // DATA_PATH is described at the beginning
        Scanner scanner = new Scanner(new File(DATA_PATH + File.separatorChar + filename));
        // read lines one by one
        while (scanner.hasNextLine()) {
            // take line from file
            String line = scanner.nextLine();
            Scanner s = new Scanner(line).useDelimiter(",");
            // read task name from line
            String TaskNameFile = s.next();
            // create new object as entity Task
            Task task = new Task();
            // data from file save into entity field Name
            task.setTaskName(TaskNameFile);
            // save Task into DB
            taskDao.saveTask(task);

            // get TaskID by name
            task = taskDao.getTaskByName(TaskNameFile);
            System.out.println(task.getTaskID());


/*
            // read employee position from line
            String EmployeePositionFile = s.next();

            // create new object as entity Employee
            Employee employee = new Employee();
            // data from file save into entity fields Name and Position
            employee.setEmployeeName(EmployeeNameFile);
            employee.setEmployeePosition(EmployeePositionFile);
            // save employee into DB
            employeeDao.saveEmployee(employee);

            System.out.println(line);*/
        }
        scanner.close();

    }
}
