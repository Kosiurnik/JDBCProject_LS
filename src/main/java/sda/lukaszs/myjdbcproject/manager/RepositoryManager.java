package sda.lukaszs.myjdbcproject.manager;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sda.lukaszs.myjdbcproject.repository.EmployeeRepository;

public class RepositoryManager {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myjdbcproject-context.xml");
    private EmployeeRepository employeeRepository = context.getBean("EmployeeRepository", EmployeeRepository.class);
    private static RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private RepositoryManager() {

    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }
}
