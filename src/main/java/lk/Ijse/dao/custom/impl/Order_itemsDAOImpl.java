package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.order_itemDAO;
import lk.Ijse.entity.Item;
import lk.Ijse.entity.order_item;

import java.sql.SQLException;
import java.util.ArrayList;

public class Order_itemsDAOImpl implements order_itemDAO {
    @Override
    public ArrayList<order_item> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean add(order_item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_item  VALUES (?,?,?,?,?)",dto.getItemID(),dto.getOrderid(),dto.getQty(),dto.getUnitprice(),dto.getTotalprice());
    }

    @Override
    public boolean update(order_item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public order_item search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
