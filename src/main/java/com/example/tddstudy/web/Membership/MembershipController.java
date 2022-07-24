package com.example.tddstudy.web.Membership;

import com.example.tddstudy.service.Membership.MembershipService;
import com.example.tddstudy.web.dto.MembershipRequestDto;
import com.example.tddstudy.web.dto.MembershipResponseDto;
import com.example.tddstudy.web.dto.MemebershipDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/api/v1/memberships")
    public int addMembership(
            @RequestHeader("X-USER-ID") final String userId,
            @RequestBody @Valid final MembershipRequestDto membershipRequestDto){


        return membershipService.addMembership(userId, membershipRequestDto.getMembershipType(), membershipRequestDto.getPoint());

    }

    @GetMapping("/api/v1/memberships")
    public ResponseEntity<List<MemebershipDetailResponseDto>> getMembershipList(
            @RequestHeader("X-USER-ID") final String userId) {

        return ResponseEntity.ok(membershipService.getMembershipList(userId));
    }
}
