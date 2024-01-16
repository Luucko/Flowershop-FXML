package ui.gui;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CustomerService;
import util.exceptions.FlowershopException;

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
    private final CustomerService service = new CustomerService();

    @FXML
    void onLogin(ActionEvent event) {
        if (fieldsFilledIn()) {
            try {
                boolean isValidUser = service.checkCustomerCredentials(txtLoginField.getText(), txtPasswordField.getText());

                if (isValidUser) {
                    System.out.println("customer logged in successfully");
                    // successful screen popup
                    close();
                } else {
                    System.out.println("username and password do not match");
                    // errorscreen popup
                }

            } catch (FlowershopException e) {
                // Handle other exceptions, if any
                System.out.println(e);
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        if (fieldsFilledIn()) {
            try {
                Customer newCustomer = service.registerCustomer(txtLoginField.getText(), txtPasswordField.getText());

                if (newCustomer == null) {
                    System.out.println("username already exists");
                    // errorscreen popup
                } else {
                    System.out.println("new customer registered successfully");
                    // successful screen popup
                    close();
                }
            } catch (FlowershopException e) {
                // Handle other exceptions, if any
                System.out.println(e);
            }
        }
    }

    private boolean fieldsFilledIn() {
        String login = txtLoginField.getText();
        String password = txtPasswordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            System.out.println("Not all fields are filled in right now");
            return false;
        }

        return true;
    }

    private void close() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtLoginField != null : "fx:id=\"txtLoginField\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtPasswordField != null : "fx:id=\"txtPasswordField\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
