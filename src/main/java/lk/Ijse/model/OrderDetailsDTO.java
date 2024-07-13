package lk.Ijse.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDTO implements Serializable {
    private String orderId;
    private Date orderDate;
    private Time orderTime;
    private String tableType;
    private String waiter;
    private String CustomerID;
    private String ItemId;
    private double orderPrice;
    private int quantity;
    private double netTotal;
}
