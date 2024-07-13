package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    String code;
    String name;
    String description;
    String category;
    double price;
    int QtyOnHand;
}
