package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.bo.custom.ItemBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.ItemDAO;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.dao.custom.impl.ItemDAOImpl;
import lk.Ijse.dao.custom.impl.SupplierDAOImpl;
import lk.Ijse.entity.Item;
import lk.Ijse.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO{
    ItemDAO itemDAO  = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allEntityData = itemDAO.getAll();
        ArrayList<ItemDTO> allDTOData= new ArrayList<>();
        for (Item i : allEntityData) {
            allDTOData.add(new ItemDTO( i.getCode(),i.getName(),i.getDescription(i.getCategory(i.getPrice(i.getQtyOnHand()));
        }
        return allDTOData;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return itemDAO.add(new Item( dto.getCode(),dto.getName(),dto.getDescription(),dto.getCategory(),dto.getPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item( dto.getCode(),dto.getName(),dto.getDescription(),dto.getCategory(),dto.getPrice(),dto.getQtyOnHand()));
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

}
