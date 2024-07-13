package lk.Ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.custom.IngredientBO;
import lk.Ijse.bo.custom.ItemBO;
import lk.Ijse.bo.custom.SupplierBO;
import lk.Ijse.db.IngredientCategory;
import lk.Ijse.model.IngredientDTO;
import lk.Ijse.model.SupplierDTO;
import lk.Ijse.tm.IngredientTm;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ingredientController implements Initializable {
    IngredientBO ingredientBO=(IngredientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.IN);
    SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUP);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllIngredients();
        ObservableList<IngredientCategory> category = FXCollections.observableArrayList(IngredientCategory.values());
        cmbInCategory.setItems(category);
        setDataSuppiler();
    }

    private void loadAllIngredients() {
        ObservableList<IngredientTm> obList = FXCollections.observableArrayList();

        try {
            List<IngredientDTO> ingredientDTOList = ingredientBO.getAll();
            for (IngredientDTO ingredientDTO : ingredientDTOList) {
                IngredientTm ingredientTm = new IngredientTm(
                        ingredientDTO.getIngredient_id(),
                        ingredientDTO.getIngredient_name(),
                        ingredientDTO.getCategory(),
                        ingredientDTO.getQty_avalible(),
                        ingredientDTO.getUnit_price(),
                        ingredientDTO.getSupplier_id()
                );

                obList.add(ingredientTm);
            }

            tblIngredients.setItems(obList);
            tblIngredients.refresh(); // Refresh TableView

            // Debugging: Print contents of obList
            System.out.println("Contents of obList:");
            for (IngredientTm ingredientTm : obList) {
                System.out.println(ingredientTm);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colIid.setCellValueFactory(new PropertyValueFactory<>("ingredient_id"));
        colIName.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));
        coliCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colIiqty.setCellValueFactory(new PropertyValueFactory<>("qty_avalible"));
        colIUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
    }


    @FXML
    private ComboBox<String> cmbISupplier;

    @FXML
    private ComboBox<IngredientCategory> cmbInCategory;

    @FXML
    private TableColumn<?, ?> colIName;

    @FXML
    private TableColumn<?, ?> colIUnitPrice;

    @FXML
    private TableColumn<?, ?> colIid;

    @FXML
    private TableColumn<?, ?> colIiqty;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableColumn<?, ?> coliCategory;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<IngredientTm> tblIngredients;

    @FXML
    private TextField txtInName;

    @FXML
    private TextField txtInQty_avalible;

    @FXML
    private TextField txtInUnitPrice;

    @FXML
    private TextField txtInid;

    @FXML
    private TextField txtsearchId;


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtInid.setText("");
        txtInName.setText("");
        txtInQty_avalible.setText("");
        txtInUnitPrice.setText("");
        txtsearchId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtInid.getText();

        try {
            boolean isDeleted = ingredientBO.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                loadAllIngredients();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id =txtInid.getText();
        String name = txtInName.getText();
        String category =cmbInCategory.getSelectionModel().getSelectedItem().toString();
        int qty = Integer.parseInt(txtInQty_avalible.getText());
        double price = Double.parseDouble(txtInUnitPrice.getText());
        String supplier= cmbISupplier.getSelectionModel().getSelectedItem();

        IngredientDTO ingredientDTO = new IngredientDTO(id, name, category,qty,price,supplier);

        try {
            boolean isSaved = ingredientBO.save(ingredientDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                clearFields();
                loadAllIngredients();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id =txtInid.getText();
        String name = txtInName.getText();
        String category =cmbInCategory.getSelectionModel().getSelectedItem().toString();
        int qty = Integer.parseInt(txtInQty_avalible.getText());
        double price = Double.parseDouble(txtInUnitPrice.getText());
        String supplier= cmbISupplier.getSelectionModel().getSelectedItem();

        IngredientDTO ingredientDTO = new IngredientDTO(id, name, category,qty,price,supplier);

        try {
            boolean isUpdated = ingredientBO.update(ingredientDTO);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ingredient updated!").show();
                clearFields();
                loadAllIngredients();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbInCategoryOnAction(ActionEvent event) {
        IngredientCategory ingredientCategory = cmbInCategory.getValue();

    }

    void setDataSuppiler(){
        try {
            List<String> supplier =supplierBO.getIds();

            ObservableList<String> observableList = FXCollections.observableArrayList();


            cmbISupplier.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtsearchId.getText();

        IngredientDTO ingredientDTO = ingredientBO.search(id);
        if (ingredientDTO != null) {
            txtInid.setText(ingredientDTO.getIngredient_id());
            txtInName.setText(ingredientDTO.getIngredient_name());
            cmbInCategory.getSelectionModel().select(IngredientCategory.valueOf(ingredientDTO.getCategory()));
            txtInQty_avalible.setText(String.valueOf(ingredientDTO.getQty_avalible()));
            txtInUnitPrice.setText(String.valueOf(ingredientDTO.getUnit_price()));
            cmbISupplier.getSelectionModel().select(ingredientDTO.getSupplier_id());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Ingredient not found!").show();
        }
    }


}
