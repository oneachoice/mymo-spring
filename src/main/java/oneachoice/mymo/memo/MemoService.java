package oneachoice.mymo.memo;

import lombok.RequiredArgsConstructor;
import oneachoice.mymo.common.SessionManager;
import oneachoice.mymo.member.MemberEntity;
import oneachoice.mymo.member.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    private final MemberService memberService;

    /**
     * 메모 생성
     */
    public boolean create(MemoDto memoDto) {

        // 세션에서 회원 아이디 추출
        Long memberId = (Long) SessionManager.getSessionAttribute("memberId");

        Optional<MemberEntity> optionalMemberEntity = memberService.readOneBy(memberId);

        // 존재하지 않는 사용자
        if (optionalMemberEntity.isEmpty()) {
            return false;
        }

        MemberEntity memberEntity = optionalMemberEntity.get();

        // 메모 엔티티 생성
        MemoEntity memoEntity = new MemoEntity();

        memoEntity.setMemberEntity(memberEntity);
        memoEntity.setTitle(memoDto.getTitle());
        memoEntity.setContent(memoDto.getContent());

        memoRepository.save(memoEntity);

        return true;
    }

    /**
     * 메모 수정
     */
    public boolean update(MemoDto memoDto) {

        // 메모 가져오기
        MemoEntity memoEntity = memoRepository.findById(memoDto.getId()).orElseThrow(() -> new RuntimeException("존재하지 않는 메모"));

        // 소유권자인지 확인
        Long memberId = (Long) SessionManager.getSessionAttribute("memberId");
        if (!memoEntity.getMemberEntity().getId().equals(memberId)) throw new RuntimeException("수정 권한 없음");

        // 메모 수정
        memoEntity.setTitle(memoDto.getTitle());
        memoEntity.setContent(memoDto.getContent());

        return true;
    }

    /**
     * 메모 가져오기, 페이지네이션
     */
    public MemoPageDto getMemoPage(Pageable pageable) {

        // 세션에서 멤버 아이디 추출
        Long memberId = (Long) SessionManager.getSessionAttribute("memberId");

        // 메모 엔티티를 MemoItemDTO로 변환 후 반환
        Page<MemoItemDto> memoItemDtoPage = memoRepository.findByMemberId(memberId, pageable).map((memoEntity) -> {

            MemoItemDto memoItemDto = new MemoItemDto();

            memoItemDto.setId(memoEntity.getId());
            memoItemDto.setTitle(memoEntity.getTitle());
            memoItemDto.setContent(memoEntity.getContent());
            memoItemDto.setCreatedAt(memoEntity.getCreatedAt());
            memoItemDto.setUpdatedAt(memoEntity.getUpdatedAt());

            return memoItemDto;
        });

        // MemoPageDto 생성
        MemoPageDto memoPageDto = new MemoPageDto();
        memoPageDto.setContent(memoItemDtoPage.getContent());
        memoPageDto.setTotalPages(memoItemDtoPage.getTotalPages());
        memoPageDto.setTotalElements(memoItemDtoPage.getTotalElements());
        memoPageDto.setNumberOfElements(memoItemDtoPage.getNumberOfElements());
        memoPageDto.setNumber(memoItemDtoPage.getNumber());
        memoPageDto.setSize(memoItemDtoPage.getSize());

        return memoPageDto;
    }


    public MemoItemDto getMemoBy(Long memoId) {

        MemoEntity memoEntity = memoRepository.findById(memoId).orElseThrow(() -> new RuntimeException("메모를 찾을 수 없음"));


        // 소유권자인지 확인
        Long memberId = (Long) SessionManager.getSessionAttribute("memberId");
        if (!memoEntity.getMemberEntity().getId().equals(memberId)) throw new RuntimeException("삭제 권한 없음");

        // MemoItemDto로 변환
        MemoItemDto memoItemDto = new MemoItemDto();

        memoItemDto.setId(memoEntity.getId());
        memoItemDto.setTitle(memoEntity.getTitle());
        memoItemDto.setContent(memoEntity.getContent());
        memoItemDto.setCreatedAt(memoEntity.getCreatedAt());
        memoItemDto.setUpdatedAt(memoEntity.getUpdatedAt());

        return memoItemDto;
    }

    /**
     * 메모 삭제
     *
     * @param memoId
     * @return true: 성공, false, 실패
     */
    public boolean removeBy(Long memoId) {

        Optional<MemoEntity> optionalMemoEntity = memoRepository.findById(memoId);

        if (optionalMemoEntity.isEmpty()) return false;

        MemoEntity memoEntity = optionalMemoEntity.get();

        // 해당 메모의 소유자인지 확인
        Long memberId = (Long) SessionManager.getSessionAttribute("memberId");
        if (!memoEntity.getMemberEntity().getId().equals(memberId)) return false;

        memoRepository.delete(memoEntity);

        return true;
    }
}
