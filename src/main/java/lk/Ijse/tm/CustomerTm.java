package lk.Ijse.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerTm implements Comparable<CustomerTm> {
    private String id;
    private String name;
    private String tel;
    private String email;

    @Override
    public int compareTo(CustomerTm o) {
        return id.compareTo(o.getId());
    }
}
