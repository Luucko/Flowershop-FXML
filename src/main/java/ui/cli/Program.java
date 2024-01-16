package ui.cli;

import data.implementation.ExamRepositoryImplementation;
import data.repository.ExamRepository;
import domain.ExamClass;
import util.Crypto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
    private static final Logger LOGGER = Logger.getLogger(Program.class.getName());

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        encryptCredentials();
        getVersion();
    }

    private void getVersion() {
        ExamRepositoryImplementation examRepositoryImplementation = new ExamRepositoryImplementation();
        System.out.println(examRepositoryImplementation.repositoryAction());
    }

    private void encryptCredentials() {
        Properties properties = new Properties();

        // Encrypt your sensitive data
        String username = "exam-user";
        String password = "exam-pwd";

        Crypto crypto = Crypto.getInstance();
        String encryptedUsername = crypto.encrypt(username);
        String encryptedPassword = crypto.encrypt(password);

        // Store the encrypted data in the properties file
        properties.setProperty("db.url", "jdbc:mysql://localhost/exam");
        properties.setProperty("db.username", encryptedUsername);
        properties.setProperty("db.password", encryptedPassword);

        try (FileOutputStream out = new FileOutputStream("src/main/resources/config/config.properties")) {
            properties.store(out, "Encrypted database credentials");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred while writing the properties file", ex);

        }
    }
}
