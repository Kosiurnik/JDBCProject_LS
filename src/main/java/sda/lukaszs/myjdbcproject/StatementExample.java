package sda.lukaszs.myjdbcproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class StatementExample {
    static void createTableExample(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS employee(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(50) not null, " +
                "salary int, " +
                "primary key (id) )");
    }
    static void insertExample(Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `employee` (`id`, `name`, `salary`) VALUES (NULL, ?, ?)");
        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1,"Grzegorz Brzęczyszczykiewicz");
            preparedStatement.setInt(2,3000+(100*i));
            preparedStatement.executeUpdate();
        }
    }

    static void deleteExample(Connection connection, int salary) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `employee` WHERE `salary` < ?");
        preparedStatement.setInt(1, salary);
        preparedStatement.execute();
    }

    static void dropTableExample(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE employee");
    }

    static void insertWithParametersExample(Connection connection, String name, int salary) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `employee` (`id`, `name`, `salary`) VALUES (NULL, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,salary);
        preparedStatement.executeUpdate();
    }

    static void deleteByIDExample(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `employee` WHERE `id` = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
