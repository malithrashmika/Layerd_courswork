package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDTO implements Serializable {
    String id;
    String name;
    String Tel;
    String salary;
    String Role;
}
