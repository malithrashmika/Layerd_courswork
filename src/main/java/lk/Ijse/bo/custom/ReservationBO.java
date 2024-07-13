package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBO;
import lk.Ijse.dto.ReservationDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBO extends SuperBO {
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException;

    public boolean addReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existReservation(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException;

    public lk.Ijse.model.ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException;
    public List<String> getcusIds() throws SQLException, ClassNotFoundException;
    public List<String> getempIds() throws SQLException, ClassNotFoundException;
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException;
    public  String getCurrentId() throws SQLException, ClassNotFoundException;
}
