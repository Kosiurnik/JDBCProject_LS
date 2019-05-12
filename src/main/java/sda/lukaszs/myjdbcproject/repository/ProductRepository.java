package sda.lukaszs.myjdbcproject.repository;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.Product;
import sda.lukaszs.myjdbcproject.repository.interfaces.RepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements RepositoryInterface<Product> {
    @Override
    public Product getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<Product> query = em.getEntityManager().createQuery("select p from Product p where p.id = :pID",Product.class).setParameter("pID", id);
            Product product = query.getSingleResult();
            if(product != null){
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<Product> products = em.getEntityManager().createQuery("select p from Product p order by p.id",Product.class).getResultList();
            if(products.size()!=0){
                return products;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(product);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<Product> products) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(Product product : products){
                em.getEntityManager().persist(product);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Product product) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().merge(product);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(Product.class, product.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
