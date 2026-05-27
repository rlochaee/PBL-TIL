package com.lion.PBL_W8.controller;

import com.lion.PBL_W8.domain.Member;
import com.lion.PBL_W8.dto.*;
import com.lion.PBL_W8.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Lion 등록
     */
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest dto) {
        Member result = memberService.createLion(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // MemberResponse 단일 타입으로 변환하여 리턴
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(result));
    }

    /**
     * Staff 등록
     */
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest dto) {
        Member result = memberService.createStaff(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(result));
    }

    /**
     * 💡 지침 반영: 단건 조회 경로 name -> id 변경 및 분기 처리 제거
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // instanceof 체크나 형변환 없이 깔끔하게 처리 가능
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    /**
     * 💡 지침 반영: Lion 수정 경로 name -> id 변경
     */
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest dto) {
        Member updated = memberService.updateLion(id, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    /**
     * 💡 지침 반영: Staff 수정 경로 name -> id 변경
     */
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest dto) {
        Member updated = memberService.updateStaff(id, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    /**
     * 💡 지침 반영: 삭제 경로 name -> id 변경
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean isDeleted = memberService.deleteMember(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * [추가] 전체 멤버 조회 (전체 메인 리스트 뷰용)
     */
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> responses = memberService.findAll().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}