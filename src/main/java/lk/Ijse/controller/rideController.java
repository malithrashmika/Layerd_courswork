package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.Ijse.API.api;
import lk.Ijse.model.CustomerDTO;

import java.sql.SQLException;

public class rideController {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblcustomerName;

    @FXML
    private TextArea txtCusAddress;

    @FXML
    private TextField txtcusContact;

    @FXML
    void btnRequestRideOnAction(ActionEvent event) {
        api ap = new api();
        try {
            // Get the customer contact and message from the UI components
            String to = txtcusContact.getText();
            String msg = txtCusAddress.getText();

            // Replace "cocktail_hub" and "maliyah$1225" with actual username and password
            // Call the sms method to send the message
            ap.sms("cocktail_hub", "maliyah$1225", msg);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during the SMS sending process

        }
        new Alert(Alert.AlertType.CONFIRMATION, "Ride is Requested..Driver is on the way!").show();
    }

    @FXML
    void txtCustomerOnAction(ActionEvent event) {
        // This method is triggered when the customer text field action is performed
        // You can add logic here if needed
        String cusTel = txtcusContact.getText();

        try {
            CustomerDTO customer = CustomerRepo.searchByContact(cusTel);

            lblcustomerName.setText(customer.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
