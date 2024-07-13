package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.EmployeeBO;
import lk.Ijse.dao.custom.EmployeeDAO;
;
import lk.Ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.getAll();
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.add(dto);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.update(dto);
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.exist(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.delete(id);
    }
    
    @Override
    public EmployeeDTO searchemployee(String id) throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.search(id);
    }
}
