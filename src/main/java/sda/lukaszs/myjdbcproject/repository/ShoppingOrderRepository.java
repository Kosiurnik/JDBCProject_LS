package sda.lukaszs.myjdbcproject.repository;

import sda.lukaszs.myjdbcproject.manager.HibernateEntityManager;
import sda.lukaszs.myjdbcproject.model.ShoppingOrder;
import sda.lukaszs.myjdbcproject.model.ShoppingOrderProductAlloc;
import sda.lukaszs.myjdbcproject.repository.interfaces.RepositoryInterface;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ShoppingOrderRepository implements RepositoryInterface<ShoppingOrder> {
    @Override
    public ShoppingOrder getById(long id) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            TypedQuery<ShoppingOrder> query = em.getEntityManager()
                    .createQuery("select so from ShoppingOrder so where so.id = :soID", ShoppingOrder.class)
                    .setParameter("soID", id);
            ShoppingOrder shoppingOrder = query.getSingleResult();
            if (shoppingOrder.getShoppingOrderProducts().size() != 0) {
                for (ShoppingOrderProductAlloc shoppingOrderProductAlloc : shoppingOrder.getShoppingOrderProducts()) {
                    shoppingOrderProductAlloc.getProduct().setShoppingOrderProducts(new ArrayList<>());
                }
                shoppingOrder.setShoppingOrderProducts(shoppingOrder.getShoppingOrderProducts());
            } else
                shoppingOrder.setShoppingOrderProducts(null);
            return shoppingOrder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShoppingOrder> getAll() {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            List<ShoppingOrder> shoppingOrders = em.getEntityManager()
                    .createQuery("select so from ShoppingOrder so order by so.id desc",
                            ShoppingOrder.class)
                    .getResultList();
            if (shoppingOrders.size() != 0) {
                for (ShoppingOrder shoppingOrder : shoppingOrders) {
                    if (shoppingOrder.getShoppingOrderProducts().size() != 0) {
                        for (ShoppingOrderProductAlloc shoppingOrderProductAlloc : shoppingOrder.getShoppingOrderProducts()) {
                            shoppingOrderProductAlloc.getProduct()
                                    .setShoppingOrderProducts(new ArrayList<>());
                        }
                        shoppingOrder.setShoppingOrderProducts(shoppingOrder.getShoppingOrderProducts());
                    } else
                        shoppingOrder.setShoppingOrderProducts(null);
                }
                return shoppingOrders;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void add(ShoppingOrder shoppingOrder) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().persist(shoppingOrder);
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<ShoppingOrder> shoppingOrders) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            for(ShoppingOrder shoppingOrder : shoppingOrders){
                em.getEntityManager().persist(shoppingOrder);
            }
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(ShoppingOrder shoppingOrder) {
        try(HibernateEntityManager em = new HibernateEntityManager()){

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ShoppingOrder shoppingOrder) {
        try(HibernateEntityManager em = new HibernateEntityManager()){
            em.getEntityManager().getTransaction().begin();
            em.getEntityManager().remove(em.getEntityManager().find(ShoppingOrder.class, shoppingOrder.getId()));
            em.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
