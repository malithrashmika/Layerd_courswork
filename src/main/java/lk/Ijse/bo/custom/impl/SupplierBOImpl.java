package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.bo.custom.SupplierBO;
import lk.Ijse.dao.custom.EmployeeDAO;
import lk.Ijse.dao.custom.SupplierDAO;
import lk.Ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.dao.custom.impl.SupplierDAOImpl;
import lk.Ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {

        return supplierDAO.getAll();
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(dto);
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(dto);
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getIDS();
    }
}
