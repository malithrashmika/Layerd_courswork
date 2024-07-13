package lk.Ijse.tm;


import lk.Ijse.bo.custom.impl.EmployeeBOImpl;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EmployeeTm extends EmployeeBOImpl implements Comparable<CustomerTm>{
    private String id;
    private String name;
    private String tel;
    private String salary;
    private String Role;

    @Override
    public int compareTo(CustomerTm o) {
        return id.compareTo(o.getId());
    }
}
