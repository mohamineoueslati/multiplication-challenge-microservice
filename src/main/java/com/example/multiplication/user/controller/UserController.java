package com.example.multiplication.user.controller;

import com.example.multiplication.user.domain.User;
import com.example.multiplication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{idList}")
    public List<User> getUsersByIdList(@PathVariable final List<Long> idList) {
        log.info("Resolving aliases for users {}", idList);
        return userRepository.findAllByIdIn(idList);
    }

}
