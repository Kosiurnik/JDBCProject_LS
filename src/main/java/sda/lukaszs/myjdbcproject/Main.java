package sda.lukaszs.myjdbcproject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.log.Slf4JLogger");
        HibernatePersistenceExample();
        //JDBCExample();
    }

    private static void JDBCExample() throws SQLException {
        //Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbcproject?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true","jdbcproject","jdbcproject123");
        /*StatementExample.createTableExample(connection);
        StatementExample.insertExample(connection);
        StatementExample.insertWithParametersExample(connection,"Jan Kowalski",3500);*/

        /*System.out.print("Podaj próg zarobku: ");
        int salary = scanner.nextInt();
        StatementExample.deleteExample(connection,salary);
        StatementExample.deleteByIDExample(connection,0);*/

        for(Employee employee : StatementExample.selectAllExample(connection)){
            System.out.println(employee);
        }
        System.out.println();
        for(Employee employee : StatementExample.selectAllByName(connection,"Jan Kowalski")){
            System.out.println(employee);
        }
        System.out.println();
        for(Employee employee : StatementExample.selectAllByName(connection,"1' or '1'='1")){
            System.out.println(employee);
        }

        connection.close();
        //scanner.close();
    }

    private static void HibernatePersistenceExample(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcproject");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //dodawanie
        /*entityManager.getTransaction().begin();
        Employee employee1 = new Employee("Grzegorz Brzęczyszczykiewicz",3500);
        Employee employee2 = new Employee("Jan Kowalski",3100);
        Employee employee3 = new Employee("Stefan Czomber",3800);
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.getTransaction().commit();*/

        //listowanie
        List<Employee> employeeList = entityManager.createQuery("select e from Employee e",Employee.class).getResultList();
        for(Employee employee : employeeList){
            System.out.println(employee);
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
