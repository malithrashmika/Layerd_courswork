/*package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Item;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.ItemRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class itemController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private JFXComboBox<?> cmbitemType;

    @FXML
    private JFXComboBox<?> cmbtype;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private ImageView item;

    @FXML
    private TableView<?> tblItem;

    @FXML
    private TextField txtcode;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtprice;

    @FXML
    private AnchorPane root;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtcode.getText();
        String description = txtname.getText();
        String type= String.valueOf(cmbtype.getValue());
        double price = Double.parseDouble(txtprice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        Item item = new Item(code, description, type, price, qtyOnHand);

        try {
            boolean isSaved = ItemRepo.save(item);
            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtcode.getText();
        String description = txtname.getText();
        String type= String.valueOf(cmbtype.getValue());
        double price = Double.parseDouble(txtprice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        Item item = new Item(code, description,type, price, qtyOnHand);

        try {
            boolean isUpdated = ItemRepo.update(item);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String id = (String) cmbtype.getValue();
        try {
           Item item1 =ItemRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtcode.setText("");
        txtname.setText("");
        txtprice.setText("");
        txtQtyOnHand.setText("");
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }
    @FXML
    void cmbItemtOnAction(ActionEvent event) {

    }
}*/

package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.Ijse.Util.Regex;
import lk.Ijse.Util.TextFieldRegex;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.custom.CustomerBO;
import lk.Ijse.bo.custom.ItemBO;
import lk.Ijse.db.ResturentItem;
import lk.Ijse.model.ItemDTO;
import lk.Ijse.tm.ItemTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class itemController implements Initializable {
    ItemBO itemBO  = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @FXML
    private TableView<ItemDTO> tableView;
    @FXML
    private JFXComboBox<ResturentItem> ItemType;

    @FXML
    private TableColumn<?, ?> colItemDes;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemType;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemDes;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQty;

    @FXML
    private TextField txtsearchId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllItems();
        ObservableList<ResturentItem> types = FXCollections.observableArrayList(ResturentItem.values());
        ItemType.setItems(types);
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemList =itemBO.getAllItems();
            for (ItemDTO item : itemList) {
                ItemTm itemTm = new ItemTm(
                        item.getCode(),
                        item.getName(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getPrice(),
                        item.getQtyOnHand()
                );

                obList.add(itemTm);
            }

            tblItem.setItems(obList);
            tblItem.refresh(); // Refresh TableView

            // Debugging: Print contents of obList

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDes.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQtyOnHand"));
    }


    @FXML
    void UpdateOnAction(ActionEvent event) {
        String code = txtItemID.getText();
        String name = txtItemName.getText();
        String des = txtItemDes.getText();
        String category = ItemType.getSelectionModel().getSelectedItem().toString();
        double price = Double.parseDouble(txtItemPrice.getText());
        int qty = Integer.parseInt(txtItemQty.getText());

        ItemDTO item = new ItemDTO(code, name, des, category, price, qty);

        try {
            boolean isUpdated = itemBO.updateItem(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                refreshTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtItemID.setText("");
        txtItemName.setText("");
        txtItemDes.setText("");
        txtItemPrice.setText("");
        txtItemQty.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemID.getText();

        try {
            boolean isDeleted =itemBO.deleteItem(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                refreshTable();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtItemID.getText();
        String name = txtItemName.getText();
        String des = txtItemDes.getText();
        String category = ItemType.getSelectionModel().getSelectedItem().toString();
        double price = Double.parseDouble(txtItemPrice.getText());
        int qty = Integer.parseInt(txtItemQty.getText());

        ItemDTO item = new ItemDTO(code, name, des, category, price, qty);

        try {
            boolean isSaved =itemBO.saveItem(item);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                clearFields();
                refreshTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void itemTypeOnAction(ActionEvent event) {
        ResturentItem resturentItem = ItemType.getValue();
        if (resturentItem != null) {

        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtsearchId.getText();

        ItemDTO item = itemBO.search(id);
        if (item != null) {
            txtItemID.setText(item.getCode());
            txtItemName.setText(item.getName());
            txtItemDes.setText(item.getDescription());
            ItemType.getItems().add(ResturentItem.valueOf(item.getCategory()));
            txtItemPrice.setText(String.valueOf(item.getPrice()));
            txtItemQty.setText(String.valueOf(item.getQtyOnHand()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "ITEM not found!").show();
        }
    }

    private void refreshTable() {
        try {
            // Reload data from the database or any other source
            List<ItemDTO> itemList =itemBO.getAllItems();
            ObservableList<ItemTm> obList = FXCollections.observableArrayList();

            for (ItemDTO item : itemList) {
                obList.add(new ItemTm(
                        item.getCode(),
                        item.getName(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getPrice(),
                        item.getQtyOnHand()
                ));
            }

            // Clear and set new items to the TableView
            tblItem.getItems().clear();
            tblItem.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void txtIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.ID, txtItemID);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
    }

    public void UnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.PRICE, txtItemPrice);
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldRegex.QTY, txtItemQty);
    }
}




