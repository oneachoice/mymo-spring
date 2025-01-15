package oneachoice.mymo.memo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MemoPageDto {

    private List<MemoItemDto> content;

    private Integer totalPages;

    private Long totalElements;

    private Integer numberOfElements;

    private Integer number;

    private Integer size;

}
