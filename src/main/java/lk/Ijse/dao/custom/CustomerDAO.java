package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.entity.Customer;
import lk.Ijse.model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<String> getIDS() throws SQLException, ClassNotFoundException;

}
