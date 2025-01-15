package oneachoice.mymo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean create(String email, String name, String password) {

        if(readOneBy(email).isPresent()) throw new RuntimeException("이미 존재하는 사용자");

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setEmail(email);
        memberEntity.setName(name);
        memberEntity.setPassword(password);

        memberRepository.save(memberEntity);

        return true;
    }

    public Optional<MemberEntity> readOneBy(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<MemberEntity> readOneBy(Long id) {
        return memberRepository.findById(id);
    }
}
