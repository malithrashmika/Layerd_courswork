package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable {
    private String orderId;
    private Date orderDate;
    private Time time;
    private String Table;
    private String customerId;
    private String employeeId;
    private double NetTotal;

    List<OrderDetailsDTO> orderDetails;
    public List<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }
}
