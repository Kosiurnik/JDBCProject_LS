package sda.lukaszs.myjdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbcproject?serverTimezone=UTC","jdbcproject","jdbcproject123");
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


        connection.close();
        scanner.close();
    }
}
