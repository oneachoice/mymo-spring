package oneachoice.mymo.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import oneachoice.mymo.common.BaseTimeEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
public class MemberEntity extends BaseTimeEntity {

    @Id
    @Column(name = "member_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", nullable = false, unique = true)
    private String email;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_password", nullable = false)
    private String password;
}
