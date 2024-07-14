package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;
}
