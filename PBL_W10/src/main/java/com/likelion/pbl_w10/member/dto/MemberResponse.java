package com.likelion.pbl_w10.member.dto;

import com.likelion.pbl_w10.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String roleName;
    private final String studentId;
    private final String position;

    public MemberResponse(Long id, String name, String major, int generation, String part, String roleName, String studentId, String position) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.studentId = studentId;
        this.position = position;
    }

    public static MemberResponse from(Member member) {
        if (member == null) return null;
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor(),
                member.getGeneration(),
                member.getPart(),
                member.getRoleType().getDisplayName(),
                member.getStudentId(),
                member.getPosition()
        );
    }
}

