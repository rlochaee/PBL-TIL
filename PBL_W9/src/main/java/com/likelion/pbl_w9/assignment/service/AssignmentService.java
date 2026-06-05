package com.likelion.pbl_w9.assignment.service;

import com.likelion.pbl_w9.assignment.domain.Assignment;
import com.likelion.pbl_w9.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w9.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w9.assignment.repository.AssignmentRepository;
import com.likelion.pbl_w9.member.domain.Member;
import com.likelion.pbl_w9.member.repository.MemberRepository;
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
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return null;
        }
        Assignment assignment = new Assignment(dto.getTitle(), dto.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Assignment update(Long id, AssignmentUpdateRequest dto) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) {
            return null;
        }
        assignment.updateInfo(dto.getTitle(), dto.getDescription());
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public boolean delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            return false;
        }
        assignmentRepository.deleteById(id);
        return true;
    }
}
