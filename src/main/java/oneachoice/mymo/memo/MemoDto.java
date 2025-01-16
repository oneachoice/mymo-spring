package oneachoice.mymo.memo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemoDto {

    private Long id;

    private String title;

    private String content;
}
