package sda.lukaszs.myjdbcproject.repository;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.Employee;
import sda.lukaszs.myjdbcproject.model.User;
import sda.lukaszs.myjdbcproject.repository.interfaces.EmployeeRepositoryInterface;
import sda.lukaszs.myjdbcproject.repository.interfaces.UserRepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {
    @Override
    public User getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<User> query = em.getEntityManager().createQuery("select u from User u where u.id = :uID",User.class).setParameter("uID", id);
            User user = query.getSingleResult();
            if(user != null){
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<User> users = em.getEntityManager().createQuery("select u from User u order by u.id",User.class).getResultList();
            if(users.size()!=0){
                return users;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(User user) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(user);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<User> users) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(User user : users){
                em.getEntityManager().persist(user);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().merge(user);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(Employee.class, user.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
