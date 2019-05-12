package sda.lukaszs.myjdbcproject.repository;

import org.springframework.stereotype.Repository;
import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.Product;
import sda.lukaszs.myjdbcproject.model.ProductCategory;
import sda.lukaszs.myjdbcproject.repository.interfaces.RepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCategoryRepository implements RepositoryInterface<ProductCategory> {
    @Override
    public ProductCategory getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<ProductCategory> query = em.getEntityManager().createQuery("select pc from ProductCategory pc where pc.id = :pcID",ProductCategory.class).setParameter("pcID", id);
            ProductCategory category = query.getSingleResult();
            if(category != null){
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<ProductCategory> categories = em.getEntityManager().createQuery("select pc from ProductCategory pc order by pc.id",ProductCategory.class).getResultList();
            if(categories.size()!=0){
                return categories;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(ProductCategory category) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(category);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<ProductCategory> categories) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(ProductCategory category : categories){
                em.getEntityManager().persist(category);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(ProductCategory category) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().merge(category);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ProductCategory category) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(ProductCategory.class, category.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
