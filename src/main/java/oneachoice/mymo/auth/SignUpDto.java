package oneachoice.mymo.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {

    private String name;

    private String email;

    private String password;

}
