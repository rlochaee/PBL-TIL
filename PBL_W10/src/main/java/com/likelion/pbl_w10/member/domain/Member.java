package com.likelion.pbl_w10.member.domain;

import com.likelion.pbl_w10.assignment.domain.Assignment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String part;
    private int generation;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String studentId;
    private String position;

    @OneToMany(mappedBy = "member")
    private List<Assignment> assignments = new ArrayList<>();

    public Member(String name, String major, String part, int generation, RoleType roleType, String studentId, String position) {
        this.name = name;
        this.major = major;
        this.part = part;
        this.generation = generation;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public void updateInfo(String major, String part, int generation) {
        this.major = major;
        this.part = part;
        this.generation = generation;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updatePosition(String position) {
        this.position = position;
    }
}

