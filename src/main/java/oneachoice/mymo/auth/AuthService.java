package oneachoice.mymo.auth;

import lombok.RequiredArgsConstructor;
import oneachoice.mymo.common.SessionManager;
import oneachoice.mymo.member.MemberEntity;
import oneachoice.mymo.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;


    public boolean signUp(SignUpDto signUpDto) {
        // !!!!!!!!!!!!!!!!!!
        // 패스워드 암호화 해야함
        // !!!!!!!!!!!!!!!!!!
        return memberService.create(signUpDto.getEmail(), signUpDto.getName(), signUpDto.getPassword());
    }


    public boolean signIn(SignInDto signInDto) {

        // 유저 찾기
        Optional<MemberEntity> optionalUserEntity = memberService.readOneBy(signInDto.getEmail());

        MemberEntity memberEntity = optionalUserEntity.orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));

        // 비밀번호 비교
        if (!memberEntity.getPassword().equals(signInDto.getPassword())) return false;

        // 세션에 키, 값 저장
        SessionManager.setSessionAttribute("memberId", memberEntity.getId());

        return true;
    }
}
