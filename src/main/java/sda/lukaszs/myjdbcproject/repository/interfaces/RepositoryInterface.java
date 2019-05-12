package sda.lukaszs.myjdbcproject.repository.interfaces;

import java.util.List;

public interface RepositoryInterface<T> {
    T getById(long id);
    List<T> getAll();
    void add(T entity);
    void addList(List<T> entities);
    void edit(T entity);
    void delete(T entity);
}
