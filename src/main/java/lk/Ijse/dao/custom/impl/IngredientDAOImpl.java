package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.IngredientDAO;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.model.IngredientDTO;
import lk.Ijse.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> allIngredients = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        while (rst.next()) {
            IngredientDTO ingredientDTO = new IngredientDTO(rst.getString("Ingredient_id"),rst.getString("Ingredient_name"),rst.getString("category"),rst.getInt("qty_avalible"),rst.getDouble("unit_price"),rst.getString("supplier_id"));
            allIngredients.add(ingredientDTO);
        }
        return allIngredients;
    }

    @Override
    public boolean add(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO ingredient (ingredient_id,name,category,qty_available,unit_price,supplier_id) VALUES (?,?,?,?,?,?)", dto.getIngredient_id(),dto.getIngredient_name(),dto.getCategory(),dto.getQty_avalible(),dto.getUnit_price(),dto.getSupplier_id());
    }

    @Override
    public boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE ingredient SET name=?,category=?,qty_available=?,unit_price=?,supplier_id=? WHERE ingredient_id=?",dto.getIngredient_name(),dto.getCategory(),dto.getQty_avalible(),dto.getUnit_price(),dto.getSupplier_id(),dto.getIngredient_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  ingredient_id FROM ingredient WHERE ingredient_id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM ingredient ORDER BY ingredient_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("ingredient_id");
            int newItemId = Integer.parseInt(id.replace("IN00-", "")) + 1;
            return String.format("IN00-%03d", newItemId);
        } else {
            return "IN00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM ingredient WHERE ingredient_id=?", id);
    }

    @Override
    public ReservationDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM ingredient WHERE ingredient_id=?", id + "");
        rst.next();
        return new IngredientDTO(id + "", rst.getString("name"), rst.getString("category"), rst.getInt("qty_available"), rst.getDouble("unit_price"), rst.getString("supplier_id"));
    }
}
