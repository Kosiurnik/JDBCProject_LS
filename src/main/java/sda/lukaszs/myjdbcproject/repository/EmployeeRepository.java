package sda.lukaszs.myjdbcproject.repository;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.Employee;
import sda.lukaszs.myjdbcproject.repository.interfaces.EmployeeRepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface {
    @Override
    public Employee getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<Employee> query = em.getEntityManager().createQuery("select e from Employee e where e.id = :eID",Employee.class).setParameter("eID", id);
            Employee employee = query.getSingleResult();
            if(employee != null){
                return employee;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<Employee> people = em.getEntityManager().createQuery("select e from Employee e order by e.id",Employee.class).getResultList();
            if(people.size()!=0){
                return people;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(Employee employee) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(employee);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<Employee> employees) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(Employee employee : employees){
                em.getEntityManager().persist(employee);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Employee employee) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().merge(employee);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(Employee.class, employee.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
