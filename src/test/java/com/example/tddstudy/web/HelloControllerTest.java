package com.example.tddstudy.web;

import com.example.tddstudy.domain.HelloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloTest() throws Exception {
        //given
        String today = "2022-07-11";


        //when
        String body = this.restTemplate.getForObject("/hello", String.class);
        System.out.println(body);

        //then
        assertThat(body).contains(today);


    }
}
