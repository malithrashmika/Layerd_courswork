package lk.Ijse.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements Serializable {
    String code;
    String name;
    String description;
    String category;
    double price;
    int QtyOnHand;
}
