package com.likelion.pbl_w10.member.service;

import com.likelion.pbl_w10.global.exception.DuplicateMemberException;
import com.likelion.pbl_w10.global.exception.MemberNotFoundException;
import com.likelion.pbl_w10.member.domain.Member;
import com.likelion.pbl_w10.member.domain.RoleType;
import com.likelion.pbl_w10.member.dto.*;
import com.likelion.pbl_w10.member.repository.MemberRepository;
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
            throw new DuplicateMemberException(dto.getName());
        }
        Member lion = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(),
                RoleType.LION, dto.getStudentId(), null);
        return memberRepository.save(lion);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest dto) {
        if (memberRepository.findByName(dto.getName()).isPresent()) {
            throw new DuplicateMemberException(dto.getName());
        }
        Member staff = new Member(dto.getName(), dto.getMajor(), dto.getPart(), dto.getGeneration(),
                RoleType.STAFF, null, dto.getPosition());
        return memberRepository.save(staff);
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.updateInfo(dto.getMajor(), dto.getPart(), dto.getGeneration());
        member.updateStudentId(dto.getStudentId());
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.updateInfo(dto.getMajor(), dto.getPart(), dto.getGeneration());
        member.updatePosition(dto.getPosition());
        return memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException(id);
        }
        memberRepository.deleteById(id);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findByPart(String part) {
        return memberRepository.findByPart(part);
    }
}
