package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.entity.Item;
import lk.Ijse.model.ItemDTO;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean add(Item dto) throws SQLException, ClassNotFoundException;

    public boolean updateQty(String Item_Id, int qty) throws SQLException, ClassNotFoundException;
}
