package com.example.tddstudy.domain.Membership;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void MembershipRepository가Null이아님(){
        assertThat(membershipRepository).isNotNull();
    }

}
