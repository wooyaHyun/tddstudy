package com.example.tddstudy.web.Membership;

import com.example.tddstudy.web.dto.MembershipRequestDto;
import com.example.tddstudy.web.dto.MembershipResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MembershipController {

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipResponseDto> addMembership(
            @RequestHeader("X-USER-ID") final String userId,
            @RequestBody final MembershipRequestDto membershipRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
