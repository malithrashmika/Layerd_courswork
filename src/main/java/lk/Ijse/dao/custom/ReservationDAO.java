package lk.Ijse.dao.custom;

import lk.Ijse.dao.CrudDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation> {
    public String getCurrentId() throws SQLException, ClassNotFoundException;
}
