package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;
}
