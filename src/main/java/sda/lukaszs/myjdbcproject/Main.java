package sda.lukaszs.myjdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Class.forName("com.mysql.cj.log.Slf4JLogger");
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
        scanner.close();
    }
}
