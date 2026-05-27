package com.lion.PBL_W8.service;

import com.lion.PBL_W8.domain.Member;
import com.lion.PBL_W8.domain.RoleType;
import com.lion.PBL_W8.dto.*;
import com.lion.PBL_W8.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 작업 최적화
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Lion 생성
     */
    @Transactional
    public Member createLion(LionCreateRequest dto) {
        // 이름 중복 검증 (Optional 처리)
        if (memberRepository.findByName(dto.getName()).isPresent()) {
            return null;
        }

        // 단일 Member 객체 생성 (Lion 전용 studentId 채우고, position은 null)
        Member lion = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(), RoleType.LION, dto.getStudentId(), null);

        // save()의 반환값(id가 매핑된 객체)을 그대로 리턴
        return memberRepository.save(lion);
    }

    /**
     * Staff 생성
     */
    @Transactional
    public Member createStaff(StaffCreateRequest dto) {
        if (memberRepository.findByName(dto.getName()).isPresent()) {
            return null;
        }

        // 단일 Member 객체 생성 (Staff 전용 position 채우고, studentId는 null)
        Member staff = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(), RoleType.STAFF, null, dto.getPosition());

        return memberRepository.save(staff);
    }

    /**
     * Lion 수정 (String name -> Long id 변경)
     */
    @Transactional
    public Member updateLion(Long id, LionUpdateRequest dto) {
        // repository.findById(id) 사용
        Member oldLion = memberRepository.findById(id).orElse(null);
        if (oldLion == null) {
            return null;
        }

        // 엔티티 내부 메서드로 필드 변경
        oldLion.updateInfo(dto.getMajor(), dto.getPart(), dto.getGeneration());
        oldLion.updateStudentId(dto.getStudentId());

        // repository.save(member) 사용
        return memberRepository.save(oldLion);
    }

    /**
     * Staff 수정 (String name -> Long id 변경)
     */
    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest dto) {
        Member oldStaff = memberRepository.findById(id).orElse(null);
        if (oldStaff == null) {
            return null;
        }

        oldStaff.updateInfo(dto.getMajor(), dto.getPart(), dto.getGeneration());
        oldStaff.updatePosition(dto.getPosition());

        return memberRepository.save(oldStaff);
    }

    /**
     * 멤버 삭제 (String name -> Long id 변경 및 deleteById 사용)
     */
    @Transactional
    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }

    /**
     * [NEW 요구사항] findById 단건 조회 추가
     */
    public Member findById(Long id) {
        // repository.findById(id).orElse(null) 사용
        return memberRepository.findById(id).orElse(null);
    }

    /**
     * 전체 조회 (8주차 API 연동용 추가)
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}