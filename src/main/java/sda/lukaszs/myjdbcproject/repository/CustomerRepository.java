package sda.lukaszs.myjdbcproject.repository;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.Customer;
import sda.lukaszs.myjdbcproject.model.Employee;
import sda.lukaszs.myjdbcproject.repository.interfaces.RepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements RepositoryInterface<Customer> {
    @Override
    public Customer getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<Customer> query = em.getEntityManager().createQuery("select c from Customer c where c.id = :cID",Customer.class).setParameter("cID", id);
            Customer customer = query.getSingleResult();
            if(customer != null){
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<Customer> customers = em.getEntityManager().createQuery("select c from Customer c order by c.id",Customer.class).getResultList();
            if(customers.size()!=0){
                return customers;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(Customer customer) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(customer);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<Customer> customers) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(Customer customer : customers){
                em.getEntityManager().persist(customer);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Customer customer) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().merge(customer);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(Customer.class, customer.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
