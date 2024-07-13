package lk.Ijse.controller;

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
import javafx.scene.layout.AnchorPane;
import lk.Ijse.Util.Regex;
import lk.Ijse.Util.TextFieldRegex;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.custom.SupplierBO;
import lk.Ijse.model.SupplierDTO;
import lk.Ijse.tm.SupplierTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class supplierController implements Initializable {
    SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUP);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllSupplier();
    }
    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierDTOList = supplierBO.getAllSuppliers();
            for (SupplierDTO supplierDTO : supplierDTOList) {
                SupplierTm tm = new SupplierTm(
                        supplierDTO.getSupplierId(),
                        supplierDTO.getSupplierName(),
                        supplierDTO.getContactNumber(),
                        supplierDTO.getContactEmail()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearchId;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtemail;

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("e_Tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtemail.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        try {
            boolean isDeleted = supplierBO.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                loadAllSupplier();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id =txtID.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();
        String email =txtemail.getText();

        SupplierDTO supplierDTO = new SupplierDTO(id, name, tel, email);

        try {
            boolean isSaved = supplierBO.addSupplier(supplierDTO);
            if (isValid()) {
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                    clearFields();
                    loadAllSupplier();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id =txtID.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();
        String email =txtemail.getText();

        SupplierDTO supplierDTO = new SupplierDTO(id, name, tel, email);
        try {
            boolean isSaved = supplierBO.updateSupplier(supplierDTO);
            if (isValid()) {
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                    clearFields();
                    loadAllSupplier();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtSearchId.getText();

        SupplierDTO supplierDTO = supplierBO.search(id);
        if (supplierDTO != null) {
            txtID.setText(supplierDTO.getSupplierId());
            txtName.setText(supplierDTO.getSupplierName());
            txtTel.setText(supplierDTO.getContactNumber());
            txtemail.setText(supplierDTO.getContactEmail());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFieldRegex.ID,txtID)) return false;
        if (!Regex.setTextColor(TextFieldRegex.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFieldRegex.CONTACT,txtTel)) return false;
        if (!Regex.setTextColor(TextFieldRegex.EMAIL,txtemail)) return false;
        return true;
    }


    public void txtIDOnKeyRelease(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.ID,txtID);

    }

    public void NameOnKeyRelease(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.NAME,txtName);
    }

    public void contactOnKeyRelease(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.CONTACT,txtTel);
    }

    public void EmailOnKeyRelease(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.EMAIL,txtemail);
    }
}
