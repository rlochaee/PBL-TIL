package com.lion.PBL_W7.service;

import com.lion.PBL_W7.domain.role.Lion;
import com.lion.PBL_W7.domain.role.Role;
import com.lion.PBL_W7.domain.role.Staff;
import com.lion.PBL_W7.dto.*;
import com.lion.PBL_W7.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Role createLion(LionCreateRequest dto) {
        if (memberRepository.existsByName(dto.getName())) {
            return null;
        }
        Lion lion = new Lion(dto.getName(), dto.getMajor(), dto.getGeneration(), dto.getPart(), dto.getStudentId());
        memberRepository.save(lion);
        return lion;
    }

    public Role createStaff(StaffCreateRequest dto) {
        if (memberRepository.existsByName(dto.getName())) {
            return null;
        }
        Staff staff = new Staff(dto.getName(), dto.getMajor(), dto.getGeneration(), dto.getPart(), dto.getPosition());
        memberRepository.save(staff);
        return staff;
    }

    public Role updateLion(String name, LionUpdateRequest dto) {
        Lion oldLion = (Lion) memberRepository.findByName(name);
        if (oldLion == null) {
            return null;
        }

        Lion updatedLion = new Lion(name, dto.getMajor(), dto.getGeneration(), dto.getPart(), dto.getStudentId());
        memberRepository.updateByName(name, updatedLion);
        return updatedLion;
    }

    public Role updateStaff(String name, StaffUpdateRequest dto) {
        Staff oldStaff = (Staff) memberRepository.findByName(name);
        if (oldStaff == null) {
            return null;
        }

        Staff updatedStaff = new Staff(name, dto.getMajor(), dto.getGeneration(), dto.getPart(), dto.getPosition());

        memberRepository.updateByName(name, updatedStaff);
        return updatedStaff;
    }

    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }

    public Role getMemberByName(String name) {
        return memberRepository.findByName(name);
    }
}