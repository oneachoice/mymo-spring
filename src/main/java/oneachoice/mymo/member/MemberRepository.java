package oneachoice.mymo.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public Optional<MemberEntity> findByEmail(String email);
}
