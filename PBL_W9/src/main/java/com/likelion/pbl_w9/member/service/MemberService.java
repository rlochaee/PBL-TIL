package com.likelion.pbl_w9.member.service;

import com.likelion.pbl_w9.member.domain.Member;
import com.likelion.pbl_w9.member.domain.RoleType;
import com.likelion.pbl_w9.member.dto.*;
import com.likelion.pbl_w9.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createLion(LionCreateRequest dto) {
        if (memberRepository.findByName(dto.getName()).isPresent()) {
            return null;
        }
        Member lion = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(), RoleType.LION, dto.getStudentId(), null);
        return memberRepository.save(lion);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest dto) {
        if (memberRepository.findByName(dto.getName()).isPresent()) {
            return null;
        }
        Member staff = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(), RoleType.STAFF, null, dto.getPosition());
        return memberRepository.save(staff);
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest dto) {
        Member oldLion = memberRepository.findById(id).orElse(null);
        if (oldLion == null) {
            return null;
        }
        oldLion.updateInfo(dto.getMajor(), dto.getPart(), dto.getGeneration());
        oldLion.updateStudentId(dto.getStudentId());
        return memberRepository.save(oldLion);
    }

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

    @Transactional
    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
