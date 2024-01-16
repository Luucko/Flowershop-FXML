package ui.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import domain.Flower;
import domain.Bouquet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.BouquetService;
import services.CustomerService;

public class BouquetsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnGenerateBouquet;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ListView<Flower> listViewFlowers;

    @FXML
    private ListView<Bouquet> listViewOrderList;

    @FXML
    private Tab tabConfigureBouquet;

    @FXML
    private Tab tabOrderList;

    @FXML
    private TextField txtAmountOfFlowersField;

    @FXML
    private Label txtCustomerName;

    @FXML
    private Label txtTotalPrice;

    private final BouquetService service = new BouquetService();
    public void displayLogin(String login) {
        txtCustomerName.setText("Customer: " + login);
    }

    @FXML
    void onClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onGenerateBouquet(ActionEvent event) {
        try {
            int amountOfFlowers = Integer.parseInt(txtAmountOfFlowersField.getText());
            if (amountOfFlowers < 5 || amountOfFlowers > 25) {
                showErrorPopup("Amount of flowers must be between 5 and 25");
                return;
            }
            Bouquet generatedBouquet = service.generateBouquet(amountOfFlowers);
            displayGeneratedFlowers(generatedBouquet);
        } catch (NumberFormatException e) {
            showErrorPopup("Please enter a valid number for the amount of flowers");
        }
    }

    @FXML
    private void displayGeneratedFlowers(Bouquet bouquet) {
        List<Flower> flowers = bouquet.getFlowers();
        listViewFlowers.setItems(FXCollections.observableArrayList(flowers));
        txtTotalPrice.setText(String.format("%.2f", bouquet.calculateTotalPrice()));
    }

    @FXML
    void onPlaceOrder(ActionEvent event) {

    }

    private void showErrorPopup(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
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
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert btnGenerateBouquet != null : "fx:id=\"btnGenerateBouquet\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert btnPlaceOrder != null : "fx:id=\"btnPlaceOrder\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert listViewFlowers != null : "fx:id=\"listViewFlowers\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert listViewOrderList != null : "fx:id=\"listViewOrderList\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert tabConfigureBouquet != null : "fx:id=\"tabConfigureBouquet\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert tabOrderList != null : "fx:id=\"tabOrderList\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert txtAmountOfFlowersField != null : "fx:id=\"txtAmountOfFlowersField\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert txtCustomerName != null : "fx:id=\"txtCustomerLogin\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert txtTotalPrice != null : "fx:id=\"txtTotalPrice\" was not injected: check your FXML file 'Bouquets.fxml'.";
        txtAmountOfFlowersField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("\\d*")) ? change : null));
    }
}
