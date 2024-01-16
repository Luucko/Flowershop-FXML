package data.implementation;

import data.util.MySqlConnection;
import domain.ExamClass;
import util.exceptions.ExamException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamRepositoryImplementation {
    private static final Logger LOGGER = Logger.getLogger(ExamRepositoryImplementation.class.getName());
    private static final String SQL_GET_VERSION = "SELECT VERSION()";

    public String repositoryAction() {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_VERSION);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new ExamException("Unable to retrieve MySQL version");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve MySQL version from the database", e);
            throw new ExamException("Unable to retrieve MySQL version from the database");
        }
    }
}
