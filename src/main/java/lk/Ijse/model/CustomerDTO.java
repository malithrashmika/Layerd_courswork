package lk.Ijse.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements Serializable {
   String id;
   String name;
   String tel;
   String email;
}
