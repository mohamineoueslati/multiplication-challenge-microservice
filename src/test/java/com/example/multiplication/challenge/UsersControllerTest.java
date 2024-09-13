package com.example.multiplication.challenge;

import com.example.multiplication.user.controller.UserController;
import com.example.multiplication.user.domain.User;
import com.example.multiplication.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(controllers = UserController.class)
public class UsersControllerTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<List<User>> jsonResponse;

    @Test
    public void callGetUsersEndpoint() throws Exception {
        // given
        List<Long> idList = List.of(1L, 2L);
        var users = List.of(new User(1L, "amine"), new User(2L, "oueslati"));
        given(userRepository.findAllByIdIn(idList)).willReturn(users);
        // when
        var response = mockMvc.perform(
                get("/users/" + String.join(",", idList.stream().map(Object::toString).toList()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponse.write(users).getJson());
    }

}
