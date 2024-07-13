package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.EmployeeDAO;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.Employee;
import lk.Ijse.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(" SELECT * FROM employee;");
        while (rst.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(rst.getString("id"),rst.getString("name"),rst.getString("Tel"),rst.getString("salary"),rst.getString("Role"));
            allEmployees.add(employeeDTO);
        }
        return allEmployees;
    }

    @Override
    public boolean add(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (id,name, Tel, salary, Role) VALUES (?,?,?,?,?)", dto.getId(), dto.getName(), dto.getTel(), dto.getSalary(), dto.getRole());
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name=?, Tel=?, salary=?, Role=? WHERE id=?", dto.getName(), dto.getTel(), dto.getSalary(), dto.getRole(), dto.getId());
    }



    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newEmployeeId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE id=?", id);
    }

    @Override
    public ReservationDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE id=?", id + "");
        rst.next();
        return new Employee(id + "", rst.getString("name"), rst.getString("Tel"), rst.getString("salary"), rst.getString("Role"));
    }

}
