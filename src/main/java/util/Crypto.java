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

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkHashedPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
