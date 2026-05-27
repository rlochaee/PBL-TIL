package com.lion.PBL_W8.dto;

import com.lion.PBL_W8.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    // 8주차 요구사항에 맞게 id 추가 및 Lion/Staff 필드 통합
    private final Long id;
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String roleName;  // "아기사자" 또는 "운영진"이 저장됨
    private final String studentId; // Staff일 때 null로 내려감
    private final String position;  // Lion일 때 null로 내려감

    // 통합 생성자
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

    // 💡 단일 Member 엔티티를 받아 통합 응답 DTO로 변환하는 팩토리 메서드
    public static MemberResponse from(Member member) {
        if (member == null) return null;

        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor(),
                member.getGeneration(),
                member.getPart(),
                member.getRoleType().getDisplayName(), // 아까 만든 RoleType Enum에서 한글명("아기사자"/"운영진")을 동적으로 쏙 꺼내옵니다.
                member.getStudentId(),
                member.getPosition()
        );
    }
}