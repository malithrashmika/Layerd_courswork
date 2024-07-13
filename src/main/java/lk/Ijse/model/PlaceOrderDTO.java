package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO implements Serializable {
    private OrderDTO orderDTO;
    private List<order_itemDTO> odList;
}
