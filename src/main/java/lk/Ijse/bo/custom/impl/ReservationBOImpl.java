package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.ReservationBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.EmployeeDAO;
import lk.Ijse.dao.custom.ReservationDAO;
import lk.Ijse.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.dao.custom.impl.ReservationDAOImpl;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO  = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Employee);
    @Override
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> allEntityData = reservationDAO.getAll();
        ArrayList<ReservationDTO> allDTOData= new ArrayList<>();
        for (Reservation dto : allEntityData) {
            allDTOData.add(new ReservationDTO(dto.getReservationId(),dto.getDate_of_reservation(),dto.getReserved_date(),dto.getReserved_time(),dto.getEmployee_id(),dto.getTable_Number(),dto.getCustomer_id(),dto.getStart_time(),dto.getEnd_time(),dto.getEvent()));
        }
        return allDTOData;
    }

    @Override
    public boolean addReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.add(new Reservation(dto.getReservationId(),dto.getDate_of_reservation(),dto.getReserved_date(),dto.getReserved_time(),dto.getEmployee_id(),dto.getTable_Number(),dto.getCustomer_id(),dto.getStart_time(),dto.getEnd_time(),dto.getEvent()));
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(new Reservation(dto.getReservationId(),dto.getDate_of_reservation(),dto.getReserved_date(),dto.getReserved_time(),dto.getEmployee_id(),dto.getTable_Number(),dto.getCustomer_id(),dto.getStart_time(),dto.getEnd_time(),dto.getEvent()));
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
    public List<String> getcusIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIDS();
    }

    @Override
    public List<String> getempIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
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
