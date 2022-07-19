package com.example.tddstudy.web.dto;

import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembershipResponseDto {
    private Long id;
    private MembershipType membershipName;
    private String userId;
    private int point;

    @Builder
    public MembershipResponseDto(MembershipType membershipName, String userId, int point) {
        this.membershipName = membershipName;
        this.userId = userId;
        this.point = point;
    }
}
