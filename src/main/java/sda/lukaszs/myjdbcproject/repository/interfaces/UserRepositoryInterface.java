package sda.lukaszs.myjdbcproject.repository.interfaces;

import sda.lukaszs.myjdbcproject.model.Employee;
import sda.lukaszs.myjdbcproject.model.User;

import java.util.List;

public interface UserRepositoryInterface {
    User getById(long id);
    List<User> getAll();
    void add(User user);
    void addList(List<User> user);
    void edit(User user);
    void delete(User user);
}
