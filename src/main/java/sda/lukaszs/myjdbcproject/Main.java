package sda.lukaszs.myjdbcproject;

import sda.lukaszs.myjdbcproject.manager.RepositoryManager;
import sda.lukaszs.myjdbcproject.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.log.Slf4JLogger");
        new HibernateInit();
        HibernateExample();
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

    private static void HibernateExample() {
        Scanner scanner = new Scanner(System.in);
        zad_9(scanner);
        scanner.close();
    }

    private static void zad_9(Scanner scanner){
        System.out.print("Wpisz ID pracownika: ");
        long id = scanner.nextLong();
        Employee employee = RepositoryManager.getInstance().getEmployeeRepository().getById(id);
        System.out.println(employee);
        System.out.println();

        System.out.print("Podaj liczbę całkowitą: ");
        int n = scanner.nextInt();
        System.out.println();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            employees.add(new Employee(String.format("Pracownik nr %d", i + 1),3000));
        }
        RepositoryManager.getInstance().getEmployeeRepository().addList(employees);
        for(Employee e : RepositoryManager.getInstance().getEmployeeRepository().getAll()){
            System.out.println(e);
        }
        System.out.println();

        System.out.print("Podaj ID do usunięcia: ");
        id = scanner.nextLong();
        Employee employee1 = RepositoryManager.getInstance().getEmployeeRepository().getById(id);
        RepositoryManager.getInstance().getEmployeeRepository().delete(employee1);
        System.out.printf("%s został usunięty z bazy%n", employee1.getName());
    }
}
