package it.sevenbits;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/servlet";
    private static final String DB_USER = "servlet";
    private static final String DB_PASSWORD = "password";

    public Connection getDBConnection() {

        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public void insert(final String firstName, final String lastName, final String email) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement statement = null;

        String insertTableSQL = "INSERT INTO user"
            + "(first_name, last_name, email) " + "VALUES"
            + "('" + firstName + "', '" + lastName + "', '" + email + "')";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement(insertTableSQL);

            System.out.println(insertTableSQL);

            // execute insert SQL stetement
            statement.execute(insertTableSQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    public List<User> selectAll() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();

        String selectSQL = "SELECT * FROM user";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement(selectSQL);

            ResultSet resultSet = statement.executeQuery(selectSQL);
            User user;
            while (resultSet.next()) {
                user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

        return users;
    }
}
