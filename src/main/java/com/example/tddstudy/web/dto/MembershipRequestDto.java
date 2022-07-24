package com.example.tddstudy.web.dto;

import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequestDto {

    @Min(0)
    private final int point;

    @NotNull
    private final MembershipType membershipType;

}
