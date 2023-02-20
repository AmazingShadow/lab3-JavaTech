package dao;

import database.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Manufacture;
import pojo.Phone;

import java.util.List;

public class PhoneDAO implements Repository<Phone>{

    public static PhoneDAO getInstance() {
        return new PhoneDAO();
    }

    @Override
    public boolean add(Phone item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            // tu dong sinh ra khoa ngoai manufacture_id
            // ---------------------------
            String phone_name = item.getName().split(" ")[0];

            Query query = session.createQuery("From Manufacture where Name = :name");
            query.setParameter("name", phone_name);
            Manufacture result = (Manufacture) query.uniqueResult();

            if (result == null && !phone_name.equalsIgnoreCase("iphone")) {
                System.out.println("This manufacture of this mobilePhone does not exist!");
                return false;
            } else if (phone_name.equalsIgnoreCase("iphone")){
                query = session.createQuery("From Manufacture where Name = :name");
                query.setParameter("name", "apple");
                Manufacture manufacture = (Manufacture) query.uniqueResult();
                item.setManufacture(manufacture);
            } else {
                item.setManufacture(result);
            }

            // ---------------------------
            session.save(item);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Phone get(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone result = null;
        try {
            Query query = session.createQuery("From Phone p join fetch p.manufacture where p.id = :id");
            query.setParameter("id", id);
            result = (Phone) query.uniqueResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Phone> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Phone> phones = null;
        try {
            phones = session.createQuery("Select phone From Phone phone join fetch phone.manufacture", Phone.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return phones;
    }

    @Override
    public boolean remove(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Phone phone = session.find(Phone.class, id);
            if (phone == null) {
                System.out.println("This phone does not exist!");
                return false;
            }
            session.getTransaction().begin();
            session.delete(phone);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean remove(Phone item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("From Phone p join fetch p.manufacture where p.id = :id and p.Name = :name");
            query.setParameter("id", item.getID());
            query.setParameter("name", item.getName());
            Phone phone = (Phone) query.uniqueResult();
            if (phone == null) {
                System.out.println("This phone does not exist!");
                return false;
            }
            session.getTransaction().begin();
            session.delete(phone);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(Phone item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Phone phone = session.find(Phone.class, item.getID());
            if (phone == null) {
                System.out.println("This manufacture does not exist!");
                return false;
            }
            session.getTransaction().begin();
            phone.setName(!item.getName().equals(" ") ? item.getName() : phone.getName());
            phone.setPrice(item.getPrice() != -1 ? item.getPrice() : phone.getPrice());
            phone.setColor(!item.getColor().equals(" ") ? item.getColor() : phone.getColor());
            phone.setCountry(!item.getCountry().equals(" ") ? item.getCountry() : phone.getCountry());
            phone.setQuantity(item.getQuantity() != -1 ? item.getQuantity() : phone.getQuantity());
            session.update(phone);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    public Phone findHighestPrice() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone phone = null;
        try {
            Query query = session.createQuery("SELECT distinct p FROM Phone p join fetch p.manufacture WHERE p.Price = (SELECT MAX(p2.Price) FROM Phone p2) ORDER BY p.Price DESC");
            query.setMaxResults(1);
            phone = (Phone) query.uniqueResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return phone;
    }

    public List<Phone> getPhonesSortByCountry() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Phone> phones = null;
        try {
            String hql = "From Phone p join fetch p.manufacture order by p.Country, p.Price";
            Query query = session.createQuery(hql);
            phones = (List<Phone>) query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return phones;
    }

    public Phone checkPriceOfPhone(String name, String color) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone phone = null;
        boolean check = true;
        try {
            String hql = "From Phone p join fetch p.manufacture where p.Name = :name and p.Color = :color";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("color", color);
            phone = (Phone) query.uniqueResult();
            if (phone == null) {
                System.out.println("Khong tim thay dien thoai nay");
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
        return phone;
    }

    public Phone getFirstPhonePink() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone phone = null;
        try {
            String hql = "From Phone p join fetch p.manufacture where p.Color = :color";
            Query query = session.createQuery(hql);
            query.setParameter("color", "pink");
            query.setMaxResults(1);
            phone = (Phone) query.uniqueResult();
        } catch (Exception ex) {

        } finally {
            session.close();
        }
        return phone;
    }
}
