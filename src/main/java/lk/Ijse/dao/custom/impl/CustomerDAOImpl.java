package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.entity.Customer;
import lk.Ijse.entity.Item;
import lk.Ijse.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("email"), rst.getString("phone"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getEmail(),dto.getTel());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(), dto.getEmail(),dto.getTel(), dto.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("email"), rst.getString("e_Tel"));
    }

    @Override
    public List<String> getIDS() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
