package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * packageName : com.example.tddstudy.service.Membership
 * fileName : MembershipServiceTest
 * author : SHW
 * date : 2022-07-15
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022-07-15   SHW     최초 생성
 */

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService target;

    @Mock
    private MembershipRepository membershipRepository;

    private final String userId = "id";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final int point = 10000;

    @DisplayName("맴버십 등록 실패_이미 존재함")
    @Test
    public void 맴버십_등록_실패_이미존재함(){
        Map<String, Object> tmp = new HashMap<>();
        //given
        doReturn(tmp).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, membershipType, point));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);

    }

    //JUnit5부터는 public 접근제어자 생략 가능
    @DisplayName("맴버쉽 등록 성공")
    @Test
    void 맴버쉽_등록_성공(){
        //given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(1).when(membershipRepository).save(any(Map.class));

        //when
        final int result = target.addMembership(userId, membershipType, point);

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(1);

        //verify
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Map.class));

    }

}