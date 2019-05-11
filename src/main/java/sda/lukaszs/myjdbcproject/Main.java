package sda.lukaszs.myjdbcproject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbcproject?serverTimezone=UTC","jdbcproject","jdbcproject123");
        StatementExample.createTableExample(connection);
        StatementExample.insertExample(connection);

        System.out.print("Podaj próg zarobku: ");
        int salary = scanner.nextInt();
        StatementExample.deleteExample(connection,salary);

        StatementExample.insertWithParametersExample(connection,"Jan Kowalski",3500);
        StatementExample.deleteByIDExample(connection,0);

        StatementExample.dropTableExample(connection);
        connection.close();
        scanner.close();
    }
}
