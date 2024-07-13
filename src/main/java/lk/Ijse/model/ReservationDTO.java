package lk.Ijse.model;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO implements Serializable {
    String reservationId;
    Date date_of_reservation;
    Date reserved_date;
    Time reserved_time;
    String employee_id;
    String table_Number;
    String Customer_id;
    String Start_time;
    String End_time;
    String Event;

}
