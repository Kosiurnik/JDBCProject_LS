package sda.lukaszs.myjdbcproject.manager;

import lombok.Getter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sda.lukaszs.myjdbcproject.repository.*;

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
    @Getter
    private ProductCategoryRepository productCategoryRepository = context.getBean("ProductCategoryRepository", ProductCategoryRepository.class);
    @Getter
    private CustomerRepository customerRepository = context.getBean("CustomerRepository", CustomerRepository.class);
    @Getter
    private ShoppingOrderRepository shoppingOrderRepository = context.getBean("ShoppingOrderRepository", ShoppingOrderRepository.class);
}
