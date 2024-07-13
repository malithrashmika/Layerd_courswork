package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient  {
    String Ingredient_id;
    String Ingredient_name;
    String category;
    int qty_avalible;
    double unit_price;
    String supplier_id;
}
