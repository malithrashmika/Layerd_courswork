package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class  DashboardController{

    @FXML
    private BarChart<?, ?> order_item;


    @FXML
    private AnchorPane root;

    @FXML
    private Label currentDate;

    @FXML
    private Label currentTime;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblUserCount;

    @FXML
    private Label lblbookCount;


    private int currentIndex = 0;

    private int EmployeeCount;

    private int UserCount;

    private int BookingCount;

    private int OrderCount;

    public void initialize() {
        setDate();
        setTime();
        try {
            EmployeeCount = getEmployeeCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            OrderCount = getOrderCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        try {
            UserCount = getUserCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            BookingCount = getBookingCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setBookingCount(BookingCount);
        setEmployeeCount(EmployeeCount);
        setUserCount(UserCount);
        setOrderCount(OrderCount);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        currentDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.toString();
        currentTime.setText(formattedTime);
        currentTime.setEllipsisString(null);
    }
    private void setOrderCount(int UserCount) {
        lblOrderCount.setText(String.valueOf(UserCount));

    }
    private int getOrderCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS Order_count FROM orders";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Order_count");
        }
        return 0;
    }


    private void setUserCount(int UserCount) {
        lblUserCount.setText(String.valueOf(UserCount));

    }

    private int getUserCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS User_count FROM users";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("User_count");
        }
        return 0;
    }

    private void setEmployeeCount(int EmployeeCount) {
        lblEmployeeCount.setText(String.valueOf(EmployeeCount));
    }

    private int getEmployeeCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS Employee_count FROM employee";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Employee_count");
        }
        return 0;
    }


    private void setBookingCount(int BookingCount) {
        lblbookCount.setText(String.valueOf(BookingCount));
    }

    private int getBookingCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS Booking_count FROM reservation";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Booking_count");
        }
        return 0;
    }


    @FXML
    void btnHomeAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reservation.fxml"));
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
    void btnCustomerOnAction(ActionEvent event) throws IOException {
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
    void btnIngredientOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ingredient.fxml"));
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
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    @FXML
    void btnMenuOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemForm.fxml"));
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
    void btnOrderDetailsOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderForm.fxml"));
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
    void btnRideOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ride.fxml"));
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
    void btnStaffOnAction(ActionEvent event) throws IOException {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee.fxml"));
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
    void btnSupplierOnAction(ActionEvent event) {
        try {
            // Load the Customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier.fxml"));
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


}

