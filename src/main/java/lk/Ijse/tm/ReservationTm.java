package lk.Ijse.tm;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class ReservationTm {
    private String reservationId;
    private Date date_of_reservation;
    private Date reserved_date;
    private Time reserved_time;
    private String employee_id;
    private String table_Number;
    private String Customer_id;
    private String start_time;
    private String end_time;
    private String Event;
}
