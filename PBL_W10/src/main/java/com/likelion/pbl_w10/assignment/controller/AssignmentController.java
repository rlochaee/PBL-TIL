package com.likelion.pbl_w10.assignment.controller;

import com.likelion.pbl_w10.assignment.domain.Assignment;
import com.likelion.pbl_w10.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w10.assignment.dto.AssignmentResponse;
import com.likelion.pbl_w10.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w10.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest dto) {
        Assignment result = assignmentService.create(memberId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(result));
    }

    // 전체 과제 조회 - /assignments/search 와 겹치지 않도록 먼저 선언
    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAll() {
        List<AssignmentResponse> responses = assignmentService.findAll().stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // 과제 제목 검색 - /{id} 보다 먼저 선언해야 "search" 문자열과 혼동 없음
    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchByTitle(@RequestParam String keyword) {
        List<AssignmentResponse> responses = assignmentService.searchByTitle(keyword).stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.findById(id)));
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMemberId(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId).stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest dto) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.update(id, dto)));
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
