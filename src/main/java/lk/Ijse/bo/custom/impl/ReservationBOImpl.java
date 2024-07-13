package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.ReservationBO;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.EmployeeDAO;
import lk.Ijse.dao.custom.ReservationDAO;
import lk.Ijse.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.dao.custom.impl.ReservationDAOImpl;
import lk.Ijse.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = new ReservationDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAll();
    }

    @Override
    public boolean addReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.add(dto);
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(dto);
    }

    @Override
    public boolean existReservation(String id) throws SQLException, ClassNotFoundException {
        return reservationDAO.exist(id);
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(id);
    }

    @Override
    public lk.Ijse.model.ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException {
        return reservationDAO.search(id);
    }

    @Override
    public List<String> getcusIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIDS();
    }

    @Override
    public List<String> getempIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIDS();
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentId();
    }

}
