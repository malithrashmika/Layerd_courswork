package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order  {
    private String orderId;
    private Date orderDate;
    private Time time;
    private String Table;
    private String customerId;
    private String employeeId;
    private double NetTotal;


}
