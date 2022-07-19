package com.example.tddstudy.domain.Membership;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/*
 *참고한 "망나니 개발자" 블로그에서는 JPA를 통한 entity에 given 값을 주었다.
 * 하지만 곧 있을 회사 프로젝트에서 mybatis를 사용할 예정이기에 map을 사용할 예정이다.
 */

// @SpringBootTest는 무겁지만 왠만한 테스트에 필요한 어노테이션을 포함하고 있기에 @SpringBootTest 어노테이션 사용
// @ExtendWith(SpringExtension.class)을 사용할려고 하였으나 bean 등록이 되지 않아 일단은 @SpringBootTest 어노테이션 사용
@SpringBootTest
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void 맴버쉽_등록() throws Exception{
        //given
        final Map<String, Object> membership = new HashMap<>();
        membership.put("userId", "id");
        membership.put("membershipName", MembershipType.NAVER);
        membership.put("point", 10000);

        //when
        int resultCnt = membershipRepository.save(membership);

        //then
        assertThat(resultCnt).isEqualTo(1);

    }

    @Test
    void 맴버쉽이_존재하는지_테스트() {
        //given
        final Map<String, Object> membership = new HashMap<>();
        membership.put("userId", "id");
        membership.put("membershipName", MembershipType.NAVER);
        membership.put("point", 10000);

        final Map<String, Object> findResult = membershipRepository.findByUserIdAndMembershipType("id", MembershipType.NAVER);
        System.out.println(findResult);
        assertThat(findResult).isNotNull();
        assertThat(findResult.get("ID")).isNotNull();
        assertThat(findResult.get("USER_ID")).isEqualTo("id");
        assertThat(findResult.get("MEMBERSHIP_NAME")).isEqualTo(String.valueOf(MembershipType.NAVER));
        assertThat(findResult.get("POINT")).isEqualTo(10000);

    }
}
