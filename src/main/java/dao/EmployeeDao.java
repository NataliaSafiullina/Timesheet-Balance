package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class EmployeeDao {
    public void saveEmployee(Employee employee) {
        // try to save data into DB
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Employee", Employee.class).list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getEmployee(String employeeName) {
        return null;
    }
}