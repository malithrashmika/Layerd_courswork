
package lk.Ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.Ijse.db.DBConnection;
import lk.Ijse.db.TableType;
import lk.Ijse.model.*;
import lk.Ijse.tm.CartTm;
import lk.Ijse.tm.OrderTm;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class orderFormController implements Initializable {

    @FXML
    private Label LblUnitPrice;

    @FXML
    private ComboBox<String> cmbItemID;

    @FXML
    private ComboBox<TableType> cmbordertable;

    @FXML
    private ComboBox<String> cmbwaiterID;

    @FXML
    private TableColumn<?, ?> colCartAction;

    @FXML
    private TableColumn<?, ?> colCartTotal;

    @FXML
    private TableColumn<?, ?> colCartitemID;

    @FXML
    private TableColumn<?, ?> colCartorderdes;

    @FXML
    private TableColumn<?, ?> colCartqty;

    @FXML
    private TableColumn<?, ?> colCartunitPrice;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colReservationID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colitemID;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colunitPrice;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblcustomerName;

    @FXML
    private Label lbltablecharge;

    @FXML
    private TextField txtcusContact;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CartTm> tblCart;

    @FXML
    private TableView<OrderTm> tblOrder;

    @FXML
    private TextField txtqty;

    private ObservableList<CartTm> cartList = FXCollections.observableArrayList();
    private ObservableList<OrderTm> orderList = FXCollections.observableArrayList();
    private double netTotal = 0;
    private double SubTotal=0;
    private String customer_id;



    private int quantityAvailable;
    private ObservableList<?> odList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCartValueFactory();
        setCellValueFactory();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadAllOrders();
            }
        });
        thread.start();
        loadNextOrderId();
        getCurrentOrderId();
        setDate();
        getItemCodes();
        getWaiterIds();
        ObservableList<TableType> Tabletype = FXCollections.observableArrayList(TableType.values());
        cmbordertable.setItems(Tabletype);

        // Check if lblQtyOnHand text is not empty before parsing it to an integer
        if (!lblQtyOnHand.getText().isEmpty()) {
            quantityAvailable = Integer.parseInt(lblQtyOnHand.getText());
        } else {
            // Handle the case where lblQtyOnHand text is empty
            // You can set a default value or show an error message
            // For now, I'll just set it to 0
            quantityAvailable = 0;
            System.err.println("Error: lblQtyOnHand text is empty!");
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
    }


    private void loadNextOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();
            String nextId = nextId(currentId);

            lblOrderID.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
//            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "O" + ++id;

        }
        return "O1";
    }
    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();
            String nextOrderId = generateNextOrderId(currentId);
            lblOrderID.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }

    private void setCartValueFactory() {
        colCartitemID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCartorderdes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCartqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCartunitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCartTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colCartAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = ItemRepo.getcode();
            obList.addAll(codeList);
            cmbItemID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getWaiterIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = EmployeeRepo.getIds();
            obList.addAll(idList);
            cmbwaiterID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemID.getValue());
        String description = lblItemName.getText();
        String qtyText = txtqty.getText();

        // Check if the quantity text field is empty before parsing
        if (qtyText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid quantity.").show();
            return;
        }

        // Parse the quantity
        int qty = Integer.parseInt(qtyText);

        double unitPrice = Double.parseDouble(LblUnitPrice.getText());
        double total = qty * unitPrice;

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            int selectedIndex = tblCart.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                new Alert(Alert.AlertType.WARNING, "Please select an item to remove.").show();
                return;
            }

            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                cartList.remove(selectedIndex);
                tblCart.refresh();
                calculateSubTotal();
            }
        });
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            if (code.equals(colCartitemID.getCellData(i))) {
                qty += cartList.get(i).getQty();
                total = unitPrice * qty;

                cartList.get(i).setQty(qty);
                cartList.get(i).setTotal(total);

                tblCart.refresh();
                calculateSubTotal();
                txtqty.setText("");
                return;
            }
        }


        // Create the CartTm object and add it to the cartList
        CartTm cartTm = new CartTm(code, description, unitPrice, qty, total, btnRemove);
        cartList.add(cartTm);

        // Update the table view with the cartList data
        tblCart.setItems(cartList);

        // Clear the quantity text field and calculate the subtotal
        txtqty.clear();
        tblCart.refresh();
        calculateSubTotal();
        calculateNetTotal();
    }

    private void calculateSubTotal() {
        SubTotal = 0;
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            SubTotal += (double) colCartTotal.getCellData(i);
        }
        lblSubtotal.setText(String.valueOf(SubTotal));
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer.fxml"));
            Parent rootNode = loader.load();

            // Assuming 'root' refers to the root AnchorPane in your current scene
            // Clear the children of the root AnchorPane
            root.getChildren().clear();

            // Add the loaded rootNode as a child to the root AnchorPane
            root.getChildren().add(rootNode);

            // Ensure that the loaded content fits and appears in the correct location
            AnchorPane.setTopAnchor(rootNode, 0.0);
            AnchorPane.setRightAnchor(rootNode, 0.0);
            AnchorPane.setBottomAnchor(rootNode, 0.0);
            AnchorPane.setLeftAnchor(rootNode, 0.0);

            // Optionally, you can adjust the size of the window to fit the new content
            Stage stage = (Stage) root.getScene().getWindow();
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException appropriately
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, JRException, ClassNotFoundException {
        String orderId = lblOrderID.getText();
        String cusId = customer_id;
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        String table =cmbordertable.getSelectionModel().getSelectedItem().toString();
        String empId =cmbwaiterID.getSelectionModel().getSelectedItem();
        double netTotal = Double.parseDouble(lblNetTotal.getText());

        OrderDTO orderDTO = new OrderDTO(orderId, date,time, table,  cusId,empId,netTotal );

        List<order_itemDTO> odList = new ArrayList<>();
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            CartTm tm = cartList.get(i);

            order_itemDTO od = new order_itemDTO(
                    tm.getCode(),
                    orderId,
                    tm.getQty(),
                    tm.getUnitPrice(),
                    tm.getTotal()
            );
            odList.add(od);
        }


        PlaceOrderDTO po = new PlaceOrderDTO(orderDTO, odList);
        boolean isPlaced = PlaceOrderRepo.placeOrder(po);
        if(isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
            loadAllOrders();
            getCurrentOrderId();
            btnPrintBillOnAction(null);
            generateBill(orderId);
        } else {
            new Alert(Alert.AlertType.WARNING, "order not placed!").show();
        }
    }
    private void calculateNetTotal() {
        String tableChargeText = lbltablecharge.getText();
        if (!tableChargeText.isEmpty()) {
            double tableCharge = Double.parseDouble(tableChargeText);
            double netTotal = SubTotal + tableCharge;
            lblNetTotal.setText(String.valueOf(netTotal));
        } else {
            // Add a check here to handle the case when lbltablecharge.getText() is empty
            String lblTableChargeText = lbltablecharge.getText();
            if (!lblTableChargeText.isEmpty()) {
                double tableCharge = Double.parseDouble(lblTableChargeText);
                netTotal = SubTotal + tableCharge;
                lblNetTotal.setText(String.valueOf(netTotal));
            } else {
                // Handle the case when lbltablecharge.getText() is empty
                // For example, set netTotal to SubTotal
                netTotal = SubTotal;
                lblNetTotal.setText(String.valueOf(netTotal));
            }
        }
    }



    @FXML
    void cmbEmployeeOnAction(ActionEvent event) throws SQLException {
        String id = cmbwaiterID.getValue();
        // Lookup employee by ID and update UI
        EmployeeDTO employee = EmployeeRepo.searchById(id);
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();
        try {
            ItemDTO item = ItemRepo.searchByCode(code);
            if (item != null) {
                lblItemName.setText(item.getDescription());
                LblUnitPrice.setText(String.valueOf(item.getPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                quantityAvailable = item.getQtyOnHand();
            } else {
                // Handle the case where the item is not found
                lblItemName.setText("");
                LblUnitPrice.setText("");
                lblQtyOnHand.setText("0");
                quantityAvailable = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtqty.requestFocus();
    }


 /*   @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();
        try {
            Item item = ItemRepo.searchByCode(code);
            if (item != null) {
                lblItemName.setText(item.getDescription());
                LblUnitPrice.setText(String.valueOf(item.getPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtqty.requestFocus();
    }*/

    @FXML
    void cmbTableOnAction(ActionEvent event) {
        TableType tableType=cmbordertable.getValue();
        lbltablecharge.setText(String.valueOf(tableType.getCharge()));
        calculateNetTotal();
    }

    @FXML
    void txtCustomerOnAction(ActionEvent event) {
        String cusTel = txtcusContact.getText();

        try {
            CustomerDTO customer = CustomerRepo.searchByContact(cusTel);

            lblcustomerName.setText(customer.getName());
            customer_id=customer.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
   private void setCellValueFactory() {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("tableType"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("waiter"));
       colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
       colitemID.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
       colunitPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
       colqty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));
    }

    private void generateBill(String orderId) {
//        try {
//
//            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Reports/CustomerOrderBill.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("orderId", orderId);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (JRException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
    @FXML
    void btnPrintBillOnAction(ActionEvent event) throws SQLException, JRException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Reports/CocktailBill.jrxml");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT \n" +
                "    o.order_id,\n" +
                "    o.date,\n" +
                "    o.time,\n" +
                "    o.table,\n" +
                "    o.netTotal,\n" +
                "    c.customer_id,\n" +
                "    c.name AS customer_name,\n" +
                "    e.employee_id,\n" +
                "    i.item_id,\n" +
                "    i.name AS item_name,\n" +
                "    i.description,\n" +
                "    i.Category,\n" +
                "    i.price,\n" +
                "    i.Qty_On_Hand,\n" +
                "    oi.qty,\n" +
                "    oi.unit_price,\n" +
                "    oi.subtotal\n" +
                "FROM \n" +
                "    order_item oi\n" +
                "JOIN \n" +
                "    orders o ON oi.order_id = o.order_id\n" +
                "JOIN \n" +
                "    customer c ON o.customer_id = c.customer_id\n" +
                "JOIN \n" +
                "    employee e ON o.employee_id = e.employee_id\n" +
                "JOIN \n" +
                "    item i ON oi.item_id = i.item_id\n" +
                "WHERE \n" +
                "    o.order_id = (SELECT MAX(order_id) FROM orders);\n");
        jasperDesign.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);



        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, null, DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
   private void loadAllOrders() {


       ObservableList<OrderTm> obList = FXCollections.observableArrayList();

       try {
           List<OrderDetailsDTO> orderDetailsDTOList = PlaceOrderRepo.getordersAll();
           for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOList) {
               OrderTm tm = new OrderTm(
                       orderDetailsDTO.getOrderId(),
                       orderDetailsDTO.getOrderDate(),
                       orderDetailsDTO.getOrderTime(),
                       orderDetailsDTO.getTableType(),
                       orderDetailsDTO.getWaiter(),
                       orderDetailsDTO.getCustomerID(),
                       orderDetailsDTO.getItemId(),
                       orderDetailsDTO.getOrderPrice(),
                       orderDetailsDTO.getQuantity(),
                       orderDetailsDTO.getNetTotal()

               );

               obList.add(tm);
           }

           tblOrder.setItems(obList);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}


