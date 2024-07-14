package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.entity.Item;
import lk.Ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString("id"),rst.getString("name"),rst.getString("contactNumber"),rst.getString("contactEmail"));
            allSuppliers.add(supplier);
        }
        return allSuppliers;
    }



    @Override
    public boolean add(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier (id,name,contact,email) VALUES (?,?,?,?)", dto.getSupplierId(),dto.getSupplierName(),dto.getContactNumber(),dto.getContactEmail());

    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE SUPPLIER SET name=?,contact=?,email=? WHERE id=?",dto.getSupplierName(),dto.getContactNumber(),dto.getContactEmail(),dto.getSupplierId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM SUPPLIER WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM SUPPLIER ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newSupplierId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newSupplierId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE id=?", id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier WHERE id=?", id + "");
        rst.next();
        return new Supplier(id + "", rst.getString("name"), rst.getString("contact"), rst.getString("email"));
    }

    @Override
    public List<String> getIDS() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM supplier");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
