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

/*Junit4에서 사용되덴 @RunWith가 JUnit5에서는 @ExtendWith로 변경 되었다.
 * @RunWith(SpringRunner.class) => @ExtendWith(SpringExtention.class)
 * Spring5 + JUnit5 에서는 위에껄 사용해야 한다.
 * @RunWith(MockitoJUnitRunner.class) => @ExtendWith(MockitoExtension.class)
 * @SpringBootTest는 @ExtendWith({SpringExtension.class})(메타어노테이션)을 포함하고 있기에
 * @SpringBootTest로 대체 가능
 * 하지만 너무 무거워 지양하는 바이다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloTest() throws Exception {
        //given
        String today = "2022-07-12";

        //when
        String body = this.restTemplate.getForObject("/hello", String.class);
        //System.out.println(body);

        //then
        assertThat(body).contains(today);
    }
}
