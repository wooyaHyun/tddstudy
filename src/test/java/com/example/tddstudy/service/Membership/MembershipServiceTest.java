package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import com.example.tddstudy.exception.MembershipErrorResult;
import com.example.tddstudy.exception.MembershipException;
import com.example.tddstudy.web.dto.MembershipDetailResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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
    private final Long membershipId = -1L;

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

    @DisplayName("멤버십 목록 조회")
    @Test
    void 멤버십목록조회() {
        //given
        Map<String, Object> tmp1 = new HashMap<>();
        Map<String, Object> tmp2 = new HashMap<>();
        doReturn(Arrays.asList(tmp1, tmp2)).when(membershipRepository).findAllByUserId("userId");

        //when
        final List<MembershipDetailResponseDto> result = target.getMembershipList("userId");

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @DisplayName("멤버십 상세조회 실패_존재하지 않음")
    @Test
    void 멤버십상세조회실패_존재하지않음() {
        //given
        doReturn(null).when(membershipRepository).findById(membershipId);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.getMembership(membershipId, userId));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);

    }
    @DisplayName("멤버십 상세조회 실패_본인이 아님")
    @Test
    void 멤버십상세조회실패_본인이아님() {
        // given
        doReturn(null).when(membershipRepository).findById(membershipId);

        // when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.getMembership(membershipId, "notowner"));

        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    public void 멤버십상세조회성공() {
        // given
        doReturn(membership()).when(membershipRepository).findById(membershipId);

        // when
        final MembershipDetailResponseDto result = target.getMembership(membershipId, userId);

        // then
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(point);
    }



    private Map<String, Object> membership() {
        Map<String, Object> membership = new HashMap<>();
        membership.put("ID", -1L);
        membership.put("USER_ID", userId);
        membership.put("POINT", point);
        membership.put("MEMBERSHIP_NAME", MembershipType.NAVER);
        return membership;
    }
}