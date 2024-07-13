package lk.Ijse.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientDTO implements Serializable {
    String Ingredient_id;
    String Ingredient_name;
    String category;
    int qty_avalible;
    double unit_price;
    String supplier_id;
}
