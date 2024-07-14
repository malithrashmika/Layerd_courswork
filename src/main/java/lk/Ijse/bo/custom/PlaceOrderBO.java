package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.dto.OrderDTO;
import lk.Ijse.model.ItemDTO;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public boolean purchaseOrder(OrderDTO dto)throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String code)throws SQLException, ClassNotFoundException;
}
