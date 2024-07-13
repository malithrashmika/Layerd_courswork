package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.ReservationDAO;
import lk.Ijse.dto.ReservationDTO;
import lk.Ijse.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class  ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> allReservation = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation");
        while (rst.next()) {
            ReservationDTO reservationDTO = new ReservationDTO(rst.getString("reservation_id"),rst.getDate("date_of_reservation"),rst.getDate("reserved_date"),rst.getTime("reserved_time"),rst.getString("employee_id"),rst.getString("table_number"),rst.getString("customer_id"),rst.getString("start_time"),rst.getString("end_time"),rst.getString("event"));
            allReservation.add(reservationDTO);
        }
        return allReservation;
    }

    @Override
    public boolean add(Reservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Reservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE reservation SET  date_of_reservation= ?, reserved_date = ?, reserved_time =?,employee_id=?,table_number=?,customer_id=? ,start_time=?,end_time=?,event=?WHERE  reservation_id= ?   ",dto.getDate_of_reservation(),dto.getReserved_date(),dto.getReserved_time(),dto.getEmployee_id(),dto.getTable_Number(),dto.getCustomer_id(),dto.getStart_time(),dto.getEnd_time(),dto.getEvent(),dto.getReservationId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT reservation_id  FROM reservation WHERE reservation_id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM reservation ORDER BY reservation_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("reservation_id");
            int newItemId = Integer.parseInt(id.replace("RE00-", "")) + 1;
            return String.format("RE00-%03d", newItemId);
        } else {
            return "RE00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM reservation WHERE reservation_id=?", id);
    }

    @Override
    public ReservationDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation WHERE reservation_id=?", id + "");
        rst.next();
        return new ReservationDTO(id + "",rst.getDate("date_of_reservation"),rst.getDate("reserved_date"),rst.getTime("reserved_time"),rst.getString("employee_id"),rst.getString("table_number"),rst.getString("customer_id"),rst.getString("start_time"),rst.getString("end_time"),rst.getString("event"));
    }
    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
     ResultSet rst = SQLUtil.execute("SELECT reservation_id FROM reservation ORDER BY reservation_id DESC LIMIT 1");

        if(rst.next()) {
            String RId = rst.getString(1);
            return RId;
        }
        return null;
    }

}
