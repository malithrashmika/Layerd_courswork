package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.IngredientBO;
import lk.Ijse.dao.custom.IngredientDAO;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.dao.custom.impl.IngredientDAOImpl;
import lk.Ijse.dao.custom.impl.SupplierDAOImpl;
import lk.Ijse.model.IngredientDTO;
import lk.Ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBOImpl implements IngredientBO {
    @Override
    public ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.getAll();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.delete(id);
    }

    @Override
    public boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.add(dto);
    }

    @Override
    public boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.update(dto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.exist(id);
    }

    @Override
    public IngredientDTO search(String id) throws SQLException, ClassNotFoundException {
        IngredientDAO dao = new IngredientDAOImpl();
        return dao.search(id);
    }


}
