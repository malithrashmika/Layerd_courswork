package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.model.IngredientDTO;
import lk.Ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientBO extends SuperBO {
    public ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException;

    public boolean exist(String id) throws SQLException, ClassNotFoundException ;

    public IngredientDTO search(String id) throws SQLException, ClassNotFoundException;
}
