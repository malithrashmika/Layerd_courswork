package lk.Ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.Ijse.Util.Regex;
import lk.Ijse.Util.TextFieldRegex;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.custom.CustomerBO;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.model.CustomerDTO;
import lk.Ijse.tm.CustomerTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class customerController implements Initializable {
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        tblCustomer.getItems().clear();
        try {
            /*Get all customers*/
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers) {
                tblCustomer.getItems().add(new CustomerTm(c.getId(), c.getName(), c.getTel(),c.getEmail()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtsearchId;

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("e_Tel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtemail.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        /*Delete Customer*/
        String id = tblCustomer.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            //Delete Customer
            customerBO.deleteCustomer(id);

            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id =txtId.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();
        String email =txtemail.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!tel.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtTel.requestFocus();
            return;
        }


            /*Save Customer*/
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                //Add Customer
                customerBO.addCustomer(new CustomerDTO(id, name, tel, email));

                tblCustomer.getItems().add(new CustomerTm(id, name, tel, email));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




            CustomerTm selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setTel(tel);
            tblCustomer.refresh();


    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.existCustomer(id);
    }


    @FXML
    void btnUpdateOnaction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();
        String email =txtemail.getText();

        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            //Update Customer
            customerBO.updateCustomer(new CustomerDTO(id, name, tel, email));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        CustomerTm selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        selectedCustomer.setName(name);
        selectedCustomer.setTel(tel);
        tblCustomer.refresh();
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtsearchId.getText();

        customerBO.searchcustomer(id);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(TextFieldRegex.ID,txtId)) return false;
        if (!Regex.setTextColor(TextFieldRegex.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFieldRegex.CONTACT,txtTel)) return false;
        if (!Regex.setTextColor(TextFieldRegex.EMAIL,txtemail)) return false;
        return true;
    }
    @FXML
    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.ID,txtId);
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldRegex.CONTACT,txtTel);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldRegex.EMAIL,txtemail);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldRegex.NAME,txtName);
    }
}
