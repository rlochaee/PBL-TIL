package com.lion.PBL_W7.controller;

import com.lion.PBL_W7.domain.role.Lion;
import com.lion.PBL_W7.domain.role.Role;
import com.lion.PBL_W7.domain.role.Staff;
import com.lion.PBL_W7.dto.*;
import com.lion.PBL_W7.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<?> createLion(@RequestBody LionCreateRequest dto) {
        Role result = memberService.createLion(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LionResponse.from((Lion) result));
    }

    @PostMapping("/staffs")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateRequest dto) {
        Role result = memberService.createStaff(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StaffResponse.from((Staff) result));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Role member = memberService.getMemberByName(name);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<?> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest dto) {
        Role updated = memberService.updateLion(name, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(LionResponse.from((Lion) updated));
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<?> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest dto) {
        Role updated = memberService.updateStaff(name, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(StaffResponse.from((Staff) updated));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        boolean isDeleted = memberService.deleteMember(name);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}