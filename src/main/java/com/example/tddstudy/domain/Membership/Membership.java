package com.example.tddstudy.domain.Membership;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Membership {
    private Long id;
    private String membershipName;
    private String userId;
    private int point;

    @Builder
    public Membership(String membershipName, String userId, int point) {
        this.membershipName = membershipName;
        this.userId = userId;
        this.point = point;
    }
}
