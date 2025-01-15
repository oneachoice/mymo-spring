package oneachoice.mymo.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

    @Query("""
            SELECT m
            FROM MemoEntity m
            WHERE m.memberEntity.id = :memberId
            """)
    public Page<MemoEntity> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
