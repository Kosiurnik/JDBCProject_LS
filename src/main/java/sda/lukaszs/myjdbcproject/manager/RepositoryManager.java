package sda.lukaszs.myjdbcproject.manager;

import lombok.Getter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sda.lukaszs.myjdbcproject.repository.EmployeeRepository;
import sda.lukaszs.myjdbcproject.repository.ProductRepository;
import sda.lukaszs.myjdbcproject.repository.UserRepository;

public class RepositoryManager {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myjdbcproject-context.xml");

    private static RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private RepositoryManager() { }

    @Getter
    private EmployeeRepository employeeRepository = context.getBean("EmployeeRepository", EmployeeRepository.class);
    @Getter
    private UserRepository userRepository = context.getBean("UserRepository", UserRepository.class);
    @Getter
    private ProductRepository productRepository = context.getBean("ProductRepository", ProductRepository.class);
}
