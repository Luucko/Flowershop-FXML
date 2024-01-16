package util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import ui.cli.Program;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crypto {
    private static final Logger LOGGER = Logger.getLogger(Crypto.class.getName());

    private static final String PASSWORD = "hello-from-howest";
    private static final String SALT = "1AB9F37C2EDA";

    private static final Crypto instance = new Crypto();

    private final TextEncryptor encryptor;

    public static Crypto getInstance() {
        return instance;
    }

    private Crypto() {
        encryptor = Encryptors.text(PASSWORD, SALT);
    }

    public String encrypt(String in) {
        return encryptor.encrypt(in);
    }

    public String decrypt(String in) {
        return encryptor.decrypt(in);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static void main(String[] args) {
        encryptCredentials();
    }

    private static void encryptCredentials() {
        Properties properties = new Properties();

        // Encrypt your sensitive data
        String username = "flowershop-user";
        String password = "flowershop-pwd";

        Crypto crypto = Crypto.getInstance();
        String encryptedUsername = crypto.encrypt(username);
        String encryptedPassword = crypto.encrypt(password);

        // Store the encrypted data in the properties file
        properties.setProperty("db.url", "jdbc:mysql://localhost/flowershop");
        properties.setProperty("db.username", encryptedUsername);
        properties.setProperty("db.password", encryptedPassword);

        try (FileOutputStream out = new FileOutputStream("src/main/resources/config/config.properties")) {
            properties.store(out, "Encrypted database credentials");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred while writing the properties file", ex);

        }
    }
}
