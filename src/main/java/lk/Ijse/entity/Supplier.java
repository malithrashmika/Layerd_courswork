package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier  {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactEmail;
}
