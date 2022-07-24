package com.example.tddstudy.web.dto;

import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName : com.example.tddstudy.web.dto
 * fileName : MemebershipDetailResponseDto
 * author : SHW
 * date : 2022-07-25
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022-07-25   SHW     최초 생성
 */
@Getter
@Builder
@RequiredArgsConstructor
public class MemebershipDetailResponseDto {
    private final Long id;
    private final String userId;
    private final MembershipType membershipType;
    private final int point;


}
