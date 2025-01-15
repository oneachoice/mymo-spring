package oneachoice.mymo.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memo")
public class MemoRestController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<String> write(MemoDto memoDto) {
        if (memoService.create(memoDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("메모 저장 완료");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("메모 저장 실패");
    }

    @GetMapping
    public ResponseEntity<MemoPageDto> read(@PageableDefault(size = 20, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(memoService.getMemoItems(pageable));
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<MemoItemDto> readOne(@PathVariable("memoId") Long memoId) {
        return ResponseEntity.ok(memoService.getMemoBy(memoId));
    }

    @DeleteMapping("/{memoId}")
    public ResponseEntity<String> remove(@PathVariable("memoId") Long memoId) {
        if (memoService.removeBy(memoId)) {
            return ResponseEntity.ok("메모 삭제 완료");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("메모 삭제 실패");
    }

}
