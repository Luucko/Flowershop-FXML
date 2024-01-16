package data.implementation;

import data.repository.CustomersRepository;
import data.util.MySqlConnection;
import domain.Customer;
import util.Crypto;
import util.exceptions.FlowershopException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomersRepositoryImpl implements CustomersRepository {

    private static final Logger LOGGER = Logger.getLogger(CustomersRepositoryImpl.class.getName());

    private static final String SQL_REGISTER_CUSTOMER = "INSERT INTO customers(login, PASSWORD) VALUES(?,?)";
    private static final String SQL_CHECK_CUSTOMER_EXISTENCE = "SELECT COUNT(*) FROM customers WHERE login = ?";
    private static final String SQL_GET_HASHED_PASSWORD = "SELECT password FROM customers WHERE login = ?";

    public Customer registerCustomer(String login, String password) {
        if (exists(login)) {
            return null; //customer already exists
        }

        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_REGISTER_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, login);
            String hashedPassword = Crypto.getInstance().hashPassword(password);
            statement.setString(2, hashedPassword);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                return new Customer(login, password);
            }

            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unable to register customer to the database", e);
            throw new FlowershopException("Unable to register customer to the database");
        }
    }

    public boolean checkCustomerCredentials(String login, String password) {
        if (exists(login)){
            return checkCustomerPassword(login, password);
        }
        return false; //login does not exist
    }

    private boolean checkCustomerPassword(String login, String password) {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_HASHED_PASSWORD);
        ) {
            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDatabase = resultSet.getString("password");
                    return Crypto.getInstance().checkPassword(password, hashedPasswordFromDatabase);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking customer password in the database", e);
            throw new FlowershopException("Error checking customer password in the database");
        }

        return false; //password is invalid
    }




    private boolean exists(String login) {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_CHECK_CUSTOMER_EXISTENCE)
        ) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking customer existence in the database", e);
            throw new FlowershopException("Error checking customer existence in the database");
        }
        return false;
    }
}
