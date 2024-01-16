package ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

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
    private ListView<?> listViewFlowers;

    @FXML
    private ListView<?> listViewOrderList;

    @FXML
    private Tab tabConfigureBouquet;

    @FXML
    private Tab tabOrderList;

    @FXML
    private TextField txtAmountOfFlowersField;

    @FXML
    private Label txtCustomerLogin;

    @FXML
    private Label txtTotalPrice;
    public void displayLogin(String login) {
        txtCustomerLogin.setText("Customer: " + login);
    }

    @FXML
    void onClose(ActionEvent event) {

    }

    @FXML
    void onGenerateBouquet(ActionEvent event) {

    }

    @FXML
    void onPlaceOrder(ActionEvent event) {

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
        assert txtCustomerLogin != null : "fx:id=\"txtCustomerLogin\" was not injected: check your FXML file 'Bouquets.fxml'.";
        assert txtTotalPrice != null : "fx:id=\"txtTotalPrice\" was not injected: check your FXML file 'Bouquets.fxml'.";
    }
}
