package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    boolean add(OrderDetails entity) throws SQLException, ClassNotFoundException;
}
