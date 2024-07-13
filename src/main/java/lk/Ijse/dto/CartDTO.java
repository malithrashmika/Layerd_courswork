package lk.Ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
