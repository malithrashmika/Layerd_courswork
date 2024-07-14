package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.EmployeeBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.EmployeeDAO;
;
import lk.Ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.entity.Employee;
import lk.Ijse.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Employee);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allCustomers= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee c : all) {
            allCustomers.add(new EmployeeDTO(c.getId(), c .getName(), c .getTel(), c .getSalary(), c .getRole()));
        }
        return allCustomers;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getId(), dto.getName(), dto.getTel(), dto.getSalary(), dto.getRole()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(), dto.getName(), dto.getTel(), dto.getSalary(), dto.getRole()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
    

}
