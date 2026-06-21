package com.likelion.pbl_w10.assignment.service;

import com.likelion.pbl_w10.assignment.domain.Assignment;
import com.likelion.pbl_w10.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w10.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w10.assignment.repository.AssignmentRepository;
import com.likelion.pbl_w10.global.exception.AssignmentNotFoundException;
import com.likelion.pbl_w10.global.exception.MemberNotFoundException;
import com.likelion.pbl_w10.member.domain.Member;
import com.likelion.pbl_w10.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Assignment create(Long memberId, AssignmentCreateRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        Assignment assignment = new Assignment(dto.getTitle(), dto.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
    }

    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public Assignment update(Long id, AssignmentUpdateRequest dto) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
        assignment.updateInfo(dto.getTitle(), dto.getDescription());
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new AssignmentNotFoundException(id);
        }
        assignmentRepository.deleteById(id);
    }
}
