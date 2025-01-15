package oneachoice.mymo.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInDto {

    private String email;

    private String password;
}
