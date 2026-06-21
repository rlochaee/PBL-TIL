package com.likelion.pbl_w10.member.controller;

import com.likelion.pbl_w10.member.domain.Member;
import com.likelion.pbl_w10.member.dto.*;
import com.likelion.pbl_w10.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest dto) {
        Member result = memberService.createLion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(result));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest dto) {
        Member result = memberService.createStaff(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(result));
    }

    // part 파라미터가 있으면 필터링, 없으면 전체 조회
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(
            @RequestParam(required = false) String part) {
        List<Member> members = (part != null && !part.isBlank())
                ? memberService.findByPart(part)
                : memberService.findAll();
        List<MemberResponse> responses = members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(MemberResponse.from(memberService.findById(id)));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(
            @PathVariable Long id, @RequestBody LionUpdateRequest dto) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateLion(id, dto)));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(
            @PathVariable Long id, @RequestBody StaffUpdateRequest dto) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateStaff(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
