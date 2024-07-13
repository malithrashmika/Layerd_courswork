package lk.Ijse.model;
import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO implements Serializable {
     String code;
     String description;
     double unitPrice;
     int qty;
     double total;
     JFXButton btnRemove;
}
