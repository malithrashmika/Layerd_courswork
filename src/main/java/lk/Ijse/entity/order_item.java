package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class order_item  {

    private String ItemID;
    private String orderid;
    private int Qty;
    private double unitprice;
    private double totalprice;


}
