package lk.Ijse.tm;

import java.sql.Date;
import java.sql.Time;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class OrderTm  {
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
