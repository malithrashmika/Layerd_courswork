package lk.Ijse.model;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO implements Serializable {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactEmail;
}
