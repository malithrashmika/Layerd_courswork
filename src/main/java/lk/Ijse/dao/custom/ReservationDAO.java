package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation> {
    @Override
    ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException;

    @Override
    boolean update(Reservation dto) throws SQLException, ClassNotFoundException;

    default boolean add(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", dto.getReservationId(),dto.getDate_of_reservation(),dto.getReserved_date(),dto.getReserved_time(),dto.getEmployee_id(),dto.getTable_Number(),dto.getCustomer_id(),dto.getStart_time(),dto.getEnd_time(),dto.getEvent());
    }

    boolean update(ReservationDTO dto) throws SQLException, ClassNotFoundException;

    @Override
    boolean exist(String id) throws SQLException, ClassNotFoundException;

    @Override
    String generateNewID() throws SQLException, ClassNotFoundException;

    @Override
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    @Override
    ReservationDTO search(String id) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;
}
