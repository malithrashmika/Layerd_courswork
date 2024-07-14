package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.IngredientBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.IngredientDAO;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.dao.custom.impl.IngredientDAOImpl;
import lk.Ijse.dao.custom.impl.SupplierDAOImpl;
import lk.Ijse.dto.ItemDTO;
import lk.Ijse.entity.Ingredient;
import lk.Ijse.entity.Item;
import lk.Ijse.model.IngredientDTO;
import lk.Ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBOImpl implements IngredientBO {
   IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> allEntityData = ingredientDAO.getAll();
        ArrayList<IngredientDTO> allDTOData= new ArrayList<>();
        for (Ingredient dto : allEntityData) {
            allDTOData.add(new IngredientDTO( dto.getIngredient_id(),dto.getIngredient_name(),dto.getCategory(),dto.getQty_avalible(),dto.getUnit_price(),dto.getSupplier_id()));
        }
        return allDTOData;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return ingredientDAO.delete(id);
    }

    @Override
    public boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException {

        return ingredientDAO.add(new Ingredient( dto.getIngredient_id(),dto.getIngredient_name(),dto.getCategory(),dto.getQty_avalible(),dto.getUnit_price(),dto.getSupplier_id()));
    }

    @Override
    public boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException {

        return ingredientDAO.update(new Ingredient( dto.getIngredient_id(),dto.getIngredient_name(),dto.getCategory(),dto.getQty_avalible(),dto.getUnit_price(),dto.getSupplier_id()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        return ingredientDAO.exist(id);
    }



}
