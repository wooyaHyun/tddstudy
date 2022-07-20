package com.example.tddstudy.web.dto;

import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequestDto {

    private final int point;
    private final MembershipType membershipType;



}
