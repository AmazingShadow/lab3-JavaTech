package dao;

import database.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Manufacture;
import java.util.List;
import exception.InvalidOperationException;

public class ManufactureDAO implements Repository<Manufacture>{
    public static ManufactureDAO getInstance() {
        return new ManufactureDAO();
    }

    @Override
    public boolean add(Manufacture item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            if (ManufactureDAO.getInstance().get(item.getID()) != null){
                System.out.println("This manufacture is exist!");
                return false;
            }
            session.save(item);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Manufacture get(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Manufacture result = null;
        try {
            result = session.find(Manufacture.class, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Manufacture> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Manufacture> manufactures = null;
        try {
            manufactures = session.createQuery("FROM Manufacture", Manufacture.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return manufactures;
    }

    @Override
    public boolean remove(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Manufacture manufacture = session.find(Manufacture.class, id);
            if (manufacture == null) {
                System.out.println("This manufacture does not exist!");
                return false;
            }
            session.getTransaction().begin();
            session.delete(manufacture);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean remove(Manufacture item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("From Manufacture where id = :id and Name= :name");
            query.setParameter("id", item.getID());
            query.setParameter("name", item.getName());
            Manufacture manufacture = (Manufacture) query.uniqueResult();
            System.out.println(manufacture);
            if (manufacture == null) {
                System.out.println("This manufacture does not exist!");
                return false;
            }
            session.getTransaction().begin();
            session.delete(manufacture);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(Manufacture item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Manufacture manufacture = session.find(Manufacture.class, item.getID());
            if (manufacture == null) {
                System.out.println("This manufacture does not exist!");
                return false;
            }
            session.getTransaction().begin();
            manufacture.setName(!item.getName().equals(" ") ? item.getName() : manufacture.getName());
            manufacture.setLocation(!item.getLocation().equals(" ") ? item.getLocation() : manufacture.getLocation());
            manufacture.setEmployee(item.getEmployee() != -1 ? item.getEmployee() : manufacture.getEmployee());
            session.update(manufacture);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    public boolean checkEmployees(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        boolean check = false;
        try {
            Query query = session.createQuery("FROM Manufacture WHERE Name = :name");
            query.setParameter("name", name);
            Manufacture result = (Manufacture) query.uniqueResult();
            if (result == null) {
                System.out.println("This manufacture does not exist!");
            } else {
                if (result.getEmployee() > 100) {
                    check = true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return check;
    }

    public long sumOfEmployees() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        long result = 0;
        try {
            String hql = "Select sum(Employee) From Manufacture";
            Query query = session.createQuery(hql);
            result = (long)query.list().get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    public Manufacture findLastManufactureUS() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Manufacture manufacture = null;
        try {
            Query query = session.createQuery("FROM Manufacture where Location = :location");
            query.setParameter("location", "US");
            List<Manufacture> manufactures = query.getResultList();
            if (manufactures.size() == 0) {
                throw new InvalidOperationException("No US-based manufacturers found.");
            }
            manufacture = manufactures.get(manufactures.size() - 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return manufacture;
    }
}
