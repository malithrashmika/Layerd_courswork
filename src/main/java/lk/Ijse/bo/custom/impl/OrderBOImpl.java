package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.OrderBO;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.OrderDAO;
import lk.Ijse.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.dao.custom.impl.OrderDAOImpl;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
   OrderDAO orderDAO= new OrderDAOImpl();
    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }
}
