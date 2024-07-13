package lk.Ijse.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;
}
