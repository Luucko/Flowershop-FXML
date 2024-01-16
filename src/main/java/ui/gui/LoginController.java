package ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import domain.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
                    openBouquetsWindow();
                } else {
                    showErrorPopup("Username and password do not match");
                }

            } catch (FlowershopException e) {
                showErrorPopup(e.getMessage());
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        if (fieldsFilledIn()) {
            try {
                Customer newCustomer = service.registerCustomer(txtLoginField.getText(), txtPasswordField.getText());

                if (newCustomer == null) {
                    showErrorPopup("Username already exists");
                } else {
                    showInfoPopup("Customer " + newCustomer.getLogin() + " registered successfully");
                }
            } catch (FlowershopException e) {
                showErrorPopup(e.getMessage());
            }
        }
    }

    private boolean fieldsFilledIn() {
        String login = txtLoginField.getText();
        String password = txtPasswordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            showErrorPopup("Not all fields are filled in right now");
            return false;
        }
        return true;
    }

    private void openBouquetsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/Bouquets.fxml")));
            Parent parent = loader.load();
            BouquetsController bouquetsController = loader.getController();
            bouquetsController.displayLogin(txtLoginField.getText());
            Scene scene = new Scene(parent);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(scene);
            closeLogin();
            secondaryStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeLogin() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    private void showErrorPopup(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    private void showInfoPopup(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Message", message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtLoginField != null : "fx:id=\"txtLoginField\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtPasswordField != null : "fx:id=\"txtPasswordField\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
