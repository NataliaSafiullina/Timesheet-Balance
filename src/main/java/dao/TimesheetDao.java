package dao;

import entity.Employee;
import entity.Task;
import entity.Timesheet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class TimesheetDao {
    public void saveTimesheet(Timesheet timesheet) {
        // try to save data into DB
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(timesheet);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public String removeTimesheet(Timesheet timesheet) {
        // try to remove row in transaction
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.remove(timesheet);

            transaction.commit();

            return " is removed";

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return " is not removed";
        }
    }

    public List<Timesheet> getTimesheet(Employee employee) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Timesheet> query = session.createQuery("from Timesheet where TimesheetEmployeeID = :P1 ", Timesheet.class);
            query.setParameter("P1", employee.getEmployeeID());
            return query.list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Timesheet getTimesheetByID(Integer fp_ID) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Timesheet> query = session.createQuery("from Timesheet where TimesheetID = :P1 ", Timesheet.class);
            query.setParameter("P1", fp_ID);
            return query.list().get(0);
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List top5taskInTimesheet () {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // form query string
            String hql = "select TS.TimesheetTaskID," +
                    " (sum(time_to_sec(timediff(TS.FinishTime,TS.StartTime)))) as Time" +
                    " from Timesheet TS group by TS.TimesheetTaskID order by Time desc";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            // this instead of sql: limit 5
            query.setMaxResults(5);
            // return results in list
            return query.list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
