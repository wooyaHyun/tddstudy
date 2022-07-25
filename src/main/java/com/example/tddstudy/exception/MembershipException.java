package com.example.tddstudy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException{

    private final MembershipErrorResult errorResult;
}
