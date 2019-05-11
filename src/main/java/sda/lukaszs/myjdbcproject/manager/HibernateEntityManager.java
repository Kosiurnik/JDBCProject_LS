package sda.lukaszs.myjdbcproject.manager;

import sda.lukaszs.myjdbcproject.HibernateInit;

import javax.persistence.EntityManager;

public class HibernateEntityManager implements AutoCloseable{
    private EntityManager entityManager;

    public HibernateEntityManager() {
        entityManager = HibernateInit.getEntityManagerFactory().createEntityManager();
    }
    /*public HibernateEntityManager() {
        entityManager = HibernateInit.getEntityManager();
    }*/

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close(){
        if (!entityManager.isOpen())
            entityManager.close();
        if(entityManager != null)
            entityManager.clear();
    }
}
