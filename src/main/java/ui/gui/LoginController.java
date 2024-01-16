package ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtLoginField;

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    void onLogin(ActionEvent event) {

    }

    @FXML
    void onRegister(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtLoginField != null : "fx:id=\"txtLoginField\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtPasswordField != null : "fx:id=\"txtPasswordField\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
