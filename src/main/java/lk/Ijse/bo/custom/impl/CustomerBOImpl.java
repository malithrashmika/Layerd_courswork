package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.CustomerBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.entity.Customer;
import lk.Ijse.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getName(),c.getTel(),c.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getId(),dto.getName(),dto.getTel(),dto.getEmail()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getTel(),dto.getEmail()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.exist(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.delete(id);
    }


}
