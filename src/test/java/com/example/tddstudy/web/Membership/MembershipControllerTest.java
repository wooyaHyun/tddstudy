package com.example.tddstudy.web.Membership;

import com.example.tddstudy.domain.Membership.MembershipType;
import com.example.tddstudy.web.dto.MembershipRequestDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {
    @InjectMocks
    private MembershipController target;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

    @DisplayName("mockMvc가 Null이 아님")
    @Test
    void mockMvc가Null이아님() throws Exception{
        assertThat(target).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    void 멤버십등록실패_사용자식별값이헤더에없음() throws Exception{
        //given
        final String url = "/api/v1/memberships";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(membershipRequestDto(10000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isBadRequest());


    }

    private MembershipRequestDto membershipRequestDto(final int point, final MembershipType membershipType){
        return MembershipRequestDto.builder()
                .point(point)
                .membershipType(membershipType)
                .build();
    }

    @DisplayName("멤버십등록실패_포인트가 음수일 경우")
    @Test
    void 멤버십등록실패_포인트가_음수() throws Exception {
        //given
        final String url = "/api/v1/memberships";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header("X-USER-ID", "12345")
                        .content(gson.toJson(membershipRequestDto(-1, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("멤버십 등록 실패_멤버십 종류가 null일 경우")
    @Test
    void 멤버십등록실패_멤버십종류가null() throws Exception {
        //given
        final String url = "/api/v1/memberships";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header("X-USER-ID", "12345")
                        .content(gson.toJson(membershipRequestDto(10000, null)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isBadRequest());

    }
}
