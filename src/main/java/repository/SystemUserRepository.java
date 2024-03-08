package repository;

import model.SystemUser;
import utils.PropertyUtils;
import utils.SQLUtils;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SystemUserRepository {
    private final Properties properties;
    public SystemUserRepository() {
        properties = PropertyUtils.loadProperty();
    }
    private Connection startDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("USERNAME"),
                properties.getProperty("PASSWORD")
        );
    }
    private String inputNewUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a new username: ");
        return scanner.nextLine();
    }
public void signUp(SystemUser systemUser) {
    boolean usernameExists = false;
    do {
        try (Connection connection = startDatabaseConnection();
             PreparedStatement checkUsernameStatement = connection.prepareStatement(SQLUtils.SystemUserSQL.CHECK_USERNAME);
             PreparedStatement signUpStatement = connection.prepareStatement(SQLUtils.SystemUserSQL.SIGNUP)) {
            // Check if username already exists
            checkUsernameStatement.setString(1, systemUser.getUsername());
            ResultSet resultSet = checkUsernameStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username already exists. Please choose a different username.");
                usernameExists = true;
                // Prompt the user to input a new username
                systemUser.setUsername(inputNewUsername());
            } else {
                usernameExists = false;
                // If username doesn't exist, proceed with signup
                signUpStatement.setString(1, systemUser.getUsername());
                signUpStatement.setString(2, systemUser.getPassword());
                signUpStatement.executeUpdate();
                System.out.println("Sign up successful. Welcome, " + systemUser.getUsername() + "!");
            }
        } catch (SQLException ex) {
            System.out.println("Error occurred during sign up. Please try again later.");
            ex.printStackTrace();
        }
    } while (usernameExists);
}
    public void Login(SystemUser systemUser) {
        try (Connection connection = startDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLUtils.SystemUserSQL.LOGIN)) {
            preparedStatement.setString(1, systemUser.getUsername());
            preparedStatement.setString(2, (systemUser.getPassword()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sendExecutiveCode();
            } else {
                System.out.println("Login failed. Please check your credentials or sign up for a new account.");
                signUp(new SystemUser().input(new Scanner(System.in)));
                System.out.println("Please note this new data for your account thank You");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during login. Please try again later.");
            e.printStackTrace();
        }
    }
    private static void sendExecutiveCode() {
        System.out.println("Login successfully.");
    }

}

