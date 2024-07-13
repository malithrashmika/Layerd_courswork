package lk.Ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ItemTm {
    private String code;
    private String itemName;
    private String itemDescription;
    private String category;
    private Double itemPrice;
    private int itemQtyOnHand;
}
