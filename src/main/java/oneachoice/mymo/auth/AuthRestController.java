package oneachoice.mymo.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(SignInDto signInDto) {
        if(authService.signIn(signInDto)) {
            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(SignUpDto signUpDto) {
        if(authService.signUp(signUpDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("회원가입 실패");
    }
}
