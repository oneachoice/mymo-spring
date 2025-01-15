package oneachoice.mymo.memo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import oneachoice.mymo.common.BaseTimeEntity;
import oneachoice.mymo.member.MemberEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "memo")
@Data
@NoArgsConstructor
public class MemoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "memo_title", nullable = false)
    private String title;

    @Column(name = "memo_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;
}
