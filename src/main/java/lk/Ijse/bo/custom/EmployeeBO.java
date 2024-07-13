package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchemployee(String id) throws SQLException, ClassNotFoundException;
}
