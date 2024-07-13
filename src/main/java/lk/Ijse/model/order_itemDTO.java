package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class order_itemDTO implements Serializable {

    private String ItemID;
    private String orderid;
    private int Qty;
    private double unitprice;
    private double totalprice;


}
