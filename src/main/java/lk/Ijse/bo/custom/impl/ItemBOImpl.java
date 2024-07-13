package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.bo.custom.ItemBO;
import lk.Ijse.dao.custom.ItemDAO;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.dao.custom.impl.ItemDAOImpl;
import lk.Ijse.dao.custom.impl.SupplierDAOImpl;
import lk.Ijse.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO, SuperBO {
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.getAll();
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.add(dto);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.update(dto);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.generateNewID();
    }

    @Override
    public ItemDTO search(String id) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO= new ItemDAOImpl();
        return itemDAO.search(id);
    }
}
