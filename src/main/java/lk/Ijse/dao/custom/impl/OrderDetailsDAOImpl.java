package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.OrderDetailsDAO;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.OrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> allorders = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details_view");
        while (rst.next()) {
            OrderDetails orderDetails= new OrderDetails(rst.getString("order_id"),rst.getDate("date"),rst.getTime("time"),rst.getString("table"),rst.getString("cus_id"),rst.getString("emp_id"),rst.getString("itemId"),rst.getDouble("unitPrice"),rst.getInt("qty"),rst.getDouble("netTotal"));
            allorders.add(orderDetails);
        }
        return allorders;
    }

    @Override
    public boolean add(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails VALUES (?,?,?,?)", entity.getOrderId(),entity.getOrderDate(),entity.getOrderTime(),entity.getTableType(),entity.getWaiter(),entity.getCustomerID(),entity.getItemId(),entity.getOrderPrice(),entity.getQuantity(),entity.getNetTotal());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public ReservationDTO search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }
}
