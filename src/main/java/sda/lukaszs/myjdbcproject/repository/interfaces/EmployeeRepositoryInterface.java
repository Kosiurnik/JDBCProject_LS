package sda.lukaszs.myjdbcproject.repository.interfaces;

import sda.lukaszs.myjdbcproject.model.Employee;

import java.util.List;

public interface EmployeeRepositoryInterface {
    Employee getById(long id);
    List<Employee> getAll();
    void add(Employee person);
    void addList(List<Employee> people);
    void edit(Employee person);
    void delete(Employee person);
}
