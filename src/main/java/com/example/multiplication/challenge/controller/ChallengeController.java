package com.example.multiplication.challenge.controller;

import com.example.multiplication.challenge.domain.Challenge;
import com.example.multiplication.challenge.service.ChallengeGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("challenges")
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private final ChallengeGeneratorService challengeGeneratorService;

    @GetMapping("random")
    public Challenge getRandomChallenge() {
        return challengeGeneratorService.randomChallenge();
    }

}
