package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.model.EmployeeDTO;
import lk.Ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
}
