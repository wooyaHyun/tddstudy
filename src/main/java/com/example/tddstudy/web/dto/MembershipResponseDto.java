package com.example.tddstudy.web.dto;

import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipResponseDto {
    private final Long id;
    private final MembershipType membershipType;
}
