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
public class IngredientTm {
    private String Ingredient_id;
    private String Ingredient_name;
    private String category;
    private int qty_avalible;
    private double unit_price;
    private String supplier_id;
}
