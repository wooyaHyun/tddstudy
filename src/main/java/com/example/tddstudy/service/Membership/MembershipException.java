package com.example.tddstudy.service.Membership;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException{

    private final MembershipErrorResult errorResult;
}
