package dao;

import entity.Employee;
import entity.Position;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class PositionDao {
    public void savePosition(Position position) {
        // try to save data into DB
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(position);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public List<Position> getPositions() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Position", Position.class).list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}