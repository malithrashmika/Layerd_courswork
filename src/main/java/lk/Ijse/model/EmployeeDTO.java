package lk.Ijse.model;
import lombok.*;

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
