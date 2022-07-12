package com.example.tddstudy.domain.Membership;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void 맴버쉽_등록() throws Exception{
        //given
        final Membership membership = Membership.builder()
                .userId("id")
                .membershipName("네이버")
                .point(10000)
                .build();

        //when
        int resultCnt = membershipRepository.save(membership);

        //then
        assertThat(resultCnt).isEqualTo(1);

    }
}
