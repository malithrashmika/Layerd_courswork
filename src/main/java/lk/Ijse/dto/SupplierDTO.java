package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
