package com.example.tddstudy.domain.Membership;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Member;

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
                .membershipName(MembershipType.NAVER)
                .point(10000)
                .build();

        //when
        int resultCnt = membershipRepository.save(membership);

        //then
        assertThat(resultCnt).isEqualTo(1);

    }

    @Test
    void 맴버쉽이_존재하는지_테스트() {
        //given
        final Membership membership = Membership.builder()
                .userId("id")
                .membershipName(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership findResult = membershipRepository.findByUserIdAndMembershipType("id", MembershipType.NAVER);

        assertThat(findResult).isNotNull();
        assertThat(findResult.getId()).isNotNull();
        assertThat(findResult.getUserId()).isEqualTo("id");
        assertThat(findResult.getMembershipName()).isEqualTo(MembershipType.NAVER);
        assertThat(findResult.getPoint()).isEqualTo(10000);

    }
}
