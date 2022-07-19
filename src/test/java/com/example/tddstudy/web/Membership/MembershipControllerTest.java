package com.example.tddstudy.web.Membership;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {
    @InjectMocks
    private MembershipController target;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public  void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

    @DisplayName("mockMvc가 Null이 아님")
    @Test
    void mockMvc가Null이아님() throws Exception{
        assertThat(target).isNotNull();
        assertThat(mockMvc).isNotNull();
    }
}
